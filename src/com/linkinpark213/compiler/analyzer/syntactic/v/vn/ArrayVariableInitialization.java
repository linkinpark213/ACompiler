package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class ArrayVariableInitialization extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Array Variable Initialization> ::= <Identifier> [ <Constant Index String> ]
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Identifier());
        production.add(new Separator("["));
        production.add(new ConstantIndexString());
        production.add(new Separator("]"));
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
    }

    public int[] getDimensionSizes() {
        ConstantIndexString constantIndexString = (ConstantIndexString) children.get(2);
        return constantIndexString.getDimensionSizes();
    }
}
