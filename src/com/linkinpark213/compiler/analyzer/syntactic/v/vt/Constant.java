package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class Constant extends VT {
    private int type;
    private com.linkinpark213.compiler.analyzer.lexical.tokens.Constant constant;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;

    public Constant() {
        this(0);
    }

    public Constant(int type) {
        this.type = type;
    }

    @Override
    public boolean checkSymbol(Token token, SymbolList symbolList) {
        boolean isConstant = token instanceof com.linkinpark213.compiler.analyzer.lexical.tokens.Constant;
        if (isConstant) {
            if (this.type > 0)
                if (token.getType() == this.type) {
                    this.constant = (com.linkinpark213.compiler.analyzer.lexical.tokens.Constant) token;
                    return true;
                } else return false;
            else {
                this.constant = (com.linkinpark213.compiler.analyzer.lexical.tokens.Constant) token;
                return true;
            }
        }
        return false;
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
