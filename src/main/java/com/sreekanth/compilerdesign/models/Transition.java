package com.sreekanth.compilerdesign.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Transition {
    private int fromState, toState;
    private char action;

    public void printTransition() {
        System.out.println("(" + this.getFromState()
                + ", " + this.getAction()
                + ", " + this.getToState() + ")"
        );
    }
}
