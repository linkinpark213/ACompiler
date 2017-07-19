package com.linkinpark213.compiler.error.semantic;

public class ParameterNumberError extends SemanticError {
    public ParameterNumberError(int line, int column) {
        super(line, column);
    }

    public ParameterNumberError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Wrong number of parameters upon calling of function '" + message + "'";
    }
}
