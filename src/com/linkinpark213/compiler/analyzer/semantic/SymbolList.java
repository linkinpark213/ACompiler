package com.linkinpark213.compiler.analyzer.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class SymbolList {
    private HashMap<String, Symbol> symbolHashMap;
    private HashMap<String, Function> functionHashMap;
    private int currentScope;

    public SymbolList() {
        symbolHashMap = new HashMap<String, Symbol>();
        functionHashMap = new HashMap<String, Function>();
        currentScope = 0;
    }

    public void openScope() {
        currentScope++;
    }

    public void closeScope() {
        currentScope--;
        ArrayList<String> symbolsToBeDeleted = new ArrayList<String>();
        symbolHashMap.forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                if (symbol.getScope() > currentScope)
                    symbolsToBeDeleted.add(s);
            }
        });
        for (String name : symbolsToBeDeleted) {
            symbolHashMap.remove(name);
        }
    }

    public boolean enterSymbol(Symbol symbol) {
        symbol.setScope(currentScope);
        if (this.retrieveSymbol(symbol.getName()) != null)
            return false;
        symbolHashMap.put(symbol.getName(), symbol);
        return true;
    }

    public boolean enterFunction(Symbol symbol, int[] parameterTypes) {
        if (this.retrieveFunction(symbol.getName()) != null)
            return false;
        functionHashMap.put(symbol.getName(), new Function(symbol, parameterTypes));
        return true;
    }

    public HashMap<String, Symbol> getSymbolHashMap() {
        return symbolHashMap;
    }

    public HashMap<String, Function> getFunctionHashMap() {
        return functionHashMap;
    }

    public Symbol retrieveSymbol(String name) {
        return symbolHashMap.get(name);
    }

    public Symbol retrieveFunction(String name) {
        try {
            return functionHashMap.get(name).getSymbol();
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteSymbol(String name) {
        symbolHashMap.remove(name);
    }

    public void printList() {
        System.out.println("============================");
        System.out.println("=       Symbol List        =");
        System.out.println("============================");
        symbolHashMap.forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                System.out.print(s);
                System.out.print(" : ");
                System.out.println(symbol.getTypeString());
            }
        });
        System.out.println("============================");
    }
}
