package com.linkinpark213.compiler.error.syntactical;

import com.linkinpark213.compiler.error.AnalysisError;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class SyntaxError extends AnalysisError {
    public SyntaxError(int line, int column) {
        super(line, column);
    }

    public SyntaxError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return "Syntax Error: (" + getLine() + ", " + getColumn() + ") " + message;
    }
}
