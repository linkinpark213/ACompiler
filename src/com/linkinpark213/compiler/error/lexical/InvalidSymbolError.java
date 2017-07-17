package com.linkinpark213.compiler.error.lexical;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class InvalidSymbolError extends Exception {
    protected String symbol;

    public InvalidSymbolError(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Invalid Symbol " + symbol;
    }
}
