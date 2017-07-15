package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BranchStatement extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue) {
        /*
        * <Branch Statement> ::= if <Relation Expression> then { <Statement Sting> }
        *
        * */
        ArrayList<V> production = new ArrayList<V>();
        productions.add(production);
        return super.analyze(parent, tokenQueue);
    }
}
