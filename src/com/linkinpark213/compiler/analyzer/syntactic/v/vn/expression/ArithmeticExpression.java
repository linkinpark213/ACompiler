package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class ArithmeticExpression extends VN {
    private int type;
    private float result;
    public static final int TYPE_INT = 100;
    public static final int TYPE_FLOAT = 101;
    public static final int TYPE_CHAR = 102;
    public static final int TYPE_BOOL = 103;

    @Override
    public boolean analyze(ArrayList<Token> tokenQueue, SymbolList symbolList) {
        /*
        * <Arithmetic Expression> ::= ( <Arithmetic Expression> ) <Alter>
        *                           | <Identifier> <Increment/Decrement Operator>
        *                           | <Identifier> <Alter>
        *                           | <Constant> <Alter>
        * */
        ArrayList<V> expressionWithBracketsProduction = new ArrayList<V>();
        ArrayList<V> crementProduction = new ArrayList<V>();
        ArrayList<V> singleIdentifierProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();

        expressionWithBracketsProduction.add(new Separator("("));
        expressionWithBracketsProduction.add(new ArithmeticExpression());
        expressionWithBracketsProduction.add(new Separator(")"));
        expressionWithBracketsProduction.add(new ArithmeticExpressionAlter());

        crementProduction.add(new Identifier());
        crementProduction.add(new ArithmeticOperator("++", "--"));

        singleIdentifierProduction.add(new Identifier());
        singleIdentifierProduction.add(new ArithmeticExpressionAlter());

        constantProduction.add(new Constant());
        constantProduction.add(new ArithmeticExpressionAlter());

        productions.add(expressionWithBracketsProduction);
        productions.add(crementProduction);
        productions.add(singleIdentifierProduction);
        productions.add(constantProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    public float getResult() {
        return result;
    }

    @Override
    public void semanticAction() {
        super.semanticAction();
        switch (productionNum) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
