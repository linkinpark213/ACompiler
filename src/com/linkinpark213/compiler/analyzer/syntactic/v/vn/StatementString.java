package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement.*;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class StatementString extends VN {

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) {
        /*
        * <Statement String>  ::=  <Statement> <Statement String Alter>
        *                        | <Statement>
        * */
        ArrayList<V> statementWithStringProduction = new ArrayList<V>();
        ArrayList<V> singleStatementProduction = new ArrayList<V>();
        statementWithStringProduction.add(new Statement());
        statementWithStringProduction.add(new StatementStringAlter());
        singleStatementProduction.add(new Statement());
        productions.add(statementWithStringProduction);
        productions.add(singleStatementProduction);
        return super.analyze(tokenQueue, symbolList);
    }
}
