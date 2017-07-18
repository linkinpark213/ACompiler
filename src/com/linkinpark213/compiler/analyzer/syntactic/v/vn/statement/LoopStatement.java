package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class LoopStatement extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Loop Statement> ::= while ( <Expression> ) do { <Statement String> }
        *                   |   do { <Statement String> } while ( <Expression> )
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

        doWhileProduction.add(new Keyword("do"));
        doWhileProduction.add(new Separator("{"));
        doWhileProduction.add(new StatementString());
        doWhileProduction.add(new Separator("}"));
        doWhileProduction.add(new Keyword("while"));
        doWhileProduction.add(new Separator("("));
        doWhileProduction.add(new Expression());
        doWhileProduction.add(new Separator(")"));
        productions.add(whileDoProduction);
        productions.add(doWhileProduction);
        symbolList.openScope();
        boolean finished = super.analyze(tokenQueue, symbolList);
        symbolList.closeScope();
        return finished;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        Expression expression;
        StatementString statementString;
        Quad checkQuad = new Quad();
        Quad falseQuad = new Quad();
        Quad returnQuad = new Quad();
        switch (productionNum) {
            case 0:
//                while-do
                expression = (Expression) children.get(2);
                statementString = (StatementString) children.get(6);
                expression.semanticAction(quadQueue);
                checkQuad.setOperator("jnz");
                checkQuad.setVariableA(expression.getVariableName());
                checkQuad.setVariableB("_");
                checkQuad.setResult("0");
                quadQueue.add(checkQuad);

                falseQuad.setOperator("j");
                falseQuad.setVariableA("_");
                falseQuad.setVariableB("_");
                falseQuad.setResult("0");
                quadQueue.add(falseQuad);

                checkQuad.setResult("" + quadQueue.nxq());

                statementString.semanticAction(quadQueue);

                returnQuad.setOperator("j");
                returnQuad.setVariableA("_");
                returnQuad.setVariableB("_");
                returnQuad.setResult("" + checkQuad.getAddress());
                quadQueue.add(returnQuad);
                falseQuad.setResult("" + quadQueue.nxq());
                break;
            case 1:
                statementString = (StatementString) children.get(2);
                expression = (Expression) children.get(6);
                int backAddress = quadQueue.nxq();
                statementString.semanticAction(quadQueue);
                checkQuad.setOperator("jnz");
                checkQuad.setVariableA(expression.getVariableName());
                checkQuad.setVariableB("_");
                checkQuad.setResult("" + backAddress);
                quadQueue.add(checkQuad);
                break;
        }
    }
}

