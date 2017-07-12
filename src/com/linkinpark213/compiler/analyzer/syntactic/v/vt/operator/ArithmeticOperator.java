package com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

/**
 * Created by ooo on 2017/7/4 0004.
 */
public class ArithmeticOperator extends Operator {
    public ArithmeticOperator() {
        acceptableSymbols.add("+");
        acceptableSymbols.add("-");
        acceptableSymbols.add("++");
        acceptableSymbols.add("--");
        acceptableSymbols.add("*");
    }

    public ArithmeticOperator(String... symbols) {
        for (String symbol : symbols
                ) {
            acceptableSymbols.add(symbol);
        }
    }

    @Override
    public boolean checkSymbol(Symbol symbol) {
        this.setSymbol(symbol.toString());
        return super.checkSymbol(symbol);
    }
}
