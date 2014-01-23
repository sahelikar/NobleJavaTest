package com.thisnoble.javatest;

//marker
public class Event {
	
	private String eventType;
	private boolean isProcessed = false;
	private String eventMessage;
	
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public boolean getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public String getEventMessage() {
		return eventMessage;
	}
	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

}