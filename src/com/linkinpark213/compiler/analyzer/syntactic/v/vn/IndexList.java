package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.ArithmeticExpression;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class IndexList extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Index List> ::= <Arithmetic Expression> <Index List Alter>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new ArithmeticExpression());
        production.add(new IndexListAlter());
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
    }

    public void getIndexNameList(ArrayList<String> nameList) {
        ArithmeticExpression arithmeticExpression = (ArithmeticExpression) children.get(0);
        nameList.add(arithmeticExpression.getVariableName());
        IndexListAlter indexListAlter = (IndexListAlter) children.get(1);
        indexListAlter.getIndexNameList(nameList);
    }
}
