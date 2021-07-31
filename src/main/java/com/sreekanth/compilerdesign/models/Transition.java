package com.sreekanth.compilerdesign.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Transition {

    private Integer fromState, toState;
    private Character action;

    public void printTransition() {
        System.out.println("(" + this.getFromState()
                + ", " + this.getAction()
                + ", " + this.getToState() + ")"
        );
    }
}
