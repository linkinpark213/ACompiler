package com.linkinpark213.compiler.error.semantic;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class TypeDoesNotMatchError extends SemanticError {
    public TypeDoesNotMatchError(int line, int column) {
        super(line, column);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
