package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.ArrayVariable;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.AssignmentOperator;
import com.linkinpark213.compiler.error.semantic.IdentifierNotDefinedError;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class AssignmentStatement extends VN {

    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Assignment Statement> ::= <Identifier> := <Any Expression>
        *                           | <Array Variable> := <Any Expression>
        * */
        ArrayList<V> simpleVariableProduction = new ArrayList<V>();
        ArrayList<V> arrayVariableProduction = new ArrayList<V>();

        simpleVariableProduction.add(new Identifier());
        simpleVariableProduction.add(new AssignmentOperator());
        simpleVariableProduction.add(new Expression());

        arrayVariableProduction.add(new ArrayVariable());
        arrayVariableProduction.add(new AssignmentOperator());
        arrayVariableProduction.add(new Expression());

        productions.add(simpleVariableProduction);
        productions.add(arrayVariableProduction);

        if (super.analyze(tokenQueue, symbolList)) {
            Identifier targetIdentifier;
            if (children.get(0) instanceof Identifier)
                targetIdentifier = (Identifier) children.get(0);
            else targetIdentifier = (Identifier) ((ArrayVariable) children.get(0)).getChildren().get(0);
            if (symbolList.retrieveSymbol(targetIdentifier.getName()) == null) {
                throw new IdentifierNotDefinedError(targetIdentifier.getToken().getRow(),
                        targetIdentifier.getToken().getColumn(),
                        targetIdentifier.getName());
            }
            return true;
        }
        return false;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        super.semanticAction(quadQueue, symbolList);
        Expression expression = (Expression) children.get(2);
        if (children.get(0) instanceof Identifier) {
            Identifier identifier = (Identifier) children.get(0);
            Quad quad = new Quad();
            quad.setOperator(":=");
            quad.setVariableA(expression.getVariableName());
            quad.setVariableB("_");
            quad.setResult(identifier.getName());
            this.variableName = identifier.getName();
            quadQueue.add(quad);
        } else {
            ArrayVariable arrayVariable = (ArrayVariable) children.get(0);
            Quad quad = new Quad("[]=", expression.getVariableName(), "_",
                    arrayVariable.getPlace() + "[" + arrayVariable.getOffset() + "]");
            quadQueue.add(quad);
        }
    }
}
