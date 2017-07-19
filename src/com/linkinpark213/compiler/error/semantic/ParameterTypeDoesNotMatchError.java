package com.linkinpark213.compiler.error.semantic;

public class ParameterTypeDoesNotMatchError extends SemanticError {
    private String expectedType;
    private String foundType;

    public ParameterTypeDoesNotMatchError(int line, int column) {
        super(line, column);
    }

    public ParameterTypeDoesNotMatchError(int line, int column, String message) {
        super(line, column, message);
    }

    public ParameterTypeDoesNotMatchError(int line, int column, String expectedType, String foundType) {
        super(line, column);
        this.expectedType = expectedType;
        this.foundType = foundType;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Parameter type does not match. Expecting '"
                + expectedType + "', but found '" + foundType + "'";
    }
}
