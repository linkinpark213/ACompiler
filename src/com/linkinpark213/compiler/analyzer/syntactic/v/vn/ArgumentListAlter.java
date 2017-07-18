package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class ArgumentListAlter extends VN {
    public ArgumentListAlter() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Argument List Alter> ::= , <Expression>
        *                           | nothing
        * */
        ArrayList<V> moreExpressionProduction = new ArrayList<V>();
        ArrayList<V> noMoreProduction = new ArrayList<V>();
        moreExpressionProduction.add(new Separator(","));
        moreExpressionProduction.add(new Expression());

        productions.add(moreExpressionProduction);
        productions.add(noMoreProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
    }

    public void getArgumentNameList(ArrayList<String> tempList) {
        if (children.size() > 0) {
            Expression expression = (Expression) children.get(1);
            tempList.add(expression.getVariableName());
        }
    }
}
