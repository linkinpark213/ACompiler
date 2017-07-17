package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class IdentifierStringAlter extends VN {
    public IdentifierStringAlter() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) {
        /*
        * <Identifier String Alter> ::= <Comma Separator> <Identifier String>
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new Separator(","));
        production.add(new IdentifierString());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    public void getNames(ArrayList<String> namesList) {
        if (children.size() > 0) {
            IdentifierString identifierString = (IdentifierString) children.get(1);
            identifierString.getNames(namesList);
        }
    }
}
