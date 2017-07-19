package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
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
        * <Argument List Alter> ::= , <Argument List>
        *                           | nothing
        * */
        ArrayList<V> moreExpressionProduction = new ArrayList<V>();
        ArrayList<V> noMoreProduction = new ArrayList<V>();
        moreExpressionProduction.add(new Separator(","));
        moreExpressionProduction.add(new ArgumentList());

        productions.add(moreExpressionProduction);
        productions.add(noMoreProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
    }

    public void getArgumentNameList(ArrayList<String> tempList) {
        if (children.size() > 0) {
            ArgumentList argumentList = (ArgumentList) children.get(1);
            argumentList.getArgumentNameList(tempList);
        }
    }

    public int[] getArgumentTypeList() {
        if (children.size() > 0) {
            ArgumentList argumentList = (ArgumentList) children.get(1);
            return argumentList.getArgumentTypeList();
        }
        return null;
    }
}
