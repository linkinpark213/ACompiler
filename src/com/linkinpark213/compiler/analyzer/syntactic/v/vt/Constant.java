package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Constant extends VT {
    private com.linkinpark213.compiler.analyzer.lexical.tokens.Constant constant;
    public static final int TYPE_INT = 100;
    public static final int TYPE_FLOAT = 101;
    public static final int TYPE_CHAR = 102;
    public static final int TYPE_BOOL = 103;

    @Override
    public boolean checkSymbol(Token token) {
        if (token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Constant) {
            constant = (com.linkinpark213.compiler.analyzer.lexical.tokens.Constant) token;
            return true;
        } else return false;
    }


    public float getValue() {
        return constant.getValue();
    }

    public int getType() {
        return constant.getType();
    }

    public String getTypeString() {
        return constant.getTypeString();
    }

    @Override
    public String toString() {
        return "Constant (" + constant.getTypeString() + ") : " + constant.getValue();
    }
}
