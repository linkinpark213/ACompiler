package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class BooleanOperator extends Operator {
    public BooleanOperator() {
        this("&", "|", "!");
    }

    public BooleanOperator(String... symbols) {
        for (String symbol : symbols) {
            acceptableSymbols.add(symbol);
        }
    }

    @Override
    public boolean checkSymbol(Token token) {
        this.setSymbol(token.toString());
        return super.checkSymbol(token);
    }
}
