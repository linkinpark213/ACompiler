package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class Program extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        ArrayList<V> production = new ArrayList<V>();
        production.add(new StatementString());
        production.add(new Separator(";"));
        productions.add(production);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
