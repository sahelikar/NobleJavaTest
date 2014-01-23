package com.thisnoble.javatest;

import java.util.ArrayList;

import com.thisnoble.javatest.messagingclients.MessageReceiver;



public class OrchestratorService {
	
	public static void service(){
		
		//Service provided by Orchestrator
		
		 MessageReceiver mr = new MessageReceiver();
		 
		 OrchestratorImpl orcImpl = new OrchestratorImpl();
		 
		 ArrayList<Event> eventList = mr.getEventList();
		 ArrayList<Event> processedEventList = new ArrayList<Event>();
		 for(Event event: eventList){
			 
			 Processor proc = new Processor();
			 
			 orcImpl.receive(event);
			 orcImpl.register(proc);
			 
			 processedEventList.add(proc.sendProcessedEvent());
			 
		 }
		 
		 
		 Publisher pub = new Publisher();
		 pub.publish(orcImpl.CollateEvents(processedEventList));
	}

}
