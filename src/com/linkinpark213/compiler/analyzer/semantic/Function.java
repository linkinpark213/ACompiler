package com.linkinpark213.compiler.analyzer.semantic;

public class Function {
    private Symbol symbol;
    private int[] parameterTypes;

    public Function(Symbol symbol, int[] parameterTypes) {
        this.symbol = symbol;
        this.parameterTypes = parameterTypes;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(int[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
