package com.linkinpark213.compiler.analyzer.lexical.tokens;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public interface Token {
    public String fullString();

    public int getType();

    public String getName();

    public String getFullTypeString();

    public int getRow();

    public void setRow(int row);

    public int getColumn();

    public void setColumn(int column);
}
