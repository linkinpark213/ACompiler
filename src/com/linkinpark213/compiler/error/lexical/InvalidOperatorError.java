package com.linkinpark213.compiler.error.lexical;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class InvalidOperatorError extends InvalidSymbolError {
    public InvalidOperatorError(String symbol) {
        super(symbol);
    }

    @Override
    public String getMessage() {
        return "Invalid Operator: " + symbol;
    }
}
