package com.linkinpark213.compiler.analyzer.lexical.symbols;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Operator implements Symbol {
    private String symbol;

    public String fullString() {
        return "Operator: " + symbol;
    }

    public Operator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
