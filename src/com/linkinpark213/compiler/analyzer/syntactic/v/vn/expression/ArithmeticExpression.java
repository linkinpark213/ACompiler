package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ArrayVariable;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.ArithmeticOperator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.Operator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

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
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Arithmetic Expression> ::= ( <Arithmetic Expression> ) <Alter>
        *                           | <Array Variable> <Alter>
        *                           | <Identifier> <Increment/Decrement Operator>
        *                           | <Identifier> <Alter>
        *                           | <Constant> <Alter>
        * */
        ArrayList<V> expressionWithBracketsProduction = new ArrayList<V>();
        ArrayList<V> arrayProduction = new ArrayList<V>();
        ArrayList<V> crementProduction = new ArrayList<V>();
        ArrayList<V> singleIdentifierProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();

        expressionWithBracketsProduction.add(new Separator("("));
        expressionWithBracketsProduction.add(new ArithmeticExpression());
        expressionWithBracketsProduction.add(new Separator(")"));
        expressionWithBracketsProduction.add(new ArithmeticExpressionAlter());

        arrayProduction.add(new ArrayVariable());
        arrayProduction.add(new ArithmeticExpressionAlter());

        crementProduction.add(new Identifier());
        crementProduction.add(new ArithmeticOperator("++", "--"));

        singleIdentifierProduction.add(new Identifier());
        singleIdentifierProduction.add(new ArithmeticExpressionAlter());

        constantProduction.add(new Constant());
        constantProduction.add(new ArithmeticExpressionAlter());

        productions.add(expressionWithBracketsProduction);
        productions.add(arrayProduction);
        productions.add(crementProduction);
        productions.add(singleIdentifierProduction);
        productions.add(constantProduction);
        if (super.analyze(tokenQueue, symbolList)) {
            return true;
        }
        return false;
    }

    public void printExpression() {
        System.out.print("Arithmetic Expression ");
        for (V v : children) {
            if (v instanceof ArithmeticExpressionAlter) {
                ((ArithmeticExpressionAlter) v).printExpression();
            } else {
                System.out.print(((VT) v).toString());
            }
        }
        System.out.println(" get!");
    }

    public float getResult() {
        return result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        /*
        * When calculating, the type of the final result should be kept the same to the broader one
        * */
        super.semanticAction(quadQueue, symbolList);
        Identifier identifier;
        ArithmeticExpression arithmeticExpression;
        ArithmeticExpressionAlter arithmeticExpressionAlter;
        Quad quad;
        Operator operator;
        Constant constant;
        ArrayVariable arrayVariable;
        switch (productionNum) {
            case 0:
                /*
                * ( <Arithmetic Expression> ) <Alter>
                * */
                arithmeticExpression = (ArithmeticExpression) children.get(1);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(3);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    this.setType(Math.max(arithmeticExpression.getType(), arithmeticExpressionAlter.getType()));
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
                    this.setType(arithmeticExpression.getType());
                }
                break;
            case 1:
                /*
                * <Array Variable> <Alter>
                * */
                arrayVariable = (ArrayVariable) children.get(0);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(1);
                Quad fetchValueQuad = new Quad("=[]", arrayVariable.getPlace() + "[" + arrayVariable.getOffset() + "]",
                        "_", "T" + quadQueue.newTemp());
                quadQueue.add(fetchValueQuad);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    this.setType(Math.max(arrayVariable.getType(), arithmeticExpressionAlter.getType()));
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    quad.setVariableA(fetchValueQuad.getResult());
                    quad.setVariableB(arithmeticExpressionAlter.getVariableName());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = fetchValueQuad.getResult();
                }
                break;
            case 2:
                /*
                *  <Identifier> <Increment/Decrement Operator>
                * */
                identifier = (Identifier) children.get(0);
                operator = (Operator) children.get(1);
                this.setType(identifier.getType());
                quad = new Quad();
                quad.setOperator(operator.toString());
                quad.setVariableA("1");
                quad.setVariableB(identifier.toString());
                quad.setResult(identifier.toString());
                quadQueue.add(quad);
                this.variableName = identifier.getName();
                break;
            case 3:
                /*
                * <Identifier> <Alter>
                * */
                identifier = (Identifier) children.get(0);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(1);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    this.setType(Math.max(identifier.getType(), arithmeticExpressionAlter.getType()));
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    quad.setVariableA(identifier.getName());
                    quad.setVariableB(arithmeticExpressionAlter.getVariableName());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.setType(identifier.getType());
                    this.variableName = identifier.getName();
                }
                break;
            case 4:
                /*
                * <Constant> <Alter>
                * */
                constant = (Constant) children.get(0);
                arithmeticExpressionAlter = (ArithmeticExpressionAlter) children.get(1);
                if (arithmeticExpressionAlter.getChildren().size() > 0) {
                    operator = (Operator) arithmeticExpressionAlter.getChildren().get(0);
                    this.setType(Math.max(constant.getType(), arithmeticExpressionAlter.getType()));
                    quad = new Quad();
                    quad.setOperator(operator.toString());
                    String valueString = constant.getFormattedValue();
                    quad.setVariableA(valueString);
                    quad.setVariableB(arithmeticExpressionAlter.getVariableName());
                    this.setTempID(quadQueue.newTemp());
                    this.variableName = "T" + this.getTempID();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.setType(constant.getType());
                    this.variableName = "" + constant.getFormattedValue();
                }
                break;
        }
    }
}
