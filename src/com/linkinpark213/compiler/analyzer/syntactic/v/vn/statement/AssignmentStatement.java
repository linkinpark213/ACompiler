package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.operator.AssignmentOperator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class AssignmentStatement extends VN {
    public AssignmentStatement() {
        super();

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
    }
}
