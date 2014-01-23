package com.thisnoble.javatest;

import java.util.ArrayList;

import com.thisnoble.javatest.Event;
import com.thisnoble.javatest.Orchestrator;
import com.thisnoble.javatest.Processor;

//Implementation of Orchestrator

public final class OrchestratorImpl implements Orchestrator {

	      
	
	Event event  = new Event();
	
	@Override
	public void register(Processor processor) {
		if(processor.interestedIn(event)){
			processor.processEvent();
			
		}
	}

	@Override
	public void receive(Event event) {
		this.event = event;
		

	}
	
	public Event CollateEvents(ArrayList<Event> eventList){
		String collatedEventString ="";
		Event collatedEvent =  new Event();
		for(Event event:eventList){
			if(event.getIsProcessed()){
				collatedEventString  = collatedEventString+event.getEventType()+"::"+event.getEventMessage()+";";
			}
		}
		collatedEvent.setEventType("Collated");
		collatedEvent.setEventMessage(collatedEventString);
		
		return collatedEvent;
		}

		
	

}
