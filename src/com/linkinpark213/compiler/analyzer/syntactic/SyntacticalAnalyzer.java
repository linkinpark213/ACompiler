package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class SyntacticalAnalyzer {
    public Program analyze(ArrayList<Token> tokenQueue, SymbolList symbolList) {
        Program root = new Program();
        if (!root.analyze(tokenQueue, symbolList) || tokenQueue.size() != 0) {
            Token token = tokenQueue.get(1);
            System.out.println("Syntax Error at Row " + token.getRow() + ", Column " + token.getColumn());
        }
        return root;
    }
}
