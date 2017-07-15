package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class LoopStatement extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Loop Statement> ::= while ( <Expression> ) do { <Statement String> } ;
        *                   |   do { <Statement String> } while ( <Expression> ) ;
        * */
        ArrayList<V> whileDoProduction = new ArrayList<V>();
        ArrayList<V> doWhileProduction = new ArrayList<V>();
        whileDoProduction.add(new Keyword("while"));
        whileDoProduction.add(new Separator("("));
        whileDoProduction.add(new Expression());
        whileDoProduction.add(new Separator(")"));
        whileDoProduction.add(new Keyword("do"));
        whileDoProduction.add(new Separator("{"));
        whileDoProduction.add(new StatementString());
        whileDoProduction.add(new Separator("}"));
        whileDoProduction.add(new Separator(";"));

        doWhileProduction.add(new Keyword("do"));
        doWhileProduction.add(new Separator("{"));
        doWhileProduction.add(new StatementString());
        doWhileProduction.add(new Separator("}"));
        doWhileProduction.add(new Keyword("while"));
        doWhileProduction.add(new Separator("("));
        doWhileProduction.add(new Expression());
        doWhileProduction.add(new Separator(")"));
        doWhileProduction.add(new Separator(";"));
        productions.add(whileDoProduction);
        productions.add(doWhileProduction);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}

