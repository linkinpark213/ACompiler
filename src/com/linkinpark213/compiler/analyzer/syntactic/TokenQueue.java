package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class TokenQueue {
    private ArrayList<Token> tokens;
    private int farthestTokenNum;
    private int totalTokenCount;
    private String farthestExpectation;

    public TokenQueue() {
        tokens = new ArrayList<Token>();
        totalTokenCount = 0;
        farthestTokenNum = 0;
    }

    public TokenQueue(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.farthestTokenNum = 0;
        this.totalTokenCount = tokens.size();
    }

    public int size() {
        return tokens.size();
    }

    public Token get(int index) {
        return tokens.get(index);
    }

    public Token remove(int index) {
        return tokens.remove(index);
    }

    public void add(Token token) {
        tokens.add(token);
    }

    public void add(int index, Token token) {
        tokens.add(index, token);
    }

    public int getFarthestTokenNum() {
        return farthestTokenNum;
    }

    public void setFarthestTokenNum(int farthestTokenNum) {
        this.farthestTokenNum = farthestTokenNum;
    }

    public int getTotalTokenCount() {
        return totalTokenCount;
    }

    public void setTotalTokenCount(int totalTokenCount) {
        this.totalTokenCount = totalTokenCount;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public String getFarthestExpectation() {
        return farthestExpectation;
    }

    public void setFarthestExpectation(String farthestExpectation) {
        this.farthestExpectation = farthestExpectation;
    }
}
