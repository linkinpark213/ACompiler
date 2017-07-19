package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.*;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ArgumentList;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.IdentifierNotDefinedError;
import com.linkinpark213.compiler.error.semantic.ParameterTypeDoesNotMatchError;
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
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        Identifier identifier = (Identifier) children.get(1);
        ArgumentList argumentList = (ArgumentList) children.get(3);
        argumentList.semanticAction(quadQueue, symbolList);
        ArrayList<String> argumentNameList = new ArrayList<String>();
        argumentList.getArgumentNameList(argumentNameList);
        Function function = symbolList.retrieveFunction(identifier.getName());
        int[] argumentTypeList = argumentList.getArgumentTypeList();
        if (argumentTypeList != null) {
            if (function == null)
                throw new IdentifierNotDefinedError(identifier.getToken().getRow(),
                        identifier.getToken().getColumn(), identifier.getName());
            int[] expectedTypeList = function.getParameterTypes();
            for (int i = 0; i < argumentTypeList.length; i++) {
                String argumentName = argumentNameList.get(i);
                if (argumentTypeList[i] > expectedTypeList[i]) {
                    throw new ParameterTypeDoesNotMatchError(identifier.getToken().getRow(),
                            identifier.getToken().getColumn(), Symbol.typeCodeToString(expectedTypeList[i]),
                            Symbol.typeCodeToString(argumentTypeList[i]));
                }
                Quad parQuad = new Quad("par", "_", "_", argumentName);
                quadQueue.add(parQuad);
            }
        }
        Quad callQuad = new Quad("call", "_", "_", identifier.getName());
        quadQueue.add(callQuad);
    }
}
