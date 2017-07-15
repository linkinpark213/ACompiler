package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.BooleanOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BooleanExpression extends VN {

    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Boolean Expression> ::= <Relation Expression> <Alter>
        *                           | <'Not' Operator> <Identifier> <Alter>
        *                           | <'Not' Operator> ( <Boolean Expression> ) <Alter>
        *                           | <Boolean Constant> <Alter>
        *                           | <Identifier> <Alter> (Not now)
        * */
        ArrayList<V> relationExpressionProduction = new ArrayList<V>();
        ArrayList<V> identifierWithNotOperatorProduction = new ArrayList<V>();
        ArrayList<V> expressionWithNotOperatorProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();
        ArrayList<V> identifierWithDoubleOperatorProduction = new ArrayList<V>();

        relationExpressionProduction.add(new RelationExpression());
        relationExpressionProduction.add(new BooleanExpressionAlter());

        identifierWithNotOperatorProduction.add(new BooleanOperator("!"));
        identifierWithNotOperatorProduction.add(new Identifier());
        identifierWithNotOperatorProduction.add(new BooleanExpressionAlter());

        expressionWithNotOperatorProduction.add(new BooleanOperator("!"));
        expressionWithNotOperatorProduction.add(new Separator("("));
        expressionWithNotOperatorProduction.add(new BooleanExpression());
        expressionWithNotOperatorProduction.add(new Separator(")"));
        expressionWithNotOperatorProduction.add(new BooleanExpressionAlter());

        constantProduction.add(new Constant());
        constantProduction.add(new BooleanExpressionAlter());

        identifierWithDoubleOperatorProduction.add(new Identifier());
        identifierWithDoubleOperatorProduction.add(new BooleanExpressionAlter());

        productions.add(relationExpressionProduction);
        productions.add(identifierWithNotOperatorProduction);
        productions.add(expressionWithNotOperatorProduction);
        productions.add(constantProduction);
//        productions.add(identifierWithDoubleOperatorProduction);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
