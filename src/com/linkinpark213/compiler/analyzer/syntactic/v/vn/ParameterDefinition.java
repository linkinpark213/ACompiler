package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

public class ParameterDefinition extends VN {
    private String name;
    private int type;

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Parameter Definition> ::= <Typedef Keyword> <Identifier>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("int", "bool", "char", "float"));
        production.add(new Identifier());
        productions.add(production);
        if (super.analyze(tokenQueue, symbolList)) {
            Keyword keyword = (Keyword) children.get(0);
            Identifier identifier = (Identifier) children.get(1);
            this.name = identifier.getName();
            this.type = Symbol.typeStringToCode(keyword.getValue());
            return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
    }

    public int getParameterType() {
        Keyword keyword = (Keyword) children.get(0);
        return Symbol.typeStringToCode(keyword.toString());
    }
}
