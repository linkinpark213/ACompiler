package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.syntactic.v.V;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class ArithmeticExpression implements Expression {
    private int type;
    private ArrayList<V> children;

    @Override
    public void analyze() {

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public ArrayList<V> getChildren() {
        return children;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void addChild() {

    }
}
