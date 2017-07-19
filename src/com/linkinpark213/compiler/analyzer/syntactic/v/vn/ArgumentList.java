package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
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
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
    }

    public void getArgumentNameList(ArrayList<String> tempList) {
        if (children.size() > 0) {
            Expression expression = (Expression) children.get(0);
            ArgumentListAlter argumentListAlter = (ArgumentListAlter) children.get(1);
            tempList.add(expression.getVariableName());
            argumentListAlter.getArgumentNameList(tempList);
        }
    }

    public int[] getArgumentTypeList() {
        if (children.size() > 0) {
            Expression expression = (Expression) children.get(0);
            ArgumentListAlter argumentListAlter = (ArgumentListAlter) children.get(1);
            int[] afterList = argumentListAlter.getArgumentTypeList();
            int[] result;
            if (afterList != null) {
                result = new int[afterList.length + 1];
                for (int i = 0; i < afterList.length; i++) {
                    result[i + 1] = afterList[i];
                }
            } else {
                result = new int[1];
            }
            result[0] = expression.getType();
            return result;
        }
        return null;
    }
}
