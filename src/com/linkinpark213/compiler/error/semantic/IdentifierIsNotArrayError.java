package com.linkinpark213.compiler.error.semantic;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class IdentifierIsNotArrayError extends SemanticError {
    public IdentifierIsNotArrayError(int line, int column) {
        super(line, column);
    }

    public IdentifierIsNotArrayError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Identifier '" + message + "' is not an array";
    }
}
