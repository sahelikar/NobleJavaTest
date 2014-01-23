package com.thisnoble.javatest;


import com.thisnoble.javatest.Event;

public class Processor implements INobleEventListener, Runnable{
	
	Event event = new Event();

	

   public boolean interestedIn(Event event){
	   
    	if(event.getEventType().equals("A")||event.getEventType().equals("B")||event.getEventType().equals("D")){
    		
    		this.event = event;
    		return true;
    	}
    	else{
    		return false;
    	}
    }

		@Override
		public void processEvent() {
			
			this.event.setIsProcessed(true) ;
			System.out.println("Processor::Event Processed by Process");
			
			
		}
		
		public Event sendProcessedEvent(){
			System.out.println(this.event.getEventType()+"::"+this.event.getEventMessage());
			return this.event;
		}

		@Override
		public void run() {
			processEvent();
			
		}
}