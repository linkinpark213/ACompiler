package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class ArgumentList extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Argument List> ::= <Expression> <Argument List Alter>
        *                   | Nothing
        * */
        this.nullable = true;
        ArrayList<V> expressionProduction = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        expressionProduction.add(new Expression());
        expressionProduction.add(new ArgumentListAlter());

        productions.add(expressionProduction);
        productions.add(nullProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
    }

    public void getArgumentNameList(ArrayList<String> tempList) {
        if (children.size() > 0) {
            Expression expression = (Expression) children.get(0);
            ArgumentListAlter argumentListAlter = (ArgumentListAlter) children.get(1);
            tempList.add(expression.getVariableName());
            argumentListAlter.getArgumentNameList(tempList);
        }
    }
}
