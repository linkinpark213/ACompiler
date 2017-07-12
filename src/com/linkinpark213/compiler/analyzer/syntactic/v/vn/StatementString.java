package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement.*;
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
        * <Statement String>  ::=  <Statement> ; <Statement String>
        *                        | <Statement>
        * */
        Statement statement = new Statement();
        if (statement.analyze(this, symbolQueue)) {
            this.addChild(statement.clone());
            CommaSeparator commaSeparator = new CommaSeparator();
            if (commaSeparator.checkSymbol(symbolQueue.get(0))) {
                StatementString statementString = new StatementString();
                if (!statementString.analyze(this, symbolQueue))
                    return false;
            }
            return true;
        }
        return false;
    }
}
