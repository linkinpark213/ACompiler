package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidOperatorException;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Operator;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class OperatorDFD implements DFD {
    private static OperatorDFD theOnlyOperatorDFD;
    private State initialState;
    private ArrayList<State> finalStates;

    private OperatorDFD() {
        this.initialState = new State(0);
        this.finalStates = new ArrayList<State>();
        State singlePlusState = new State(1);
        State doublePlusState = new State(2);
        State singleMinusState = new State(3);
        State doubleMinusState = new State(4);
        State multipleState = new State(5);
        State colonState = new State(6);
        State assignmentState = new State(7);
        initialState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '+';
            }
        }, singlePlusState);
        initialState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '-';
            }
        }, singleMinusState);
        initialState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '*';
            }
        }, multipleState);
        initialState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == ':';
            }
        }, colonState);
        singlePlusState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '+';
            }
        }, doublePlusState);
        singleMinusState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '-';
            }
        }, doubleMinusState);
        colonState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        }, assignmentState);
        finalStates.add(singlePlusState);
        finalStates.add(singleMinusState);
        finalStates.add(doublePlusState);
        finalStates.add(doubleMinusState);
        finalStates.add(multipleState);
        finalStates.add(assignmentState);
    }

    public static OperatorDFD getInstance() {
        if (theOnlyOperatorDFD == null) {
            theOnlyOperatorDFD = new OperatorDFD();
        }
        return theOnlyOperatorDFD;
    }

    @Override
    public Symbol nextSymbol(String string) {
        State statePointer = initialState;
        State nextStatePointer = initialState;
        StringBuilder symbolBuilder = new StringBuilder();

        try {
            nextStatePointer = statePointer.nextState(string.charAt(0));
            symbolBuilder.append(string.charAt(0));
            statePointer = nextStatePointer;
            nextStatePointer = statePointer.nextState(string.charAt(1));
            if (finalStates.contains(nextStatePointer)) {
                statePointer = nextStatePointer;
                symbolBuilder.append(string.charAt(1));
                return new Operator(symbolBuilder.toString());
            } else {
                if (finalStates.contains(statePointer)) {
                    return new Operator(symbolBuilder.toString());
                } else {
                    throw new InvalidOperatorException();
                }
            }
        } catch (InvalidOperatorException e) {
            System.out.println("Invalid Operator \"" + symbolBuilder.toString() + "\"");
        }
        return null;
    }
}
