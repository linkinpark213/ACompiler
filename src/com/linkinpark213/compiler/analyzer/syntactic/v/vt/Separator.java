package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Separator extends VT {
    public Separator() {
        acceptableSymbols.add(":");
        acceptableSymbols.add(";");
        acceptableSymbols.add("{");
        acceptableSymbols.add("}");
        acceptableSymbols.add("(");
        acceptableSymbols.add(")");
        acceptableSymbols.add(",");
    }

    public Separator(String... symbols) {
        for (String symbol : symbols) {
            acceptableSymbols.add(symbol);
        }
    }

    @Override
    public boolean checkSymbol(Symbol symbol) {
        for (String acceptableSymbol : acceptableSymbols) {
            if (acceptableSymbol.equals(symbol))
                return true;
        }
        return false;
    }
}
