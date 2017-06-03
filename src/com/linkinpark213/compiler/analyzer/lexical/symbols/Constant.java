package com.linkinpark213.compiler.analyzer.lexical.symbols;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Constant implements Symbol {
    private String symbol;
    private int type;
    private float value;
    public static final int TYPE_INT = 0;
    public static final int TYPE_FLOAT = 1;
    public static final int TYPE_CHAR = 2;
    public static final int TYPE_BOOL = 3;
    public static final ArrayList<String> boolConstants;
    static  {
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

    @Override
    public String fullString() {
        return "Constant value: " + symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
