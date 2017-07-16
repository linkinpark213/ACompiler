package com.linkinpark213.compiler.analyzer.semantic;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class SymbolList {
    private HashMap<String, Symbol> symbolHashMap;
    private int currentScope;

    public SymbolList() {
        symbolHashMap = new HashMap<String, Symbol>();
        currentScope = 0;
    }

    public void openScope() {
        currentScope++;
    }

    public void closeScope() {
        currentScope--;
        symbolHashMap.forEach(new BiConsumer<String, Symbol>() {
            @Override
            public void accept(String s, Symbol symbol) {
                symbolHashMap.remove(s);
            }
        });
    }

    public boolean enterSymbol(Symbol symbol) {
        if (this.retrieveSymbol("") != null)
            return false;
        symbolHashMap.put("", symbol);
        return true;
    }

    public Symbol retrieveSymbol(String name) {
        return symbolHashMap.get(name);
    }

}
