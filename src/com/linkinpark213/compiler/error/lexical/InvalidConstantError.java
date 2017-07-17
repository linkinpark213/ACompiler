package com.linkinpark213.compiler.error.lexical;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class InvalidConstantError extends InvalidSymbolError {
    public InvalidConstantError(String symbol) {
        super(symbol);
    }

    @Override
    public String getMessage() {
        return "Invalid Constant: " + symbol;
    }
}
