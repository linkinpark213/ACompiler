package com.linkinpark213.compiler.analyzer.lexical.symbols;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Identifier implements Symbol {
    public String symbol;
    public static final int TYPE_IDENTIFIER = 104;

    public Identifier(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String fullString() {
        return "Identifier: " + symbol;
    }

    public String toString() {
        return symbol;
    }

    @Override
    public int getType() {
        return TYPE_IDENTIFIER;
    }
}
