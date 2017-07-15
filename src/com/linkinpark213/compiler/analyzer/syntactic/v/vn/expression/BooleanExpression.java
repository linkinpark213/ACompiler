package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.BooleanOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BooleanExpression extends VN {

    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue) {
        /*
        * <Boolean Expression> ::= <Identifier> <'And' or 'Or' Boolean Operator> <Expression>
        *                           | <Identifier>
        *                           | <'Not' Operator> <Identifier>
        *                           | <'Not' Operator> ( <Boolean Expression> )\
        *                           | <Constant>
        * */
        ArrayList<V> identifierWithDoubleOperatorProduction = new ArrayList<V>();
        ArrayList<V> singleIdentifierProduction = new ArrayList<V>();
        ArrayList<V> identifierWithSingleOperatorProduction = new ArrayList<V>();
        ArrayList<V> expressionWithNotOperatorProduction = new ArrayList<V>();
        singleIdentifierProduction.add(new Identifier());
        identifierWithDoubleOperatorProduction.add(new Identifier());
        identifierWithDoubleOperatorProduction.add(new BooleanOperator());
        identifierWithDoubleOperatorProduction.add(new Expression());
        identifierWithSingleOperatorProduction.add(new BooleanOperator());
        identifierWithSingleOperatorProduction.add(new Identifier());
        expressionWithNotOperatorProduction.add(new BooleanOperator());
        expressionWithNotOperatorProduction.add(new Separator());
        expressionWithNotOperatorProduction.add(new BooleanExpression());
        expressionWithNotOperatorProduction.add(new Separator());
        productions.add(singleIdentifierProduction);
        productions.add(identifierWithDoubleOperatorProduction);
        productions.add(identifierWithSingleOperatorProduction);
        productions.add(expressionWithNotOperatorProduction);
        return super.analyze(parent, tokenQueue);
    }
}
