package com.thisnoble.javatest.messagingclients;

import java.util.ArrayList;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;

import com.thisnoble.javatest.Event;

public class MessageReceiver {
	
    private static int ackMode;
    private static String clientQueueName;
 
    private boolean transacted = false;
    private MessageConsumer consumer;
    
    ArrayList<Event> eventList = new ArrayList<Event>();
   
    public ArrayList<Event> getEventList() {
		return eventList;
	}

	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

	static {
        clientQueueName = "TestQueue";
        ackMode = Session.AUTO_ACKNOWLEDGE; 
    }

    
    public MessageReceiver() {
    	
    	
    	
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(transacted, ackMode);
            
         // Create the destination (Topic or Queue)
            Destination adminQueue = session.createQueue(clientQueueName);
 
         

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(adminQueue);

            // Wait for a message
            Message message = (Message) consumer.receive(1000);
            while(message!=null){
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
               
                Event event = new Event();
                event.setEventMessage(text);
                event.setEventType(textMessage.getStringProperty("MessageType"));
                eventList.add(event);
            } else {
                System.out.println("Received: " + message);
            }
            
            message =  (Message) consumer.receive(1000);
            
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
        
    }
 
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
    
    public static void main(String args[]){
    	new MessageReceiver();
    }
}
