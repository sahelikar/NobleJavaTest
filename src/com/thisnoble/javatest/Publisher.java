package com.thisnoble.javatest;

import com.thisnoble.javatest.Event;
import com.thisnoble.javatest.Processor;

public class Publisher {
	
	//Publisher for publish the event from Orchestrator

        void publish(Event event){
        	System.out.println("Event published");
        	System.out.println(event.getEventMessage());
        	System.out.println(event.getEventType());
        }
}