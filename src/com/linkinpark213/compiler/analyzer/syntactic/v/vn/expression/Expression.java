package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class Expression extends VN {
    protected int type;
    protected int value;

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public Expression() {
        /*
        * <Expression> ::= <Boolean Expression>
        *                   | <Relation Expression>
        *                   | <Arithmetic Expression>
        * */
        super();
        ArrayList<V> booleanProduction = new ArrayList<V>();
        ArrayList<V> relationProduction = new ArrayList<V>();
        ArrayList<V> arithmeticProduction = new ArrayList<V>();
        booleanProduction.add(new BooleanExpression());
        relationProduction.add(new RelationExpression());
        arithmeticProduction.add(new ArithmeticExpression());
        productions.add(booleanProduction);
        productions.add(relationProduction);
        productions.add(arithmeticProduction);
    }
}
