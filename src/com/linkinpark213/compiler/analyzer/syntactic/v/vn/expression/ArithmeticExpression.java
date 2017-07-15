package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class ArithmeticExpression extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue) {
        /*
        * <Arithmetic Expression> ::= ( <Arithmetic Expression> ) <Alter>
        *                           | <Identifier> <Increment/Decrement Operator>
        *                           | <Identifier> <Alter>
        *                           | <Constant> <Alter>
        * */
        ArrayList<V> expressionWithBracketsProduction = new ArrayList<V>();
        ArrayList<V> singleIdentifierProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();
        ArrayList<V> crementProduction = new ArrayList<V>();
        expressionWithBracketsProduction.add(new Separator("("));
        expressionWithBracketsProduction.add(new ArithmeticExpression());
        expressionWithBracketsProduction.add(new Separator(")"));
        expressionWithBracketsProduction.add(new ArithmeticExpressionAlter());
        crementProduction.add(new Identifier());
        crementProduction.add(new ArithmeticOperator("++", "--"));
        singleIdentifierProduction.add(new Identifier());
        singleIdentifierProduction.add(new ArithmeticExpressionAlter());
        constantProduction.add(new Constant());
        constantProduction.add(new ArithmeticExpressionAlter());
        productions.add(expressionWithBracketsProduction);
        productions.add(crementProduction);
        productions.add(singleIdentifierProduction);
        productions.add(constantProduction);
        return super.analyze(parent, tokenQueue);
    }
}
