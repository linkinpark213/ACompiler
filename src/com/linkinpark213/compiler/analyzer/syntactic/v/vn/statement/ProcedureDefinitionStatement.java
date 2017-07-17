package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
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
        return super.analyze(tokenQueue, symbolList);
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
    }
}
