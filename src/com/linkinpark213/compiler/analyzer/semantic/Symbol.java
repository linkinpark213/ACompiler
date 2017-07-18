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
        int typeInt = typeStringToCode(type);
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
        typeString = typeCodeToString(type);
        if (this.isArray()) {
            return "Array(" + typeString + ")";
        }
        return typeString;
    }

    public static String typeCodeToString(int typeCode) {
        switch (typeCode) {
            case TYPE_INT:
                return "Integer";
            case TYPE_FLOAT:
                return "Real";
            case TYPE_CHAR:
                return "Character";
            case TYPE_BOOL:
                return "Boolean";
            case TYPE_FUNCTION:
                return "Function";
            default:
                return "Undefined";
        }
    }

    public static int typeStringToCode(String typeString) {
        if (typeString.equals("int")) {
            return TYPE_INT;
        } else if (typeString.equals("bool")) {
            return TYPE_BOOL;
        } else if (typeString.equals("char")) {
            return TYPE_CHAR;
        } else if (typeString.equals("float")) {
            return TYPE_FLOAT;
        } else if (typeString.equals("function")) {
            return TYPE_FUNCTION;
        }
        return 0;
    }
}
