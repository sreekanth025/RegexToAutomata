package com.sreekanth.compilerdesign.models;

import com.sreekanth.compilerdesign.common.AppConstants;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class TransitionTable {

    private Map<Integer, Map<Character, Set<Integer>>> table;
    private List<Integer> states;
    private Set<Character> actions;
    private List<Integer> finalStates;

    public TransitionTable(List<Integer> states, List<Transition> transitions) {

        this.states = states;
        this.actions = new HashSet<Character>();
        this.table = new HashMap<>();

        for(Transition t: transitions) {
            this.actions.add(t.getAction());
        }

        for(Integer state: states) {
            table.put(state, new HashMap<>());
            for(Character c: actions) {
                table.get(state).put(c, new HashSet<Integer>());
            }
        }

        for(Transition t: transitions) {
            this.table
                    .get(t.getFromState())
                    .get(t.getAction())
                    .add(t.getToState());
        }
    }

    public TransitionTable(Automata automata) {
        this(automata.getStates(), automata.getTransitions());
    }

    public TransitionTable( Set<Character> actions) {
        this.states = new ArrayList<Integer>();
        this.actions = actions;
        this.table = new HashMap<>();
    }

    public void addState(Integer state) {

        if(!this.states.contains(state)) {
            this.states.add(state);
            this.table.put(state, new HashMap<>());

            for(Character c: this.actions) {
                this.table
                        .get(state)
                        .put(c, new HashSet<Integer>());
            }
        }
    }

    public void addTransition(Integer fromState, Character action, Integer toState) {
        this.table.get(fromState).get(action).add(toState);
    }

    public Map<Character, Set<Integer>> getTransitionsFrom(Integer state) {
        return this.table.get(state);
    }

    public List<Transition> getAllTransitions() {

        List<Transition> transitions = new ArrayList<Transition>();

        for(Map.Entry<Integer, Map<Character, Set<Integer>>> entry:
                this.table.entrySet()) {

            Integer fromState = entry.getKey();
            for(Map.Entry<Character, Set<Integer>> innerEntry:
                    entry.getValue().entrySet()) {

                Character action = innerEntry.getKey();
                for(Integer toState: innerEntry.getValue()) {
                    transitions.add(new Transition(fromState, toState, action));
                }
            }
        }

        return transitions;
    }

    public void show() {

        String formatString = "| %" + AppConstants.tableCellWidth.toString() + "s";

        String cellUnderline = "";
        for(int i=0; i<AppConstants.tableCellWidth+3; i++) cellUnderline += "-";

        String rowUnderline = cellUnderline;

        List<String> headings = new ArrayList<String>();
        headings.add("states");

        for(Character c: actions) {
            formatString += " | %" + AppConstants.tableCellWidth.toString() + "s";
            headings.add(c.toString());
            rowUnderline += cellUnderline;
        }
        formatString += "\n";

        System.out.println(rowUnderline);
        System.out.format(formatString, headings.toArray());
        System.out.println(rowUnderline);

        for(Map.Entry entry: table.entrySet()) {

            List<String> row = new ArrayList<String>();
            row.add(entry.getKey().toString());

            Map<Character, Integer> m = (Map<Character, Integer>) entry.getValue();
            for(Map.Entry innerEntry: m.entrySet()) {
                row.add(innerEntry.getValue().toString());
            }

            System.out.format(formatString, row.toArray());
        }

        System.out.println(rowUnderline);
//        System.out.println("Finished printing Transition Table...");
    }
}
