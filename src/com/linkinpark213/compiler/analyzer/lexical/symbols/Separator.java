package com.linkinpark213.compiler.analyzer.lexical.symbols;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Separator implements Symbol {
    private char symbol;
    private int row;
    private int column;
    private static final char[] separators = {'{', '}', '(', ')', ';', ','};
    public static final int TYPE_SEPARATOR = 110;

    public Separator(char symbol) {
        this.symbol = symbol;
    }

    public static boolean isSeparator(char c) {
        for (int i = 0; i < separators.length; i++) {
            if (c == separators[i]) return true;
        }
        return false;
    }

    @Override
    public String fullString() {
        return "Separator: " + symbol;
    }

    @Override
    public String toString() {
        return "" + symbol;
    }

    @Override
    public int getType() {
        return TYPE_SEPARATOR;
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
}
