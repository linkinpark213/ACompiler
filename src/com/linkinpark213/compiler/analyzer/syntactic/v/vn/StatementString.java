package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement.*;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class StatementString extends VN {

    public StatementString() {
        super();

        /*
        * <Statement String>  ::=  <Statement> ; <Statement String>
        *                        | <Statement>
        * */
        ArrayList<V> multipleStatementProduction = new ArrayList<V>();
        ArrayList<V> singleStatementProduction = new ArrayList<V>();

        Statement firstStatement = new Statement();
        StatementString statementString = new StatementString();
        multipleStatementProduction.add(firstStatement);
        multipleStatementProduction.add(statementString);
        Statement statement = new Statement();
        singleStatementProduction.add(statement);

        productions.add(multipleStatementProduction);
        productions.add(singleStatementProduction);
    }
}
