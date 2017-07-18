package com.linkinpark213.compiler.analyzer.semantic;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class Symbol {
    private String name;
    private int type;
    private String typeString;
    private int scope;
    private boolean isArray;
    private int[] dimensions;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;
    public static final int TYPE_FUNCTION = 105;

    public Symbol(String name, int type) {
        this.name = name;
        this.type = type;
        this.isArray = false;
        this.dimensions = null;
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
        } else if (type.equals("function")) {
            typeInt = TYPE_FUNCTION;
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

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.setArray(true);
        this.dimensions = dimensions;
    }

    public String getTypeString() {
        typeString = "";
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
                break;
            case TYPE_FUNCTION:
                typeString = "Function";
                break;
        }
        if (this.isArray()) {
            return "Array(" + typeString + ")";
        }
        return typeString;
    }

}
