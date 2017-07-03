package com.linkinpark213.compiler.analyzer.lexical.symbols;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public interface Symbol {
    public String fullString();
    public int getType();
    public int getRow();
    public void setRow(int row);
    public int getColumn();
    public void setColumn(int column);
}
