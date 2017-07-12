package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class ArithmeticExpression extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Symbol> symbolQueue) {
        /*
        * <Arithmetic Expression> ::= <Identifier> <Arithmetic Operator> <Arithmetic Expression>
        *                           | <Identifier>
        *                           | <Constant>
        *                           | ( <Arithmetic Expression> )
        * */
        ArrayList<V> identifierWithOperatorProduction = new ArrayList<V>();
        ArrayList<V> singleIdentifierProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();
        identifierWithOperatorProduction.add(new Identifier());
        identifierWithOperatorProduction.add(new ArithmeticOperator());
        identifierWithOperatorProduction.add(new ArithmeticExpression());
        singleIdentifierProduction.add(new Identifier());
        constantProduction.add(new Constant());
        productions.add(identifierWithOperatorProduction);
        productions.add(singleIdentifierProduction);
        productions.add(constantProduction);
        return super.analyze(parent, symbolQueue);
    }
}
