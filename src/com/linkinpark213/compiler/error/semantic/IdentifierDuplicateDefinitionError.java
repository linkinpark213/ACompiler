package com.linkinpark213.compiler.error.semantic;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class IdentifierDuplicateDefinitionError extends SemanticError {
    public IdentifierDuplicateDefinitionError(int line, int column) {
        super(line, column);
    }

    public IdentifierDuplicateDefinitionError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Duplicate definition of identifier '" + message + "'";
    }
}
