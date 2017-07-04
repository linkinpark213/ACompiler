package com.linkinpark213.compiler.analyzer.syntactic.v.vt.keyword;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class TypeDefinitionKeyword extends VT {
    public TypeDefinitionKeyword() {
        acceptableSymbols.add("int");
        acceptableSymbols.add("float");
        acceptableSymbols.add("char");
        acceptableSymbols.add("bool");
    }
}
