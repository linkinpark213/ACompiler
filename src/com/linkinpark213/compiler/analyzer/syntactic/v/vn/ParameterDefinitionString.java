package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

public class ParameterDefinitionString extends VN {
    public ParameterDefinitionString() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Parameter Definition String> ::= <Parameter Definition> <Alter>
        *                                 | nothing
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new ParameterDefinition());
        production.add(new ParameterDefinitionStringAlter());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
    }

    public int[] getParameterTypes() {
        if (children.size() > 0) {
            ParameterDefinition parameterDefinition = (ParameterDefinition) children.get(0);
            ParameterDefinitionStringAlter parameterDefinitionStringAlter = (ParameterDefinitionStringAlter) children.get(1);
            int firstType = parameterDefinition.getParameterType();
            int[] afterTypes = parameterDefinitionStringAlter.getParameterTypes();
            int[] newTypes;
            if (afterTypes != null) {
                newTypes = new int[afterTypes.length + 1];
                newTypes[0] = firstType;
                for (int i = 0; i < afterTypes.length; i++) {
                    newTypes[i + 1] = afterTypes[i];
                }
                return newTypes;
            } else {
                newTypes = new int[1];
                newTypes[0] = firstType;
                return newTypes;
            }
        }
        return null;
    }
}
