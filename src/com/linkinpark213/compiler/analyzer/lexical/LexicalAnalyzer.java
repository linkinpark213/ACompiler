package com.linkinpark213.compiler.analyzer.lexical;

import com.linkinpark213.compiler.analyzer.lexical.dfd.ConstantDFD;
import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFD;
import com.linkinpark213.compiler.analyzer.lexical.dfd.OperatorDFD;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Operator;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Separator;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class LexicalAnalyzer {
    public ArrayList<Symbol> analyze(String code) {
        System.out.println("Analyzing: \n\t" + code + "\n");
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
        String tempCode = code;
        do {
            char firstChar = tempCode.charAt(0);
            char secondChar = 0;
            if (tempCode.length() > 1)
                secondChar = tempCode.charAt(1);
            while (firstChar == ' ') {
                tempCode = tempCode.substring(1);
                if (tempCode.length() == 0)
                    return symbolList;
                firstChar = tempCode.charAt(0);
                if (tempCode.length() > 1)
                    secondChar = tempCode.charAt(1);
            }
            Symbol nextSymbol = null;
            if (firstChar == '-' && Constant.isDigit(secondChar)
                    || Constant.isDigit(firstChar) || firstChar == '\'') {
                //  Constant Value
                nextSymbol = ConstantDFD.getInstance().nextSymbol(tempCode);
                symbolList.add(nextSymbol);
            } else if (Operator.isOperatorBeginning(firstChar)) {
                //  Operator
                nextSymbol = OperatorDFD.getInstance().nextSymbol(tempCode);
                symbolList.add(nextSymbol);
            } else if (Separator.isSeparator(firstChar)) {
                //  Separator
                nextSymbol = new Separator(firstChar);
                symbolList.add(nextSymbol);
            } else {
                //  Identifier or keyword
                nextSymbol = IdentifierDFD.getInstance().nextSymbol(tempCode);
                symbolList.add(nextSymbol);
            }
            if (tempCode.length() > nextSymbol.toString().length())
                tempCode = tempCode.substring(nextSymbol.toString().length());
            else tempCode = "";
        } while (tempCode.length() > 0);
        return symbolList;
    }
}
