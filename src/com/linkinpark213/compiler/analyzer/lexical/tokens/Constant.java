package com.linkinpark213.compiler.analyzer.lexical.tokens;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Constant implements Token {
    private String symbol;
    private int type;
    private float value;
    private int row;
    private int col;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;
    public static final ArrayList<String> boolConstants;

    static {
        boolConstants = new ArrayList<String>();
        boolConstants.add("true");
        boolConstants.add("false");
    }

    public Constant(String symbol, int type) {
        this.symbol = symbol;
        this.type = type;
        switch (type) {
            case TYPE_INT:
                value = Integer.parseInt(symbol);
                break;
            case TYPE_FLOAT:
                value = Float.parseFloat(symbol);
                break;
            case TYPE_CHAR:
                value = symbol.charAt(0);
                break;
            case TYPE_BOOL:
                if (symbol.equals("true"))
                    value = 1;
                else value = 0;
        }
    }

    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    @Override
    public String fullString() {
        return "Constant value (" + getTypeString() + "): " + symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getTypeString() {
        String typeString = "";
        switch (type) {
            case TYPE_INT:
                typeString = "Integer";
                break;
            case TYPE_FLOAT:
                typeString = "Real";
                break;
            case TYPE_CHAR:
                typeString = "Character";
                break;
            case TYPE_BOOL:
                typeString = "Boolean";
        }
        return typeString;
    }

    public float getValue() {
        return value;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return col;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setColumn(int column) {
        this.col = column;
    }

    @Override
    public String getName() {
        return symbol;
    }

    @Override
    public String getFullTypeString() {
        return "Constant (" + getTypeString() + ")";
    }
}
