package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class CaseBlockString extends VN {
    @Override
    public boolean analyze(ArrayList<Token> tokenQueue) {
        /*
        * <Case Block String> ::= <Case Block> ; <Case Block String>
        *                       | <Case Block>
        * */
        ArrayList<V> moreCaseProduction = new ArrayList<V>();
        ArrayList<V> noMoreCaseProduction = new ArrayList<V>();
        moreCaseProduction.add(new CaseBlock());
        moreCaseProduction.add(new Separator(";"));
        moreCaseProduction.add(new CaseBlockString());
        noMoreCaseProduction.add(new CaseBlock());
        productions.add(moreCaseProduction);
        productions.add(noMoreCaseProduction);
        return super.analyze(tokenQueue);
    }
}
