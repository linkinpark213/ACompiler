package com.linkinpark213.compiler.analyzer.lexical.dfd;

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
        finalStates.add(singlePlusState);
        finalStates.add(singleMinusState);
        finalStates.add(doublePlusState);
        finalStates.add(doubleMinusState);
        finalStates.add(multipleState);
    }

    @Override
    public Symbol nextSymbol(String string) {
        return null;
    }
}
