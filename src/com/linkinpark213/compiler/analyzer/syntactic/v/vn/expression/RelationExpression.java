package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.RelationOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class RelationExpression extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Relation Expression> ::= <Arithmetic Expression> <Relation Operator> <Arithmetic Expression>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new ArithmeticExpression());
        production.add(new RelationOperator());
        production.add(new ArithmeticExpression());
        productions.add(production);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
