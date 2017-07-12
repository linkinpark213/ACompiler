package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class Operator extends VT {
    private String symbol;

    @Override
    public boolean checkSymbol(Symbol symbol) {
        for (int i = 0; i < acceptableSymbols.size(); i++) {
            if (acceptableSymbols.get(i).equals(symbol.toString()))
                return true;
        }
        return false;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}