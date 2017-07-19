package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class ArithmeticExpressionAlter extends VN {
    private int type;
    public ArithmeticExpressionAlter() {
        super();
        this.type = 0;
        this.nullable = true;
    }

    public void printExpression() {
        if (children.size() == 0) return;
        ArithmeticOperator operator = (ArithmeticOperator) children.get(0);
        ArithmeticExpression arithmeticExpression = (ArithmeticExpression) children.get(1);
        System.out.print(operator.toString());
        System.out.print(" ");
        arithmeticExpression.printExpression();
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Arithmetic Expression Alter> ::= <Arithmetic Operator> <Arithmetic Expression> | Null
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new ArithmeticOperator("+", "-", "*"));
        production.add(new ArithmeticExpression());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
        if (children.size() > 0) {
            ArithmeticExpression arithmeticExpression = (ArithmeticExpression) children.get(1);
            this.setType(arithmeticExpression.getType());
            this.variableName = arithmeticExpression.getVariableName();
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}