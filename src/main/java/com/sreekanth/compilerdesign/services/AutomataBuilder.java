package com.sreekanth.compilerdesign.services;

import com.sreekanth.compilerdesign.models.automata.NFA;
import com.sreekanth.compilerdesign.utils.Operations;

import java.util.Stack;

public class AutomataBuilder {

    public static NFA createNFA(String regExPostfix) {

        Stack<NFA> stack = new Stack<NFA>();

        for(Character c: regExPostfix.toCharArray()) {

//            System.out.println("Processing character: " + c);
            if(Character.isLetter(c)) {
                stack.push(new NFA(c));
            }

            else if(c.equals('*')) {
                NFA nfa = stack.pop();
                stack.push(Operations.kleeneStar(nfa));
            }

            else if(c.equals('.')) {
                NFA nfa2 = stack.pop();
                NFA nfa1 = stack.pop();

                stack.push(Operations.concat(nfa1, nfa2));
            }

            else if(c.equals('+')) {
                NFA nfa2 = stack.pop();
                NFA nfa1 = stack.pop();

                stack.push(Operations.union(nfa1, nfa2));
            }
        }

//        System.out.println("Final NFA stack size: " + stack.size());
        return stack.peek();
    }

}
