package com.linkinpark213.compiler.analyzer.lexical.symbols;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class Operator implements Symbol {
    private String symbol;
    private int type;
    private static final ArrayList<String> arithmeticOperators;
    private static final ArrayList<String> relationOperators;
    private static final ArrayList<String> boolOperators;
    private static final String assignmentOperator = ":=";
    private static final int TYPE_ARITHMETIC = 106;
    private static final int TYPE_RELATION = 107;
    private static final int TYPE_BOOL = 108;
    private static final int TYPE_ASSIGNMENT = 109;

    static {
        arithmeticOperators = new ArrayList<String>();
        relationOperators = new ArrayList<String>();
        boolOperators = new ArrayList<String>();
        arithmeticOperators.add("+");
        arithmeticOperators.add("-");
        arithmeticOperators.add("++");
        arithmeticOperators.add("--");
        arithmeticOperators.add("*");
        relationOperators.add(">");
        relationOperators.add("<");
        relationOperators.add("<=");
        relationOperators.add(">=");
        relationOperators.add("=");
        relationOperators.add("!=");
        boolOperators.add("&");
        boolOperators.add("|");
        boolOperators.add("!");
    }

    public static boolean isOperatorBeginning(char c) {
        for (int i = 0; i < arithmeticOperators.size(); i++) {
            if (arithmeticOperators.get(i).charAt(0) == c) {
                return true;
            }
        }
        for (int i = 0; i < relationOperators.size(); i++) {
            if (relationOperators.get(i).charAt(0) == c) {
                return true;
            }
        }
        for (int i = 0; i < boolOperators.size(); i++) {
            if (boolOperators.get(i).charAt(0) == c) {
                return true;
            }
        }
        return c == ':';
    }

    public String fullString() {
        String typeSring = new String();
        switch (type) {
            case TYPE_ARITHMETIC:
                typeSring = "Arithmetic";
                break;
            case TYPE_BOOL:
                typeSring = "Boolean";
                break;
            case TYPE_RELATION:
                typeSring = "Relation";
                break;
            case TYPE_ASSIGNMENT:
                typeSring = "Assignment";
                break;
        }
        return "Operator (" + typeSring + "): " + symbol;
    }

    public Operator(String symbol) {
        this.symbol = symbol;
        if (arithmeticOperators.contains(symbol)) type = TYPE_ARITHMETIC;
        else if (relationOperators.contains(symbol)) type = TYPE_RELATION;
        else if (boolOperators.contains(symbol)) type = TYPE_BOOL;
        else if (symbol.equals(":=")) type = TYPE_ASSIGNMENT;
        else {
            System.out.println("Compiler Error: Invalid operator type.");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public int getType() {
        return type;
    }
}
