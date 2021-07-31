package com.sreekanth.compilerdesign.utils;

import com.sreekanth.compilerdesign.models.automata.NFA;
import com.sreekanth.compilerdesign.models.Transition;

public class Operations {

    public static NFA union(NFA n1, NFA n2) {

        NFA result = new NFA(n1.getSize()+ n2.getSize() + 2);

        int offSet1 = 1;
        int offSet2 = n1.getSize() + 1;
        int resultFinalState = n1.getSize() + n2.getSize() + 1;

        /*
        * Adding new E-Transition
        * from newly created starting node to starting node of n1
        * */
        result.addTransition(new Transition(0, 1, 'E'));

        /*
        * Adding all transitions of n1 to result
        * */
        for(Transition t: n1.getTransitions()) {
            result.addTransition(
                    new Transition(
                        t.getFromState()+offSet1,
                        t.getToState()+offSet1,
                        t.getAction()
                    )
            );
        }

        /*
        * Adding new E-Transition
        * from n1's final state to result's final state
        * */
        result.addTransition(new Transition(n1.getSize(), resultFinalState, 'E'));


        /*
        * Adding new E-Transition
        * from newly created starting state to starting state of n2
        * */
        result.addTransition(new Transition(0, n1.getSize() + 1, 'E'));

        /*
        * Adding all transitions of n2 to result
        * */
        for(Transition t: n2.getTransitions()) {
            result.addTransition(
                    new Transition(
                            t.getFromState() + offSet2,
                            t.getToState() + offSet2,
                            t.getAction()
                    )
            );
        }

        /*
        * Adding new E-Transition
        * from n2's final state to result's final state
        * */
        result.addTransition(new Transition(resultFinalState-1, resultFinalState, 'E'));

        return result;
    }

    public static NFA concat(NFA n1, NFA n2) {

        /*
        * Removing the first node of second NFA
        * (To merge this node with the final node of first NFA)
        * */
        n2.removeStartingState();


        /*
        * Adding all the transitions components of n2 to n1
        * (merging n2's transitions with n1)
        * */
        int offSet = n1.getSize() - 1;
        for(Transition t: n2.getTransitions()) {
            n1.addTransition(new Transition(
                    t.getFromState() + offSet,
                    t.getToState() + offSet,
                    t.getAction()
            ));
        }

        /*
        * Adding n2 states to the n1
        * */
        for(Integer s: n2.getStates()) {
            n1.addState(s + offSet);
        }

        return n1;
    }

    public static NFA kleeneStar(NFA n) {

        NFA result = new NFA(n.getSize() + 2);

        /*
        * Adding new E-Transition
        * from newly created starting state to original NFA's starting state
        * */
        result.addTransition(new Transition(0, 1, 'E'));

        /*
        * Adding all the transitions of n to result
        * */
        int offSet = 1;
        for(Transition t: n.getTransitions()) {
            result.addTransition(new Transition(
                    t.getFromState() + offSet,
                    t.getToState() + offSet,
                    t.getAction()
            ));
        }

        /*
        * Adding new E-Transition
        * from final state of original NFA to newly created final state
        * */
        result.addTransition(new Transition(
                n.getSize(),
                n.getSize() + 1,
                'E'
        ));

        /*
        * Adding new E-Transition (looping back)
        * from last state of nfa to its first state
        * */
        result.addTransition(new Transition(n.getSize(), 1, 'E'));

        /*
        * Adding new E-Transition
        * from new start state to new final state
        * */
        result.addTransition(new Transition(0, n.getSize()+1, 'E'));

        return result;
    }
}
