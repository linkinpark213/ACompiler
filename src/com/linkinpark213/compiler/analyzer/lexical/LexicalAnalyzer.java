package com.linkinpark213.compiler.analyzer.lexical;

import com.linkinpark213.compiler.analyzer.lexical.dfd.ConstantDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfd.OperatorDFA;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidConstantException;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidIdentifierException;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidOperatorException;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Constant;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Operator;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Separator;
import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class LexicalAnalyzer {
    private ArrayList<Symbol> symbolQueue;
    private int row;
    private int col;

    public LexicalAnalyzer() {
        clear();
    }

    private void clear() {
        row = 1;
        col = 1;
        symbolQueue = new ArrayList<Symbol>();
    }

    public void reportError(String message) {
        System.out.println("Compile Error: (" + row + ", " + col + "): " + message);
        System.exit(0);
    }

    public ArrayList<Symbol> analyze(String code) {
        System.out.println("Analyzing: \n" + code + "\n");
        String tempCode = code;
        this.clear();
        try {
            do {
                char firstChar = tempCode.charAt(0);
                char secondChar = 0;
                if (tempCode.length() > 1)
                    secondChar = tempCode.charAt(1);
                while (firstChar == ' ' || firstChar == '\n') {
                    tempCode = tempCode.substring(1);
                    if (firstChar == ' ')
                        col++;
                    else {
                        row++;
                        col = 1;
                    }
                    if (tempCode.length() == 0)
                        return symbolQueue;
                    firstChar = tempCode.charAt(0);
                    if (tempCode.length() > 1)
                        secondChar = tempCode.charAt(1);
                }
                Symbol nextSymbol = null;
                if (firstChar == '-' && Constant.isDigit(secondChar)
                        || Constant.isDigit(firstChar) || firstChar == '\'') {
                    //  Constant Value
                    nextSymbol = ConstantDFA.getInstance().nextSymbol(tempCode, this);
                    if (nextSymbol.getType() == Constant.TYPE_CHAR) {
                        tempCode = tempCode.substring(2);
                    }
                    symbolQueue.add(nextSymbol);
                } else if (Separator.isSeparator(firstChar)) {
                    //  Separator
                    nextSymbol = new Separator(firstChar);
                    symbolQueue.add(nextSymbol);
                } else if (Operator.isOperatorBeginning(firstChar)) {
                    //  Operator
                    nextSymbol = OperatorDFA.getInstance().nextSymbol(tempCode, this);
                    symbolQueue.add(nextSymbol);
                } else {
                    //  Identifier or keyword
                    nextSymbol = IdentifierDFA.getInstance().nextSymbol(tempCode, this);
                    symbolQueue.add(nextSymbol);
                }
                nextSymbol.setRow(row);
                nextSymbol.setColumn(col);
                if (tempCode.length() > nextSymbol.toString().length())
                    tempCode = tempCode.substring(nextSymbol.toString().length());
                else tempCode = "";
                if (nextSymbol.getType() == Constant.TYPE_CHAR) {
                    tempCode = tempCode.substring(2);
                    col += 2;
                }
                col += nextSymbol.toString().length();
            } while (tempCode.length() > 0);
        } catch (InvalidConstantException | InvalidIdentifierException | InvalidOperatorException e) {
            System.out.println("Compile Error: (" + row + ", " + col + "): " + e.getMessage());
            return symbolQueue;
        }
        return symbolQueue;
    }
}
