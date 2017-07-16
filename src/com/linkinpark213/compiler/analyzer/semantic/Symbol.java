package com.linkinpark213.compiler.analyzer.semantic;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class Symbol {
    private String name;
    private int type;
    private int scope;
    public static final int TYPE_INT = 100;
    public static final int TYPE_FLOAT = 101;
    public static final int TYPE_CHAR = 102;
    public static final int TYPE_BOOL = 103;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}
