package com.linkinpark213.compiler.analyzer.semantic;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class Symbol {
    private String name;
    private int type;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;

    public Symbol(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public Symbol(String name, String type) {
        int typeInt = 0;
        if (type.equals("int")) {
            typeInt = TYPE_INT;
        } else if (type.equals("bool")) {
            typeInt = TYPE_BOOL;
        } else if (type.equals("float")) {
            typeInt = TYPE_FLOAT;
        } else if (type.equals("char")) {
            typeInt = TYPE_CHAR;
        }
        this.name = name;
        this.type = typeInt;
    }

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

    public String getTypeString() {
        String typeString = "";
        switch (type) {
            case TYPE_INT:
                typeString = "Integer";
                break;
            case TYPE_FLOAT:
                typeString = "Real";
                break;
            case TYPE_CHAR:
                typeString = "Character";
                break;
            case TYPE_BOOL:
                typeString = "Boolean";
        }
        return typeString;
    }

}
