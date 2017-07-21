package com.linkinpark213.compiler.analyzer.lexical;

import com.linkinpark213.compiler.analyzer.lexical.dfa.ConstantDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfa.IdentifierDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfa.OperatorDFA;
import com.linkinpark213.compiler.analyzer.lexical.tokens.*;
import com.linkinpark213.compiler.error.AnalysisError;
import com.linkinpark213.compiler.error.lexical.InvalidSymbolError;
import com.linkinpark213.compiler.error.lexical.LexicalError;
import com.linkinpark213.compiler.error.lexical.NoCodeError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class LexicalAnalyzer {
    private ArrayList<Token> tokenQueue;
    private int row;
    private int col;

    public LexicalAnalyzer() {
        clear();
    }

    private void clear() {
        row = 1;
        col = 1;
        tokenQueue = new ArrayList<Token>();
    }

    public void reportError(String message) {
        System.out.println("Compile Error: (" + row + ", " + col + "): " + message);
        System.exit(0);
    }

    public ArrayList<Token> analyze(String code) throws Exception {
        System.out.println("Analyzing: \n" + code + "\n");
        String tempCode = code;
        this.clear();
        if (code.length() == 0) {
            throw new NoCodeError(1, 1);
        }
        try {
            do {
                char firstChar = tempCode.charAt(0);
                char secondChar = 0;
                if (tempCode.length() > 1)
                    secondChar = tempCode.charAt(1);
                while (firstChar == ' ' || firstChar == '\n' || firstChar == '\t' || firstChar == '\r') {
                    tempCode = tempCode.substring(1);
                    if (firstChar == ' ')
                        col++;
                    else {
                        row++;
                        col = 1;
                    }
                    if (tempCode.length() == 0)
                        return tokenQueue;
                    firstChar = tempCode.charAt(0);
                    if (tempCode.length() > 1)
                        secondChar = tempCode.charAt(1);
                }
                Token nextToken = null;
                if (firstChar == '-' && Constant.isDigit(secondChar)
                        && !(tokenQueue.get(tokenQueue.size() - 1) instanceof Constant)
                        && !(tokenQueue.get(tokenQueue.size() - 1) instanceof Identifier)) {
                    //  Minus Constant Value
                    nextToken = ConstantDFA.getInstance().nextSymbol(tempCode, this);
                    if (nextToken.getType() == Constant.TYPE_CHAR) {
                        tempCode = tempCode.substring(2);
                    }
                    tokenQueue.add(nextToken);
                } else if (Constant.isDigit(firstChar) || firstChar == '\'') {
                    //  Constant Value
                    nextToken = ConstantDFA.getInstance().nextSymbol(tempCode, this);
                    if (nextToken.getType() == Constant.TYPE_CHAR) {
                        tempCode = tempCode.substring(2);
                    }
                    tokenQueue.add(nextToken);
                } else if (Operator.isOperatorBeginning(firstChar)) {
                    //  ArithmeticOperator
                    nextToken = OperatorDFA.getInstance().nextSymbol(tempCode, this);
                    tokenQueue.add(nextToken);
                } else if (Separator.isSeparator(firstChar)) {
                    //  Separator
                    nextToken = new Separator(firstChar);
                    tokenQueue.add(nextToken);
                } else {
                    //  Identifier or keyword
                    nextToken = IdentifierDFA.getInstance().nextSymbol(tempCode, this);
                    tokenQueue.add(nextToken);
                }
                nextToken.setRow(row);
                nextToken.setColumn(col);
                if (tempCode.length() > nextToken.toString().length())
                    tempCode = tempCode.substring(nextToken.toString().length());
                else tempCode = "";
                if (nextToken.getType() == Constant.TYPE_CHAR) {
                    tempCode = tempCode.substring(2);
                    col += 2;
                }
                col += nextToken.toString().length();
            } while (tempCode.length() > 0);
        } catch (InvalidSymbolError e) {
            throw new LexicalError(row, col, e.getMessage());
        }
        return tokenQueue;
    }
}
