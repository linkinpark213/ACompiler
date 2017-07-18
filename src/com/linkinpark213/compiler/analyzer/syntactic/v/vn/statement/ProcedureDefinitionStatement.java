package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
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
    private int returnAddress;

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Procedure Definition Statement> ::= <Typedef Keyword> <Identifier> () { <Statement String> }
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("function"));
        production.add(new Identifier());
        production.add(new Separator("("));
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
            symbolList.enterFunction(new Symbol(identifier.getName(), "function"));
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
    public void semanticAction(QuadQueue quadQueue) {
//        super.semanticAction(quadQueue);
        StatementString statementString = (StatementString) productions.get(0).get(5);
        Quad jumpAcrossQuad = new Quad("j", "_", "_", "0");
        quadQueue.add(jumpAcrossQuad);
        address = jumpAcrossQuad.getAddress() + 1;
        statementString.semanticAction(quadQueue);
        jumpAcrossQuad.setResult("" + quadQueue.nxq());

    }
}
