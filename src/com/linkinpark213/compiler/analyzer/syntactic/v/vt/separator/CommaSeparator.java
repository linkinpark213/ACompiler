package com.linkinpark213.compiler.analyzer.syntactic.v.vt.separator;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class CommaSeparator extends VT {
    public CommaSeparator() {
        acceptableSymbols.add(",");
    }

    @Override
    public boolean checkSymbol(Token token) {
        return token.toString().equals(",");
    }

    @Override
    public String toString() {
        return ",";
    }
}
