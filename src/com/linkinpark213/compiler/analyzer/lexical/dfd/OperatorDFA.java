package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidOperatorException;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Operator;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Separator;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class OperatorDFA implements DFA {
    private static OperatorDFA theOnlyOperatorDFD;
    private State initialState;
    private ArrayList<State> finalStates;

    private OperatorDFA() {
        this.initialState = new State(0);
        this.finalStates = new ArrayList<State>();
        State singlePlusState = new State(1);
        State doublePlusState = new State(2);
        State singleMinusState = new State(3);
        State doubleMinusState = new State(4);
        State multipleState = new State(5);
        State colonState = new State(6);
        State assignmentState = new State(7);
        State greaterState = new State(8);
        State greaterEqualState = new State(9);
        State lowerState = new State(10);
        State lowerEqualState = new State(11);
        State equalState = new State(12);
        State notState = new State(13);
        State notEqualState = new State(14);
        State andState = new State(15);
        State orState = new State(16);
        initialState.addNextState(singlePlusState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '+';
            }
        });
        initialState.addNextState(singleMinusState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '-';
            }
        });
        initialState.addNextState(multipleState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '*';
            }
        });
        initialState.addNextState(colonState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == ':';
            }
        });
        initialState.addNextState(greaterState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '>';
            }
        });
        initialState.addNextState(lowerState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '<';
            }
        });
        initialState.addNextState(equalState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        });
        initialState.addNextState(notState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '!';
            }
        });
        initialState.addNextState(andState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '&';
            }
        });
        initialState.addNextState(orState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '|';
            }
        });
        singlePlusState.addNextState(doublePlusState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '+';
            }
        });
        singleMinusState.addNextState(doubleMinusState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '-';
            }
        });
        colonState.addNextState(assignmentState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        });
        greaterState.addNextState(greaterEqualState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        });
        lowerState.addNextState(lowerEqualState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        });
        notState.addNextState(notEqualState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '=';
            }
        });
        finalStates.add(singlePlusState);
        finalStates.add(singleMinusState);
        finalStates.add(doublePlusState);
        finalStates.add(doubleMinusState);
        finalStates.add(multipleState);
        finalStates.add(assignmentState);
        finalStates.add(greaterState);
        finalStates.add(greaterEqualState);
        finalStates.add(lowerState);
        finalStates.add(lowerEqualState);
        finalStates.add(equalState);
        finalStates.add(notState);
        finalStates.add(notEqualState);
        finalStates.add(andState);
        finalStates.add(orState);
    }

    public static OperatorDFA getInstance() {
        if (theOnlyOperatorDFD == null) {
            theOnlyOperatorDFD = new OperatorDFA();
        }
        return theOnlyOperatorDFD;
    }

    @Override
    public Token nextSymbol(String string, LexicalAnalyzer analyzer) throws InvalidOperatorException {
        State statePointer = initialState;
        State nextStatePointer = initialState;
        StringBuilder symbolBuilder = new StringBuilder();

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
                if (statePointer.getStateNum() == 6)
                    return new Separator(':');
                throw new InvalidOperatorException("Invalid ArithmeticOperator \"" + symbolBuilder.toString() + "\"");
            }
        }
    }
}
