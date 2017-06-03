package com.linkinpark213.compiler.analyzer.lexical.dfd;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class ConstantDFD implements DFD {
    private State initialState;
    ArrayList<State> finalStates;

    private ConstantDFD() {
        initialState = new State(0);
        finalStates = new ArrayList<State>();
    }

    @Override
    public Symbol nextSymbol(String string) {
        return null;
    }
}
