package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Identifier extends VT {
    private String name;
    private int type;
    public static final int TYPE_INT = 100;
    public static final int TYPE_FLOAT = 101;
    public static final int TYPE_CHAR = 102;
    public static final int TYPE_BOOL = 103;

    public Identifier() {
        this(0);
    }

    public Identifier(int type) {
        this.type = type;
    }

    @Override
    public boolean checkSymbol(Token token, SymbolList symbolList) {
        boolean isIdentifier = token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Identifier;
        if (isIdentifier) {
            this.setName(token.toString());
            if (type != 0) {
                Symbol symbol = symbolList.retrieveSymbol(token.toString());
                if (symbol == null) {
                    return false;
                } else if (symbol.getType() != type) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        }
        return typeString;
    }

    @Override
    public String toString() {
        return "Identifier: " + name + " (" + getTypeString() + ")";
    }
}
