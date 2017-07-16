package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.CaseBlockString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BranchStatement extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Branch Statement> ::= if ( <Expression> ) then { <Statement String> };
        *                      | if ( <Expression> ) then { <Statement String> } else { <Statement String };
        *                      | switch ( <Identifier> ) { <Case Block String> };
        * */
        ArrayList<V> noElseProduction = new ArrayList<V>();
        ArrayList<V> withElseProduction = new ArrayList<V>();
        ArrayList<V> switchCaseProduction = new ArrayList<V>();
        noElseProduction.add(new Keyword("if"));
        noElseProduction.add(new Separator("("));
        noElseProduction.add(new Expression());
        noElseProduction.add(new Separator(")"));
        noElseProduction.add(new Keyword("then"));
        noElseProduction.add(new Separator("{"));
        noElseProduction.add(new StatementString());
        noElseProduction.add(new Separator("}"));

        withElseProduction.add(new Keyword("if"));
        withElseProduction.add(new Separator("("));
        withElseProduction.add(new Expression());
        withElseProduction.add(new Separator(")"));
        withElseProduction.add(new Keyword("then"));
        withElseProduction.add(new Separator("{"));
        withElseProduction.add(new StatementString());
        withElseProduction.add(new Separator("}"));
        withElseProduction.add(new Keyword("else"));
        withElseProduction.add(new Separator("{"));
        withElseProduction.add(new StatementString());
        withElseProduction.add(new Separator("}"));

        switchCaseProduction.add(new Keyword("switch"));
        switchCaseProduction.add(new Separator("("));
        switchCaseProduction.add(new Identifier());
        switchCaseProduction.add(new Separator(")"));
        switchCaseProduction.add(new Separator("{"));
        switchCaseProduction.add(new CaseBlockString());
        switchCaseProduction.add(new Separator("}"));
        productions.add(noElseProduction);
        productions.add(withElseProduction);
        productions.add(switchCaseProduction);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
