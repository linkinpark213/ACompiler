package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
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
        * */
        ArrayList<V> production = new ArrayList<V>();
        Identifier identifier = new Identifier();
        AssignmentOperator assignmentOperator = new AssignmentOperator();
        Expression expression = new Expression();
        production.add(identifier);
        production.add(assignmentOperator);
        production.add(expression);

        productions.add(production);

        if (super.analyze(tokenQueue, symbolList)) {
            Identifier targetIdentifier = (Identifier) children.get(0);
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
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        Identifier identifier = (Identifier) children.get(0);
        Expression expression = (Expression) children.get(2);
        Quad quad = new Quad();
        quad.setOperator(":=");
        quad.setVariableA(expression.getVariableName());
        quad.setVariableB("_");
        quad.setResult(identifier.getName());
        this.variableName = identifier.getName();
        quadQueue.add(quad);
    }
}
