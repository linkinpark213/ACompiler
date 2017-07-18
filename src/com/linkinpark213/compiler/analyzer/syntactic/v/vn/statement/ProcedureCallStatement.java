package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ArgumentList;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class ProcedureCallStatement extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Procedure Call Statement> ::= call <Identifier> ( <Argument List> )
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("call"));
        production.add(new Identifier());
        production.add(new Separator("("));
        production.add(new ArgumentList());
        production.add(new Separator(")"));
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        Identifier identifier = (Identifier) productions.get(0).get(1);
        ArgumentList argumentList = (ArgumentList) productions.get(0).get(3);
        argumentList.semanticAction(quadQueue);
        ArrayList<String> argumentNameList = new ArrayList<String>();
        argumentList.getArgumentNameList(argumentNameList);
        for (String argumentName : argumentNameList) {
            Quad parQuad = new Quad("par", "_", "_", argumentName);
            quadQueue.add(parQuad);
        }
        Quad callQuad = new Quad("call", "_", "_", identifier.getName());
        quadQueue.add(callQuad);
    }
}
