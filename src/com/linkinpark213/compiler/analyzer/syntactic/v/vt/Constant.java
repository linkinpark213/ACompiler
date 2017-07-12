package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Constant extends VT {
    private String value;
    private String type;

    @Override
    public boolean checkSymbol(Symbol symbol) {
        return symbol instanceof com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
    }

    @Override
    public String toString() {
        return "Constant (" + type + ") : " + value;
    }
}
