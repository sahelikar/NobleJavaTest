package com.thisnoble.javatest.messagingclients;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.thisnoble.javatest.Event;

import javax.jms.*;

import java.util.Random;
 
public class MessageSender  {
    private static int ackMode;
    private static String clientQueueName;
 
    private boolean transacted = false;
    private MessageProducer producer;
   
    static {
        clientQueueName = "TestQueue";
        ackMode = Session.AUTO_ACKNOWLEDGE; 
    }
 
    public MessageSender(String messageType) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
           

            Session session = connection.createSession(transacted, ackMode);
            Destination adminQueue = session.createQueue(clientQueueName);
 
            //Setup a message producer to send message to the queue the server is consuming from
            this.producer = session.createProducer(adminQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
 

 
            //Now create the actual message you want to send
            TextMessage txtMessage = session.createTextMessage();
            txtMessage.setStringProperty("MessageType", messageType);
            txtMessage.setText("ProtocolMessage");
 
            
            String correlationId = this.createRandomString();
            txtMessage.setJMSCorrelationID(correlationId);
            
            Event e = new Event();
            this.producer.send(txtMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
 
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
 
 
    public static void main(String[] args) {
        new MessageSender("Type A");
        
       

    }
}
