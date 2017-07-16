package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Identifier extends VT {
    private String name;

    @Override
    public boolean checkSymbol(Token token) {
        this.setName(token.toString());
        return token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Identifier: " + name;
    }
}
