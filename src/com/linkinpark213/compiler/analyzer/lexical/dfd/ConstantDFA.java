package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidConstantException;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Constant;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class ConstantDFA implements DFA {
    private State initialState;
    ArrayList<State> finalStates;
    public static ConstantDFA theOnlyConstantDFD;

    private ConstantDFA() {
        initialState = new State(0);
        finalStates = new ArrayList<State>();
        State intState = new State(1);
        State unstableState = new State(2);
        State floatState = new State(3);
        State minusState = new State(4);
        State singleQuoteState = new State(5);
        State quoteWithCharState = new State(6);
        State doubleQuoteState = new State(7);

        //  Connect States
        initialState.addNextState(intState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= '0' && c <= '9');
            }
        });
        intState.addNextState(intState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= '0' && c <= '9');
            }
        });
        intState.addNextState(unstableState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c == '.');
            }
        });
        unstableState.addNextState(floatState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= '0' && c <= '9');
            }
        });
        floatState.addNextState(floatState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= '0' && c <= '9');
            }
        });
        initialState.addNextState(minusState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '-';
            }
        });
        minusState.addNextState(intState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= '0' && c <= '9');
            }
        });
        initialState.addNextState(singleQuoteState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '\'';
            }
        });
        singleQuoteState.addNextState(quoteWithCharState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return true;
            }
        });
        quoteWithCharState.addNextState(doubleQuoteState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return c == '\'';
            }
        });
        finalStates.add(intState);
        finalStates.add(floatState);
        finalStates.add(doubleQuoteState);
    }

    public static ConstantDFA getInstance() {
        if (theOnlyConstantDFD == null) {
            theOnlyConstantDFD = new ConstantDFA();
        }
        return theOnlyConstantDFD;
    }

    @Override
    public Token nextSymbol(String string, LexicalAnalyzer analyzer) throws InvalidConstantException {
        State statePointer = initialState;
        State nextStatePointer = initialState;
        StringBuilder symbolBuilder = new StringBuilder();
        while (statePointer != null && symbolBuilder.length() < string.length()) {
            if (string.charAt(symbolBuilder.length()) == ' ') break;
            nextStatePointer = statePointer.nextState(string.charAt(symbolBuilder.length()));
            if (nextStatePointer != null) {
                statePointer = nextStatePointer;
                symbolBuilder.append(string.charAt(symbolBuilder.length()));
            } else break;
        }
        if (finalStates.contains(statePointer)) {
            String constant = symbolBuilder.toString();
            if (constant.charAt(0) == '\'' && constant.charAt(2) == '\'') {
                return new Constant("" + constant.charAt(1), Constant.TYPE_CHAR);
            } else if (constant.contains(".")) {
                return new Constant(constant, Constant.TYPE_FLOAT);
            } else return new Constant(constant, Constant.TYPE_INT);
        } else {
            symbolBuilder.append(string.charAt(symbolBuilder.length()));
            throw new InvalidConstantException("Invalid Constant: " + symbolBuilder.toString());
        }
    }
}
