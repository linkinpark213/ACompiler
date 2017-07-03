package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BooleanExpression implements Expression {
    private int type;
    private boolean value;
    private ArrayList<V> children;

    public void setType(int type) {
        this.type = type;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public void analyze() {

    }

    @Override
    public int getType() {
        return Constant.TYPE_BOOL;
    }

    @Override
    public ArrayList<V> getChildren() {
        return children;
    }

    @Override
    public int getValue() {
        return value ? 1 : 0;
    }

    @Override
    public void addChild() {

    }
}
