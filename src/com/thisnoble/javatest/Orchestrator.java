package com.thisnoble.javatest;

//Interface for Orchestrator

public interface Orchestrator {

    void register(Processor processor);

    void receive(Event event);
}