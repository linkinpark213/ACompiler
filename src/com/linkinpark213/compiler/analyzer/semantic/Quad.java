package com.linkinpark213.compiler.analyzer.semantic;

/**
 * Created by ooo on 2017/7/15 0015.
 */
public class Quad {
    private int address;
    private String operator;
    private String variableA;
    private String variableB;
    private String result;

    public Quad() {
        this.setAddress(0);
        this.setResult("0");
    }

    public Quad(int address, String operator, String variableA, String variableB, String result) {
        this.address = address;
        this.operator = operator;
        this.variableA = variableA;
        this.variableB = variableB;
        this.result = result;
    }

    public Quad(String operator, String variableA, String variableB, String result) {
        this(0, operator, variableA, variableB, result);
    }

    public String toString() {
        return address + "\t\t(\t" + operator + ",\t\t" + variableA + ",\t\t" + variableB + ",\t\t" + result + "\t)";
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
