package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Separator extends VT {
    public Separator() {
        acceptableSymbols.add("{");
        acceptableSymbols.add("}");
        acceptableSymbols.add("(");
        acceptableSymbols.add(")");
        acceptableSymbols.add(":");
        acceptableSymbols.add(";");
        acceptableSymbols.add("\'");
    }
}
