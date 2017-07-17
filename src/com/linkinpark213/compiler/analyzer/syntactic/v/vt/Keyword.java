package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class Keyword extends VT {
    private String value;

    public Keyword(String... symbols) {
        super();
        for (String symbol : symbols) {
            acceptableSymbols.add(symbol);
        }
    }

    @Override
    public boolean checkSymbol(Token token, SymbolList symbolList) {
        this.setValue(token.toString());
        for (int i = 0; i < acceptableSymbols.size(); i++) {
            if (acceptableSymbols.get(i).equals(token.toString()))
                return true;
        }
        return false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String toExactString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < acceptableSymbols.size(); i++) {
            if (i != 0)
                if (i == acceptableSymbols.size() - 1) {
                    stringBuilder.append(" or ");
                } else {
                    stringBuilder.append(", ");
                }
            stringBuilder.append("'");
            stringBuilder.append(acceptableSymbols.get(i));
            stringBuilder.append("'");
        }
        return "keyword " + stringBuilder.toString();
    }
}
