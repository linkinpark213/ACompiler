package com.linkinpark213.compiler.error.lexical;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class InvalidIdentifierError extends InvalidSymbolError {
    public InvalidIdentifierError(String symbol) {
        super(symbol);
    }

    @Override
    public String getMessage() {
        return "Invalid Identifier: " + symbol;
    }
}
