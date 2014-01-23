package com.thisnoble.javatest;

import java.util.ArrayList;

import com.thisnoble.javatest.Event;
import com.thisnoble.javatest.OrchestratorImpl;
import com.thisnoble.javatest.messagingclients.MessageReceiver;
import com.thisnoble.javatest.messagingclients.MessageSender;

public class TestCase1 {
	
 public static void main(String args[]){
	 
	 //Single unit test case with multiple event threads with different types of events

	 
	 Runnable t1 = new Runnable() {
	        public void run() {
	        	new MessageSender("A");
	        }
	    };
	    
	    Runnable t2 = new Runnable() {
	        public void run() {
	        	new MessageSender("B");
	        }
	    };
	    
	    Runnable t3 = new Runnable() {
	        public void run() {
	        	new MessageSender("C");
	        }
	    };
	    
	    Runnable t4 = new Runnable() {
	        public void run() {
	        	new MessageSender("D");
	        }
	    };
	    
	    new Thread(t1).start();
	    new Thread(t2).start();
	    
	    new Thread(t3).start();
	    new Thread(t4).start();
	    
	   
	 
	 OrchestratorService.service();
	 
 }
}
