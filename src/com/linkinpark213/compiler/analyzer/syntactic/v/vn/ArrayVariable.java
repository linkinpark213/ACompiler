package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class ArrayVariable extends VN {
    private int[] dimensions;

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Array Variable> ::= <Identifier> [ <Index List> ]
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Identifier());
        production.add(new Separator("["));
        production.add(new IndexList());
        production.add(new Separator("]"));
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        Identifier identifier = (Identifier) children.get(0);
        IndexList indexList = (IndexList) children.get(2);
        ArrayList<String> variableNamesList = new ArrayList<String>();
        indexList.getIndexNameList(variableNamesList);
        String temp = "";
//        I need a table to record the dimensions of this array
        for (String name : variableNamesList) {

        }
    }
}
