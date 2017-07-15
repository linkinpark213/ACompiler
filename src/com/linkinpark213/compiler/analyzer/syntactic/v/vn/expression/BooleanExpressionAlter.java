package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.BooleanOperator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.Operator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/15 0015.
 */
public class BooleanExpressionAlter extends VN {
    public BooleanExpressionAlter() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Boolean Expression Alter> ::= <'And' or 'Or' Boolean Operator> <Expression>
        *                               | Nothing
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new BooleanOperator("&", "|"));
        production.add(new Expression());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
