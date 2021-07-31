package com.sreekanth.compilerdesign.utils;

import java.util.*;

public class Conversions {

    private final static Map<Character, Integer> precedenceMap = Map.ofEntries(
            Map.entry('*', 3),
            Map.entry('.', 2),
            Map.entry('+', 1)
    );

    public static String infixToPostfix(String regEx) throws Exception {

        Stack<Character> stack = new Stack<Character>();
        String output = "";

        for(Character c: regEx.toCharArray()) {

            if(isLetter(c)) output += c;
            else if(c == '(') stack.push(c);

            else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(')
                    output += stack.pop();

                stack.pop();
            }

            else {
                while(!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()))
                    output += stack.pop();

                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            if(stack.peek() == '(') {
                throw new Exception("Invalid Expression");
            }

            output += stack.pop();
        }

        return output;
    }

    public static String formatRegEx(String regEx) {

        String result = "";
        List<Character> operators = Arrays.asList('*', '+');

        for (int i = 0; i < regEx.length()-1; i++) {

            Character c1 = regEx.charAt(i);
            Character c2 = regEx.charAt(i + 1);
            result += c1;

            if (!c1.equals('(')
                    && !c2.equals(')')
                    && !operators.contains(c2)
                    && !c1.equals('+')
            ) {
                result += '.';
            }
        }

        result += regEx.charAt(regEx.length()-1);
        return  result;
    }


    private static int getPrecedence(char c) {
        if(precedenceMap.containsKey(c)) {
            return precedenceMap.get(c);
        }
        return -1;
    }

    private static boolean isLetter(char c) {
        if(Character.isLetter(c))
            return true;
        return false;
    }
}
