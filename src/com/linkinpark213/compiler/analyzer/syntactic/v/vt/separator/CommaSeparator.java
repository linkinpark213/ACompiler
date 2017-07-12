package com.linkinpark213.compiler.analyzer.syntactic.v.vt.separator;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class CommaSeparator extends VT {
    public CommaSeparator() {
        acceptableSymbols.add(",");
    }

    @Override
    public boolean checkSymbol(Symbol symbol) {
        return symbol.toString().equals(",");
    }
}
