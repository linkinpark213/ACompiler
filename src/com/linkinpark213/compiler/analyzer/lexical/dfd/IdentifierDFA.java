package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.LexicalAnalyzer;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidIdentifierException;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Identifier;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Keyword;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class IdentifierDFA implements DFA {
    private static IdentifierDFA theOnlyIdentifierDFD;
    private State initialState;
    private ArrayList<State> finalStates;

    private IdentifierDFA() {
        this.initialState = new State(0);
        State middleState = new State(1);
        finalStates = new ArrayList<State>();
        this.initialState.addNextState(middleState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_');
            }
        });
        middleState.addNextState(middleState, new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c >= '0' && c <= '9');
            }
        });
        finalStates.add(middleState);
    }

    public static IdentifierDFA getInstance() {
        if (theOnlyIdentifierDFD == null) {
            theOnlyIdentifierDFD = new IdentifierDFA();
        }
        return theOnlyIdentifierDFD;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public Symbol nextSymbol(String string, LexicalAnalyzer analyzer) throws InvalidIdentifierException {
        State statePointer = initialState;
        StringBuilder symbolBuilder = new StringBuilder();

        while (statePointer != null && symbolBuilder.length() < string.length()) {
            if (string.charAt(symbolBuilder.length()) == ' ') break;
            State nextState = statePointer.nextState(string.charAt(symbolBuilder.length()));
            if (nextState != null) {
                statePointer = nextState;
                symbolBuilder.append(string.charAt(symbolBuilder.length()));
            } else break;
        }
        if (finalStates.contains(statePointer)) {
            if (Keyword.keyWords.contains(symbolBuilder.toString())) {
                return new Keyword(symbolBuilder.toString());
            } else if (Constant.boolConstants.contains(symbolBuilder.toString())) {
                return new Constant(symbolBuilder.toString(), Constant.TYPE_BOOL);
            } else {
                return new Identifier(symbolBuilder.toString());
            }
        } else {
            symbolBuilder.append(string.charAt(symbolBuilder.length()));
            throw new InvalidIdentifierException("Invalid identifier \"" + symbolBuilder.toString() + "\"");
        }
    }
}
