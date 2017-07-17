package com.linkinpark213.compiler.error.semantic;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class TypeDoesNotMatchError extends SemanticError {
    private String expectedType;
    private String foundType;

    public TypeDoesNotMatchError(int line, int column) {
        super(line, column);
    }

    public TypeDoesNotMatchError(int line, int column, String expectedType, String foundType) {
        super(line, column);
        this.expectedType = expectedType;
        this.foundType = foundType;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Type doesn't match. Expected '" + expectedType
                + "', but found " + foundType + "'";
    }

    public String getExpectedType() {
        return expectedType;
    }

    public void setExpectedType(String expectedType) {
        this.expectedType = expectedType;
    }
}
