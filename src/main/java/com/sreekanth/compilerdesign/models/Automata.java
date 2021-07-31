package com.sreekanth.compilerdesign.models;

import java.util.ArrayList;
import java.util.List;

public interface Automata {
    List<Transition> getTransitions();
    List<Integer> getStates();
    int getSize();
    void addTransition(Transition t);
}
