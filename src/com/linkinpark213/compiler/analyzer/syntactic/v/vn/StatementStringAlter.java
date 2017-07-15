package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/12 0012.
 */
public class StatementStringAlter extends VN {
    public StatementStringAlter() {
        super();
        this.nullable = true;
    }

    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue) {
        /*
        * <Statement String Alter> ::= <Semi-colon Separator> <Statement String>
        * */
        ArrayList<V> production = new ArrayList<V>();
        ArrayList<V> nullProduction = new ArrayList<V>();
        production.add(new Separator(";"));
        production.add(new StatementString());
        productions.add(production);
        productions.add(nullProduction);
        return super.analyze(parent, tokenQueue);
    }
}
