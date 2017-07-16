package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.IdentifierString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class DefinitionStatement extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
         * <Definition Statement> ::= <Typedef Keyword> <Identifier String>
         */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("int", "bool", "float", "char"));
        production.add(new IdentifierString());
        productions.add(production);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
