package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.*;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ParameterDefinitionString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class ProcedureDefinitionStatement extends VN {
    private int address;

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Procedure Definition Statement> ::= <Typedef Keyword> <Identifier> ( <Parameter Definition String> ) { <Statement String> }
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("function"));
        production.add(new Identifier());
        production.add(new Separator("("));
        production.add(new ParameterDefinitionString());
        production.add(new Separator(")"));
        production.add(new Separator("{"));
        production.add(new StatementString());
        production.add(new Separator("}"));
        productions.add(production);
        symbolList.openScope();
        boolean finished = super.analyze(tokenQueue, symbolList);
        symbolList.closeScope();
        if (finished) {
            Identifier identifier = (Identifier) children.get(1);
            ParameterDefinitionString parameterDefinitionString = (ParameterDefinitionString) children.get(3);
            symbolList.enterFunction(new Symbol(identifier.getName(), "function"),
                    parameterDefinitionString.getParameterTypes());
        }
        return finished;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
        StatementString statementString = (StatementString) productions.get(0).get(6);
        Quad jumpAcrossQuad = new Quad("j", "_", "_", "0");
        quadQueue.add(jumpAcrossQuad);
        address = jumpAcrossQuad.getAddress() + 1;
        statementString.semanticAction(quadQueue, symbolList);
        jumpAcrossQuad.setResult("" + quadQueue.nxq());
        Quad returnQuad = new Quad("ret", "_", "_", "_");
        quadQueue.add(returnQuad);
    }
}
