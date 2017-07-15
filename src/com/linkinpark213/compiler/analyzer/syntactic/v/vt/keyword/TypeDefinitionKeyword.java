package com.linkinpark213.compiler.analyzer.syntactic.v.vt.keyword;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class TypeDefinitionKeyword extends Keyword {
    public TypeDefinitionKeyword() {
        acceptableSymbols.add("int");
        acceptableSymbols.add("float");
        acceptableSymbols.add("char");
        acceptableSymbols.add("bool");
    }

    @Override
    public boolean checkSymbol(Token token) {
        this.setValue(token.toString());
        return super.checkSymbol(token);
    }
}
