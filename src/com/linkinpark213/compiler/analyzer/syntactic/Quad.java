package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

/**
 * Created by ooo on 2017/7/15 0015.
 */
public class Quad {
    private int address;
    private Token[] operand;
    private String variableA;
    private String variableB;
    private String result;

    public Quad() {
        this.setAddress(0);
        this.setResult("0");
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public Token[] getOperand() {
        return operand;
    }

    public void setOperand(Token[] operand) {
        this.operand = operand;
    }

    public String getVariableA() {
        return variableA;
    }

    public void setVariableA(String variableA) {
        this.variableA = variableA;
    }

    public String getVariableB() {
        return variableB;
    }

    public void setVariableB(String variableB) {
        this.variableB = variableB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
