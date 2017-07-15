package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class ArithmeticExpressionAlter extends VN {
    public ArithmeticExpressionAlter() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Arithmetic Expression Alter> ::= <Arithmetic Operator> <Expression> | 3
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new ArithmeticOperator("+", "-", "*"));
        production.add(new Expression());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}