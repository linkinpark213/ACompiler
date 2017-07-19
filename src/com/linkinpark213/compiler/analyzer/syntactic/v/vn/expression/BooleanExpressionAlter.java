package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.BooleanOperator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

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
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Boolean Expression Alter> ::= <'And' or 'Or' Boolean Operator> <Expression>
        *                               | Nothing
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new BooleanOperator("&", "|"));
        production.add(new BooleanExpression());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
        if (children.size() > 0) {
            this.variableName = ((BooleanExpression) children.get(1)).getVariableName();
        }
    }
}
