package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.error.semantic.IdentifierNotDefinedError;
import com.linkinpark213.compiler.error.semantic.SemanticError;
import com.linkinpark213.compiler.error.semantic.TypeDoesNotMatchError;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Identifier extends VT {
    private String name;
    private int type;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;

    public Identifier() {
        this(0);
    }

    public Identifier(int type) {
        this.type = type;
    }

    @Override
    public boolean checkSymbol(Token token, SymbolList symbolList) throws SemanticError {
        this.setName(token.toString());
        return token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Identifier;
    }

    public boolean checkType(SymbolList symbolList) {
        Symbol symbol = symbolList.retrieveSymbol(getToken().toString());
        return symbol.getType() == type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeString() {
        String typeString = "";
        switch (type) {
            case TYPE_INT:
                typeString = "Integer";
                break;
            case TYPE_FLOAT:
                typeString = "Real";
                break;
            case TYPE_CHAR:
                typeString = "Character";
                break;
            case TYPE_BOOL:
                typeString = "Boolean";
            default:
                typeString = "Not specified";
        }
        return typeString;
    }

    @Override
    public String toString() {
        return "Identifier: " + name;
    }

    @Override
    public String toExactString() {
        return "identifier";
    }
}
