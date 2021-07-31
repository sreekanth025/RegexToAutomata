package com.sreekanth.compilerdesign.models.automata;

import com.sreekanth.compilerdesign.models.Automata;
import com.sreekanth.compilerdesign.models.Transition;
import com.sreekanth.compilerdesign.models.TransitionTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class DFA implements Automata {

    private List<Transition> transitions;
    private List<Integer> states;

    @Override
    public int getSize() {
        return this.states.size();
    }

    @Override
    public void addTransition(Transition t) {
        this.transitions.add(t);
    }

    public DFA(NFA nfa) {

        TransitionTable dfaTransitionTable = new TransitionTable(nfa.getActions());
        TransitionTable nfaTransitionTable = new TransitionTable(nfa);

        /*
        * Adding all the transitions from state-0;
        * */
        dfaTransitionTable.addState(0);
        for(Map.Entry<Character, Set<Integer>> entry:
                nfaTransitionTable.getTransitionsFrom(0).entrySet()) {

            Character action  = entry.getKey();
            for(Integer toState: entry.getValue()) {
                dfaTransitionTable.addTransition(0, action, toState);
            }
        }


        // After all the process
        this.transitions = dfaTransitionTable.getAllTransitions();
        this.states = dfaTransitionTable.getStates();
    }
}
