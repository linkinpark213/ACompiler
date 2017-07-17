package com.linkinpark213.compiler.error;

/**
 * Created by ooo on 2017/7/17 0017.
 */
public class AnalysisError extends Exception {
    private int line;
    private int column;
    protected String message;

    public AnalysisError(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public AnalysisError(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String getMessage() {
        return "Unknown Error: (" + line + ", " + column + ") " + message;
    }
}
