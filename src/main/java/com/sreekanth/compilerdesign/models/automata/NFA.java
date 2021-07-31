package com.sreekanth.compilerdesign.models.automata;

import com.sreekanth.compilerdesign.common.AppConstants;
import com.sreekanth.compilerdesign.models.Automata;
import com.sreekanth.compilerdesign.models.Transition;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NFA implements Automata {

    private ArrayList<Integer> states;
    private ArrayList<Transition> transitions;

    public NFA() {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
    }

    public NFA(int numStates) {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
        initStates(numStates);
    }

    public NFA(char c) {
        this.states = new ArrayList<Integer>();
        this.transitions = new ArrayList<Transition>();
        initStates(2);
        this.transitions.add(new Transition(0, 1, c));
    }

    public void initStates(int n) {
        for(int i=0; i<n; i++) {
            this.states.add(i);
        }
    }

    public void show() {

        System.out.println(AppConstants.underline);
        System.out.println("Number of states in NFA: " + this.states.size());

        System.out.println("Transitions: ");
        for(Transition t: transitions) {
            t.printTransition();
        }

        System.out.println("Finished...\n");
    }

    public int getSize() {
        return this.states.size();
    }

    public void addTransition(Transition t) {
        this.transitions.add(t);
    }

    public void removeStartingState() {
        this.states.remove(0);
    }

    public void addState(int n) {
        this.states.add(n);
    }

    public Set<Character> getActions() {

        Set<Character> actions = new HashSet<Character>();
        for(Transition t: transitions) {
            actions.add(t.getAction());
        }

        return actions;
    }
}
