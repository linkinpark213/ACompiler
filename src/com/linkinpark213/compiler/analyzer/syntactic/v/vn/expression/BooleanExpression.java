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
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.BooleanOperator;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BooleanExpression extends VN {

    @Override
    public boolean analyze(ArrayList<Token> tokenQueue, SymbolList symbolList) {
        /*
        * <Boolean Expression> ::= <Relation Expression> <Alter>
        *                           | <'Not' Operator> <Identifier> <Alter>
        *                           | <'Not' Operator> ( <Boolean Expression> ) <Alter>
        *                           | <Boolean Constant> <Alter>
        *                           | <Identifier> <Alter>
        * */
        ArrayList<V> relationExpressionProduction = new ArrayList<V>();
        ArrayList<V> identifierWithNotOperatorProduction = new ArrayList<V>();
        ArrayList<V> expressionWithNotOperatorProduction = new ArrayList<V>();
        ArrayList<V> constantProduction = new ArrayList<V>();
        ArrayList<V> identifierWithDoubleOperatorProduction = new ArrayList<V>();

        relationExpressionProduction.add(new RelationExpression());
        relationExpressionProduction.add(new BooleanExpressionAlter());

        identifierWithNotOperatorProduction.add(new BooleanOperator("!"));
        identifierWithNotOperatorProduction.add(new Identifier(Identifier.TYPE_BOOL));
        identifierWithNotOperatorProduction.add(new BooleanExpressionAlter());

        expressionWithNotOperatorProduction.add(new BooleanOperator("!"));
        expressionWithNotOperatorProduction.add(new Separator("("));
        expressionWithNotOperatorProduction.add(new BooleanExpression());
        expressionWithNotOperatorProduction.add(new Separator(")"));
        expressionWithNotOperatorProduction.add(new BooleanExpressionAlter());

        constantProduction.add(new Constant(Constant.TYPE_BOOL));
        constantProduction.add(new BooleanExpressionAlter());

        identifierWithDoubleOperatorProduction.add(new Identifier(Identifier.TYPE_BOOL));
        identifierWithDoubleOperatorProduction.add(new BooleanExpressionAlter());

        productions.add(relationExpressionProduction);
        productions.add(identifierWithNotOperatorProduction);
        productions.add(expressionWithNotOperatorProduction);
        productions.add(constantProduction);
        productions.add(identifierWithDoubleOperatorProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        /*
        * <Boolean Expression> ::= <Relation Expression> <Alter>
        *                           | <'Not' Operator> <Identifier> <Alter>
        *                           | <'Not' Operator> ( <Boolean Expression> ) <Alter>
        *                           | <Boolean Constant> <Alter>
        *                           | <Identifier> <Alter>
        * */
        super.semanticAction(quadQueue);
        Quad quad = new Quad();
        Identifier identifier;
        RelationExpression relationExpression;
        BooleanExpressionAlter booleanExpressionAlter;
        BooleanExpression booleanExpression;
        Constant constant;
        switch (productionNum) {
            case 0:
                relationExpression = (RelationExpression) children.get(0);
                booleanExpressionAlter = (BooleanExpressionAlter) children.get(1);
                if (booleanExpressionAlter.getChildren().size() > 0) {
                    quad.setOperator(booleanExpressionAlter.getChildren().get(0).toString());
                    quad.setVariableA(relationExpression.getVariableName());
                    quad.setVariableB(booleanExpressionAlter.getVariableName());
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = relationExpression.getVariableName();
                }
                break;
            case 1:
                identifier = (Identifier) children.get(1);
                booleanExpressionAlter = (BooleanExpressionAlter) children.get(2);
                if (booleanExpressionAlter.getChildren().size() > 0) {
                    Quad notQuad = new Quad();
                    notQuad.setOperator("!");
                    notQuad.setVariableA(identifier.getName());
                    notQuad.setVariableB("_");
                    notQuad.setResult("T" + quadQueue.newTemp());
                    quadQueue.add(notQuad);
                    quad.setOperator(booleanExpressionAlter.getChildren().get(0).toString());
                    quad.setVariableA(notQuad.getResult());
                    quad.setVariableB(booleanExpressionAlter.getVariableName());
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    quad.setOperator("!");
                    quad.setVariableA(identifier.getName());
                    quad.setVariableB("_");
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                }
                break;
            case 2:
                booleanExpression = (BooleanExpression) children.get(1);
                booleanExpressionAlter = (BooleanExpressionAlter) children.get(2);
                if (booleanExpressionAlter.getChildren().size() > 0) {
                    Quad notQuad = new Quad();
                    notQuad.setOperator("!");
                    notQuad.setVariableA(booleanExpression.getVariableName());
                    notQuad.setVariableB("_");
                    notQuad.setResult("T" + quadQueue.newTemp());
                    quadQueue.add(notQuad);
                    quad.setOperator(booleanExpressionAlter.getChildren().get(0).toString());
                    quad.setVariableA(notQuad.getResult());
                    quad.setVariableB(booleanExpressionAlter.getVariableName());
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    quad.setOperator("!");
                    quad.setVariableA(booleanExpression.getVariableName());
                    quad.setVariableB("_");
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                }
                break;
            case 3:
                constant = (Constant) children.get(0);
                booleanExpressionAlter = (BooleanExpressionAlter) children.get(1);
                if (booleanExpressionAlter.getChildren().size() > 0) {
                    quad.setOperator(booleanExpressionAlter.getChildren().get(0).toString());
                    quad.setVariableA(constant.getValue() == 0 ? "FALSE" : "TRUE");
                    quad.setVariableB(booleanExpressionAlter.getVariableName());
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = constant.getValue() == 0 ? "FALSE" : "TRUE";
                }
                break;
            case 4:
                identifier = (Identifier) children.get(0);
                booleanExpressionAlter = (BooleanExpressionAlter) children.get(1);
                if (booleanExpressionAlter.getChildren().size() > 0) {
                    quad.setOperator(booleanExpressionAlter.getChildren().get(0).toString());
                    quad.setVariableA(identifier.getName());
                    quad.setVariableB(booleanExpressionAlter.getVariableName());
                    this.variableName = "T" + quadQueue.newTemp();
                    quad.setResult(this.getVariableName());
                    quadQueue.add(quad);
                } else {
                    this.variableName = identifier.getName();
                }
                break;
        }
    }
}
