package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.RelationOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class RelationExpression extends VN {
    @Override
    public boolean analyze(ArrayList<Token> tokenQueue, SymbolList symbolList) {
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
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        Quad jumpQuad = new Quad();
        Quad falseQuad = new Quad();
        Quad trueQuad = new Quad();
        jumpQuad.setOperator("j");
        jumpQuad.setVariableA("");
        jumpQuad.setVariableB("");
        jumpQuad.setResult("T");
        falseQuad.setOperator(":=");
        falseQuad.setVariableA("");
        falseQuad.setVariableB("");
        falseQuad.setResult("T");
        trueQuad.setOperator(":=");
        trueQuad.setVariableA("");
        trueQuad.setVariableB("");
        trueQuad.setResult("T");
    }
}
