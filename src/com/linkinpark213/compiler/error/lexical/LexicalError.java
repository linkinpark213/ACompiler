package com.linkinpark213.compiler.error.lexical;

import com.linkinpark213.compiler.error.AnalysisError;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class LexicalError extends AnalysisError {
    private String message;

    public LexicalError(int line, int column) {
        super(line, column);
    }

    public LexicalError(int line, int column, String message) {
        super(line, column);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Lexical Error: (" + getLine() + ", " + getColumn() + ") " + message;
    }
}
