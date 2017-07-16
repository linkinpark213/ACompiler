package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class CaseBlock extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        *  <Case Block> ::= case <Constant> : <Statement String>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("case"));
        production.add(new Constant());
        production.add(new Separator(":"));
        production.add(new StatementString());
        productions.add(production);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
