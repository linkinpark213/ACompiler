package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class ConstantIndexString extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Constant Index String> ::= <Constant> <Constant Index String Alter>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Constant(Constant.TYPE_INT));
        production.add(new ConstantIndexStringAlter());
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
    }

    public int[] getDimensionSizes() {
        Constant constant = (Constant) children.get(0);
        ConstantIndexStringAlter constantIndexStringAlter = (ConstantIndexStringAlter) children.get(1);
        int[] afterDimensions = constantIndexStringAlter.getDimensionSizes();
        if (afterDimensions != null) {
            int[] newDimensionSizes = new int[afterDimensions.length + 1];
            newDimensionSizes[0] = (int) constant.getValue();
            for (int i = 0; i < afterDimensions.length; i++) {
                newDimensionSizes[i + 1] = afterDimensions[i];
            }
            return newDimensionSizes;
        } else {
            int[] dimensionSizes = new int[1];
            dimensionSizes[0] = (int) constant.getValue();
            return dimensionSizes;
        }
    }
}
