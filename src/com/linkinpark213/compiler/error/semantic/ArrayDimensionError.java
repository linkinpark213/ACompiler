package com.linkinpark213.compiler.error.semantic;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class ArrayDimensionError extends SemanticError {
    public ArrayDimensionError(int line, int column) {
        super(line, column);
    }

    public ArrayDimensionError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Wrong number of dimensions. Array identifier is '" + message + "'";
    }
}
