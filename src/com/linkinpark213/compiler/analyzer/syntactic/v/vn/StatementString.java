package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement.*;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.separator.CommaSeparator;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class StatementString extends VN {

    @Override
    public boolean analyze(VN parent, ArrayList<Symbol> symbolQueue) {
        /*
        * <Statement String>  ::=  <Statement> <Statement String Alter>
        *                        | <Statement>
        * */
        ArrayList<V> statementWithStringProduction = new ArrayList<V>();
        ArrayList<V> singleStatementProduction = new ArrayList<V>();
        statementWithStringProduction.add(new Statement());
        statementWithStringProduction.add(new StatementStringAlter());
        singleStatementProduction.add(new Statement());
        productions.add(statementWithStringProduction);
        productions.add(singleStatementProduction);
        return super.analyze(parent, symbolQueue);
    }
}
