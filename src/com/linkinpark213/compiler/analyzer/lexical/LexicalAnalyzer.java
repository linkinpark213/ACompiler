package com.linkinpark213.compiler.analyzer.lexical;

import com.linkinpark213.compiler.analyzer.lexical.dfd.ConstantDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfd.IdentifierDFA;
import com.linkinpark213.compiler.analyzer.lexical.dfd.OperatorDFA;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidConstantException;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidIdentifierException;
import com.linkinpark213.compiler.analyzer.lexical.exception.InvalidOperatorException;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Constant;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Operator;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Separator;
import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;

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

    public ArrayList<Token> analyze(String code) {
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
                        return tokenQueue;
                    firstChar = tempCode.charAt(0);
                    if (tempCode.length() > 1)
                        secondChar = tempCode.charAt(1);
                }
                Token nextToken = null;
                if (firstChar == '-' && Constant.isDigit(secondChar)
                        || Constant.isDigit(firstChar) || firstChar == '\'') {
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
        } catch (Exception e) {
            System.out.println("Compile Error: (" + row + ", " + col + "): " + e.getMessage());
            return tokenQueue;
        }
        return tokenQueue;
    }
}
