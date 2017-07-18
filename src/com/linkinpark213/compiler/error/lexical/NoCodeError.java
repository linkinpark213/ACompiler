package com.linkinpark213.compiler.error.lexical;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class NoCodeError extends LexicalError {
    public NoCodeError(int line, int column) {
        super(line, column);
    }

    public NoCodeError(int line, int column, String message) {
        super(line, column, message);
    }

    @Override
    public String getMessage() {
        return "No invalid code detected.";
    }
}
