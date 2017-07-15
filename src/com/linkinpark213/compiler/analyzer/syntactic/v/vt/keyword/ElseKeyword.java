package com.linkinpark213.compiler.analyzer.syntactic.v.vt.keyword;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class ElseKeyword extends Keyword {
    public ElseKeyword() {
        acceptableSymbols.add("else");
        this.setValue("else");
    }
}
