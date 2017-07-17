package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class Program extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) {
        ArrayList<V> production = new ArrayList<V>();
        production.add(new StatementString());
        production.add(new Separator(";"));
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }
}
