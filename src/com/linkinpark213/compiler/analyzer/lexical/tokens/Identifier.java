package com.linkinpark213.compiler.analyzer.lexical.tokens;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Identifier implements Token {
    public String symbol;
    private int row;
    private int column;
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

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String getName() {
        return symbol;
    }

    @Override
    public String getFullTypeString() {
        return "Identifier";
    }
}
