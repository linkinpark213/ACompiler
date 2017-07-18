package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.Symbol;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.ArrayDimensionError;
import com.linkinpark213.compiler.error.semantic.IdentifierIsNotArrayError;
import com.linkinpark213.compiler.error.semantic.IdentifierNotDefinedError;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/18 0018.
 */
public class ArrayVariable extends VN {
    private String offset;
    private String place;
    private int[] dimensions;

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Array Variable> ::= <Identifier> [ <Index List> ]
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Identifier());
        production.add(new Separator("["));
        production.add(new IndexList());
        production.add(new Separator("]"));
        productions.add(production);
        if (super.analyze(tokenQueue, symbolList)) {
            Identifier identifier = (Identifier) children.get(0);
            Symbol symbol = symbolList.retrieveSymbol(identifier.getName());
            if (symbol == null) {
                throw new IdentifierNotDefinedError(identifier.getToken().getRow(),
                        identifier.getToken().getColumn(), identifier.getName());
            }
            if (!symbol.isArray()) {
                throw new IdentifierIsNotArrayError(identifier.getToken().getRow(),
                        identifier.getToken().getColumn(), identifier.getName());
            }
            dimensions = symbol.getDimensions();
            IndexList indexList = (IndexList) children.get(2);
            ArrayList<String> indexNameList = new ArrayList<String>();
            indexList.getIndexNameList(indexNameList);
            if (dimensions.length != indexNameList.size()) {
                throw new ArrayDimensionError(identifier.getToken().getRow(),
                        identifier.getToken().getColumn(), identifier.getName());
            }
            return true;
        } else {
            rollBack(tokenQueue, symbolList);
            return false;
        }
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        Identifier identifier = (Identifier) children.get(0);
        IndexList indexList = (IndexList) children.get(2);
        ArrayList<String> variableNamesList = new ArrayList<String>();
        indexList.getIndexNameList(variableNamesList);
        String passedTemp = variableNamesList.get(0);
        for (int i = 0; i < variableNamesList.size() - 1; i++) {
            String tempString = "T" + quadQueue.newTemp();
            Quad multipleQuad = new Quad("*", passedTemp, "" + dimensions[i + 1], tempString);
            quadQueue.add(multipleQuad);
            Quad plusQuad = new Quad("+", variableNamesList.get(i + 1), tempString, tempString);
            quadQueue.add(plusQuad);
            passedTemp = tempString;
        }
        int c = 1;
        for (int i = 1; i < dimensions.length; i++) {
            c = c * dimensions[i];
            c++;
        }
        this.variableName = "T" + quadQueue.newTemp();
        this.offset = passedTemp;
        Quad addressCalculationQuad = new Quad("-", identifier.getName(), "" + c, getVariableName());
        quadQueue.add(addressCalculationQuad);
        this.place = addressCalculationQuad.getResult();
    }

    public String getOffset() {
        return offset;
    }

    public String getPlace() {
        return place;
    }
}
