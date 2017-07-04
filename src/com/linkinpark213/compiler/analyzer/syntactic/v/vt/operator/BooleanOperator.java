package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class BooleanOperator extends VT {
    public BooleanOperator() {
        acceptableSymbols.add("&");
        acceptableSymbols.add("|");
        acceptableSymbols.add("!");
    }
}
