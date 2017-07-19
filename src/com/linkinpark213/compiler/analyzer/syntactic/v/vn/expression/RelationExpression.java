package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.Operator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.RelationOperator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class RelationExpression extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Relation Expression> ::= <Arithmetic Expression> <Relation Operator> <Arithmetic Expression>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new ArithmeticExpression());
        production.add(new RelationOperator());
        production.add(new ArithmeticExpression());
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
        ArithmeticExpression arithmeticExpression1 = (ArithmeticExpression) children.get(0);
        Operator operator = (Operator) children.get(1);
        ArithmeticExpression arithmeticExpression2 = (ArithmeticExpression) children.get(2);

        Quad jumpQuad = new Quad();
        Quad falseQuad = new Quad();
        Quad finishQuad = new Quad();
        Quad trueQuad = new Quad();

        this.variableName = "T" + quadQueue.newTemp();

        jumpQuad.setOperator("j" + operator.getSymbol());
        jumpQuad.setVariableA(arithmeticExpression1.getVariableName());
        jumpQuad.setVariableB(arithmeticExpression2.getVariableName());
        jumpQuad.setResult("" + (quadQueue.nxq() + 3));

        falseQuad.setOperator(":=");
        falseQuad.setVariableA("FALSE");
        falseQuad.setVariableB("_");
        falseQuad.setResult(this.getVariableName());

        finishQuad.setOperator("j");
        finishQuad.setVariableA("_");
        finishQuad.setVariableB("_");
        finishQuad.setResult("" + (quadQueue.nxq() + 4));

        trueQuad.setOperator(":=");
        trueQuad.setVariableA("TRUE");
        trueQuad.setVariableB("_");
        trueQuad.setResult(this.getVariableName());

        quadQueue.add(jumpQuad);
        quadQueue.add(falseQuad);
        quadQueue.add(finishQuad);
        quadQueue.add(trueQuad);
    }

    public int getType() {
        return Constant.TYPE_BOOL;
    }
}
