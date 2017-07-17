package com.linkinpark213.compiler.error.semantic;

import com.linkinpark213.compiler.error.semantic.SemanticError;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class IdentifierNotDefinedError extends SemanticError {
    private String identifier;

    public IdentifierNotDefinedError(int line, int column, String identifier) {
        super(line, column);
        this.identifier = identifier;
    }

    public IdentifierNotDefinedError(int line, int column) {
        super(line, column);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Identifier '" + identifier + "' is undefined when accessed.";
    }
}
