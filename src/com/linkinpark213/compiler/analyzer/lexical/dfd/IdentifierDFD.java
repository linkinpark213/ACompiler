package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidIdentifierException;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Identifier;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Keyword;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class IdentifierDFD implements DFD {
    private static IdentifierDFD theOnlyIdentifierDFD;
    private State initialState;
    private ArrayList<State> finalStates;

    private IdentifierDFD() {
        this.initialState = new State(0);
        State middleState = new State(1);
        finalStates = new ArrayList<State>();
        this.initialState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_');
            }
        }, middleState);
        middleState.addInputHandler(new InputHandler() {
            @Override
            public boolean handle(char c) {
                return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c >= '0' && c <= '9');
            }
        }, middleState);
        finalStates.add(middleState);
    }

    public static IdentifierDFD getInstance() {
        if (theOnlyIdentifierDFD == null) {
            return new IdentifierDFD();
        } else {
            return theOnlyIdentifierDFD;
        }
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    @Override
    public Symbol nextSymbol(String string) {
        State statePointer = initialState;
        StringBuilder symbolBuilder = new StringBuilder();

        try {
            while (statePointer != null && symbolBuilder.length() < string.length()) {
                if (string.charAt(symbolBuilder.length()) == ' ') break;
                State nextState = statePointer.nextState(string.charAt(symbolBuilder.length()));
                symbolBuilder.append(string.charAt(symbolBuilder.length()));
                if (nextState != null) {
                    statePointer = nextState;
                } else throw new InvalidIdentifierException();
            }
            if (finalStates.contains(statePointer)) {
                if (Keyword.keyWords.contains(symbolBuilder.toString())) {
                    return new Keyword(symbolBuilder.toString());
                } else {
                    return new Identifier(symbolBuilder.toString());
                }
            } else {
                throw new InvalidIdentifierException();
            }
        } catch (InvalidIdentifierException e) {
            System.out.println("Invalid identifier \"" + symbolBuilder.toString() + "\"");
            System.exit(0);
        }
        return null;
    }
}
