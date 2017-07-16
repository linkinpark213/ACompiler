package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.IdentifierString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class DefinitionStatement extends VN {
    private ArrayList<String> namesList;

    public DefinitionStatement() {
        namesList = new ArrayList<String>();
    }

    @Override
    public boolean analyze(ArrayList<Token> tokenQueue, SymbolList symbolList) {
        /*
         * <Definition Statement> ::= <Typedef Keyword> <Identifier String>
         */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("int", "bool", "float", "char"));
        production.add(new IdentifierString());
        productions.add(production);
        if (super.analyze(tokenQueue, symbolList)) {
            String typeString = ((Keyword) production.get(0)).toString();
            IdentifierString identifierString = (IdentifierString) production.get(1);
            identifierString.getNames(namesList);
            for (String name : namesList) {
                symbolList.enterSymbol(new Symbol(name, typeString));
            }
            return true;
        } else return false;
    }

    @Override
    public void rollBack(ArrayList<Token> tokenQueue, SymbolList symbolList) {
        super.rollBack(tokenQueue, symbolList);
        for (String name : namesList) {
            symbolList.deleteSymbol(name);
        }
    }
}
