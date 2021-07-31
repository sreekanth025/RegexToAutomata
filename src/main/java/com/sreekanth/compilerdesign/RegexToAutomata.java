package com.sreekanth.compilerdesign;

import com.sreekanth.compilerdesign.models.TransitionTable;
import com.sreekanth.compilerdesign.models.automata.NFA;
import com.sreekanth.compilerdesign.services.AutomataBuilder;
import com.sreekanth.compilerdesign.utils.Conversions;
import com.sreekanth.compilerdesign.visualization.AutomataVisualization;

import java.util.*;

public class RegexToAutomata {

    public static void main(String[] args) throws Exception {

        System.setProperty("org.graphstream.ui", "swing");
        Scanner scanner = new Scanner(System.in);


        List<String> examples = new ArrayList<String>(Arrays.asList(
                "a", "a+b", "ab", "a*", "a(a+b)", "a*(a+b)", "a(a+b)*b"
        ));

        System.out.print("Enter a Regular Expression (or 'random'): ");
        String regEx = scanner.nextLine();

        if(regEx.equals("random")) {
            regEx = examples.get(new Random().nextInt(examples.size()));
        }

        System.out.println("Given regEx: " + regEx);
        String regExFormatted = Conversions.formatRegEx(regEx);
        System.out.println("Formatted: " + regExFormatted);

        String postfix = Conversions.infixToPostfix(regExFormatted);
        System.out.println("Postfix: " + postfix);

        NFA nfa = AutomataBuilder.createNFA(postfix);
        nfa.show();
        AutomataVisualization.displayAutomata(nfa);

        TransitionTable transitionTable = new TransitionTable(nfa.getStates(), nfa.getTransitions());
        transitionTable.show();
    }
}
