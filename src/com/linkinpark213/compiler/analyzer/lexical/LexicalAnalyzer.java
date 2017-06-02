package com.linkinpark213.compiler.analyzer.lexical;

import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFD;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class LexicalAnalyzer {
    public ArrayList<Symbol> analyze(String code) {
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
        String tempCode = code;
        do {
            char firstChar = tempCode.charAt(0);
            char secondChar = tempCode.charAt(1);
            while (firstChar == ' ') {
                tempCode = tempCode.substring(1);
            }
            if (firstChar == '-' && secondChar >= '0' && secondChar <= '9' || firstChar >= '0' && firstChar <= '9') {
                //  Constant Value
            } else if (firstChar == '+' || firstChar == '-' || firstChar == '*') {
                //  Operator
            } else if (firstChar == '{' || firstChar == '}'
                    || firstChar == '(' || firstChar == ')'
                    || firstChar == ';' || firstChar == ',') {
                //  Separator
            } else {
                Symbol nextSymbol = IdentifierDFD.getInstance().nextSymbol(tempCode);
                symbolList.add(nextSymbol);
                tempCode = tempCode.substring(nextSymbol.toString().length());
            }
        } while (tempCode.length() > 0);
        return symbolList;
    }
}
