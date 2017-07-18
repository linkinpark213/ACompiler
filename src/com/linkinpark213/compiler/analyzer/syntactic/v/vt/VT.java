package com.linkinpark213.compiler.analyzer.syntactic.v.vt;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public abstract class VT implements V, Cloneable {
    public static final int CONSTANT_INT = 102;
    public static final int CONSTANT_FLOAT = 103;
    public static final int CONSTANT_CHAR = 101;
    public static final int CONSTANT_BOOL = 100;
    public static final int IDENTIFIER = 104;
    public static final int KEYWORD = 105;
    public static final int OPERATOR_ARITHMETIC = 106;
    public static final int OPERATOR_RELATION = 107;
    public static final int OPERATOR_BOOL = 108;
    public static final int OPERATOR_ASSIGNMENT = 109;
    public static final int TYPE_SEPARATOR = 110;
    protected ArrayList<String> acceptableSymbols;
    private Token token;

    public VT() {
        acceptableSymbols = new ArrayList<String>();
    }

    public abstract boolean checkSymbol(Token token, SymbolList symbolList) throws SemanticError;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public VT getClone() {
        try {
            return (VT) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void printSyntacticalAnalysisTree(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t\t");
        }
        System.out.println(this.toString());
    }

    public String toExactString() {
        return toString();
    }
}
