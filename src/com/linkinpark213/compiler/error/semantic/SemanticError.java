package com.linkinpark213.compiler.error.semantic;

import com.linkinpark213.compiler.error.AnalysisError;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class SemanticError extends AnalysisError {
    public SemanticError(int line, int column) {
        super(line, column);
    }

    @Override
    public String getMessage() {
        return "Semantic Error: " + getLine() + ", " + getColumn() + ") ";
    }
}
