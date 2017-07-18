package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ArrayVariableInitialization;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.IdentifierString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.error.semantic.IdentifierDuplicateDefinitionError;
import com.linkinpark213.compiler.error.semantic.IdentifierNotDefinedError;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class DefinitionStatement extends VN {
    private ArrayList<String> namesList;

    public DefinitionStatement() {
        namesList = new ArrayList<String>();
    }

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
         * <Definition Statement> ::= <Typedef Keyword> <Array Variable Initialization>
         *                          | <Typedef Keyword> <Identifier String>
         */
        ArrayList<V> arrayProduction = new ArrayList<V>();
        ArrayList<V> simpleProduction = new ArrayList<V>();

        arrayProduction.add(new Keyword("int", "bool", "float", "char"));
        arrayProduction.add(new ArrayVariableInitialization());

        simpleProduction.add(new Keyword("int", "bool", "float", "char"));
        simpleProduction.add(new IdentifierString());

        productions.add(arrayProduction);
        productions.add(simpleProduction);
        if (super.analyze(tokenQueue, symbolList)) {
            String typeString = ((Keyword) children.get(0)).toString();
            if (children.get(1) instanceof IdentifierString) {
                IdentifierString identifierString = (IdentifierString) simpleProduction.get(1);
                identifierString.getNames(namesList);
                for (String name : namesList) {
                    if (!symbolList.enterSymbol(new Symbol(name, typeString)))
                        throw new IdentifierDuplicateDefinitionError(tokenQueue.get(0).getRow(),
                                tokenQueue.get(0).getColumn(), name);
                }
            } else {
                ArrayVariableInitialization arrayVariableInitialization = (ArrayVariableInitialization) children.get(1);
                Identifier identifier = (Identifier) arrayVariableInitialization.getChildren().get(0);
                String name = identifier.getName();
                namesList.add(name);
                Symbol arraySymbol = new Symbol(name, typeString);
                arraySymbol.setDimensions(arrayVariableInitialization.getDimensionSizes());
                if (!symbolList.enterSymbol(arraySymbol)) {
                    throw new IdentifierDuplicateDefinitionError(tokenQueue.get(0).getRow(),
                            tokenQueue.get(0).getColumn(), name);
                }
            }
            return true;
        } else return false;
    }

    @Override
    public void rollBack(TokenQueue tokenQueue, SymbolList symbolList) {
        super.rollBack(tokenQueue, symbolList);
        for (String name : namesList) {
            symbolList.deleteSymbol(name);
        }
    }
}
