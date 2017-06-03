package com.linkinpark213.compiler.analyzer.lexical.exception;

/**
 * Created by ooo on 2017/6/3 0003.
 */
public class InvalidSymbolException extends Exception {
    private String message;

    public InvalidSymbolException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
