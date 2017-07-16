package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.Operator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class ArithmeticExpression extends VN {
    private int type;
    private float result;
    public static final int TYPE_INT = 102;
    public static final int TYPE_FLOAT = 103;
    public static final int TYPE_CHAR = 101;
    public static final int TYPE_BOOL = 100;

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
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        Identifier identifier;
        ArithmeticExpression arithmeticExpression;
        ArithmeticExpressionAlter arithmeticExpressionAlter;
        Quad quad;
        Operator operator;
        Constant constant;
        switch (productionNum) {
            case 0:
                arithmeticExpression = (ArithmeticExpression) children.get(1);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(3);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    quad.setVariableA("T" + arithmeticExpression.getTempID());
                    quad.setVariableB("T" + arithmeticExpressionAlter.getTempID());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = arithmeticExpression.getVariableName();
                }
                break;
            case 1:
                identifier = (Identifier) children.get(0);
                operator = (Operator) children.get(1);
                quad = new Quad();
                quad.setOperator(operator.toString());
                quad.setVariableA("1");
                quad.setVariableB(identifier.toString());
                quad.setResult(identifier.toString());
                quadQueue.add(quad);
                this.variableName = identifier.getName();
                break;
            case 2:
                identifier = (Identifier) children.get(0);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(1);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    quad.setVariableA(identifier.getName());
                    quad.setVariableB(arithmeticExpressionAlter.getVariableName());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = identifier.getName();
                }
                break;
            case 3:
                constant = (Constant) children.get(0);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(1);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    float value = constant.getValue();
                    String valueString = "";
                    switch (constant.getType()) {
                        case TYPE_BOOL:
                            valueString = constant.getValue() == 0 ? "false" : "true";
                            break;
                        case TYPE_CHAR:
                            valueString = "" + (char) constant.getValue();
                            break;
                        case TYPE_INT:
                            valueString = "" + (int) constant.getValue();
                            break;
                        case TYPE_FLOAT:
                            valueString = "" + constant.getValue();
                            break;
                    }
                    quad.setVariableA(valueString);
                    quad.setVariableB(arithmeticExpressionAlter.getVariableName());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = "" + constant.getValue();
                }
                break;
        }
    }
}
