package com.sreekanth.compilerdesign.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class NFA {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ArrayList<Integer> states;
    public ArrayList<Transition> transitions;
    public int finalState;

    public NFA() {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
        this.finalState = 0;
    }

    public NFA(int numStates) {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
        this.finalState = 0;
        initStates(numStates);
    }

    public NFA(char c) {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
        initStates(2);
        this.finalState = 1;
        this.transitions.add(new Transition(0, 1, c));
    }

    public void initStates(int n) {
        for(int i=0; i<n; i++) {
            this.states.add(i);
        }
    }

    public void show() {

        LOGGER.info("Printing the contents of NFA: ");
        System.out.println("Number of states: " + this.states.size());

        System.out.println("Transitions: ");
        for(Transition t: transitions) {
            t.printTransition();
        }

        System.out.println("Finished...\n");
    }
}
