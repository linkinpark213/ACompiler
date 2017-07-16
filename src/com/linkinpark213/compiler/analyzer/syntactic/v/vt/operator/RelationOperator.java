package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class RelationOperator extends Operator {
    public RelationOperator() {
        acceptableSymbols.add(">");
        acceptableSymbols.add("<");
        acceptableSymbols.add(">=");
        acceptableSymbols.add("<=");
        acceptableSymbols.add("=");
        acceptableSymbols.add("!=");
    }

    @Override
    public boolean checkSymbol(Token token, SymbolList symbolList) {
        this.setSymbol(token.toString());
        return super.checkSymbol(token, symbolList);
    }
}
