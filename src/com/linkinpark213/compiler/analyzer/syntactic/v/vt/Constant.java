package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Constant extends VT {
    private String value;
    private String type;

    @Override
    public boolean checkSymbol(Token token) {
        return token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Constant;
    }

    @Override
    public String toString() {
        return "Constant (" + type + ") : " + value;
    }
}
