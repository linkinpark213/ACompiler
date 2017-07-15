package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class AssignmentOperator extends Operator {
    public AssignmentOperator() {
        acceptableSymbols.add(":=");
        this.setSymbol(":=");
    }
}
