package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;
import com.linkinpark213.compiler.error.AnalysisError;
import com.linkinpark213.compiler.error.syntactical.SyntaxError;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class SyntacticalAnalyzer {
    public Program analyze(TokenQueue tokenQueue, SymbolList symbolList) throws AnalysisError {
        Program root = new Program();
        if (!root.analyze(tokenQueue, symbolList) || tokenQueue.size() != 0) {
            try {
                Token token = tokenQueue.get(tokenQueue.getFarthestTokenNum() + tokenQueue.size() - tokenQueue.getTotalTokenCount());
                throw new SyntaxError(token.getRow(), token.getColumn(), "Expected "
                        + tokenQueue.getFarthestExpectation() +
                        ", but found " + token.toString());
            } catch (IndexOutOfBoundsException e) {
                Token token = tokenQueue.get(tokenQueue.getFarthestTokenNum() - 1);
                throw new SyntaxError(token.getRow(), token.getColumn(), "Expected "
                        + tokenQueue.getFarthestExpectation() + ", but code ended");
            }
        }
        return root;
    }
}
