package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class RelationExpression implements Expression {
    private int type;
    private boolean value;
    private ArrayList<V> children;

    @Override
    public int getType() {
        return Constant.TYPE_BOOL;
    }

    @Override
    public int getValue() {
        return value ? 1 : 0;
    }

    @Override
    public void analyze() {

    }

    @Override
    public ArrayList<V> getChildren() {
        return children;
    }

    @Override
    public void addChild() {

    }
}
