package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class Expression extends VN {
    protected int type;
    protected int value;

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Expression> ::=   <Boolean Expression>
        *                   | <Relation Expression>
        *                   | <Arithmetic Expression>
        * */
        ArrayList<V> booleanProduction = new ArrayList<V>();
        ArrayList<V> relationProduction = new ArrayList<V>();
        ArrayList<V> arithmeticProduction = new ArrayList<V>();
        booleanProduction.add(new BooleanExpression());
        relationProduction.add(new RelationExpression());
        arithmeticProduction.add(new ArithmeticExpression());
        productions.add(booleanProduction);
        productions.add(relationProduction);
        productions.add(arithmeticProduction);
//        return super.analyze(tokenQueue, symbolList);
        for (int i = 0; i < productions.size(); i++) {
            V v = productions.get(i).get(0);
            VN vn = (VN) v;
            if (tokenQueue.size() == 0 && !((VN) v).isNullable()) break;
            if (vn.analyze(tokenQueue, symbolList)) {
                this.addChild(vn.getClone());
                System.out.println("Adding child " + vn.getVariableName());
                productionNum = i;
                return true;
            } else {
                rollBack(tokenQueue, symbolList);
                children.clear();
            }
        }
        return false;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        V expression = children.get(0);
        switch (productionNum) {
            case 0:
                this.variableName = ((BooleanExpression) expression).getVariableName();
                break;
            case 1:
                this.variableName = ((RelationExpression) expression).getVariableName();
                break;
            case 2:
                this.variableName = ((ArithmeticExpression) expression).getVariableName();
                break;
        }
    }
}
