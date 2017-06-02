package com.linkinpark213.compiler.analyzer.lexical.symbols;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Identifier implements Symbol {
    public String symbol;

    public Identifier(String symbol) {
        this.symbol = symbol;
    }

    public String fullString() {
        return "Identifier: " + symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
