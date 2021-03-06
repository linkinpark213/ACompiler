package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.CaseBlockString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.BooleanExpression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression.Expression;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class BranchStatement extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Branch Statement> ::= if ( <Expression> ) then { <Statement String> } else { <Statement String }
        *                      | if ( <Expression> ) then { <Statement String> }
        *                      | switch ( <Identifier> ) { <Case Block String> }
        * */
        ArrayList<V> withElseProduction = new ArrayList<V>();
        ArrayList<V> noElseProduction = new ArrayList<V>();
        ArrayList<V> switchCaseProduction = new ArrayList<V>();
        withElseProduction.add(new Keyword("if"));
        withElseProduction.add(new Separator("("));
        withElseProduction.add(new BooleanExpression());
        withElseProduction.add(new Separator(")"));
        withElseProduction.add(new Keyword("then"));
        withElseProduction.add(new Separator("{"));
        withElseProduction.add(new StatementString());
        withElseProduction.add(new Separator("}"));
        withElseProduction.add(new Keyword("else"));
        withElseProduction.add(new Separator("{"));
        withElseProduction.add(new StatementString());
        withElseProduction.add(new Separator("}"));

        noElseProduction.add(new Keyword("if"));
        noElseProduction.add(new Separator("("));
        noElseProduction.add(new BooleanExpression());
        noElseProduction.add(new Separator(")"));
        noElseProduction.add(new Keyword("then"));
        noElseProduction.add(new Separator("{"));
        noElseProduction.add(new StatementString());
        noElseProduction.add(new Separator("}"));

        switchCaseProduction.add(new Keyword("switch"));
        switchCaseProduction.add(new Separator("("));
        switchCaseProduction.add(new Identifier());
        switchCaseProduction.add(new Separator(")"));
        switchCaseProduction.add(new Separator("{"));
        switchCaseProduction.add(new CaseBlockString());
        switchCaseProduction.add(new Separator("}"));
        productions.add(withElseProduction);
        productions.add(noElseProduction);
        productions.add(switchCaseProduction);
        symbolList.openScope();
        boolean finished = super.analyze(tokenQueue, symbolList);
        symbolList.closeScope();
        if (!finished) rollBack(tokenQueue, symbolList);
        return finished;
    }

    @Override
    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Branch Statement> ::= if ( <Boolean Expression> ) then { <Statement String> } else { <Statement String }
        *                      | if ( <Boolean Expression> ) then { <Statement String> }
        *                      | switch ( <Identifier> ) { <Case Block String> }
        * */
//        super.semanticAction(quadQueue);
        Quad trueQuad;
        Quad falseQuad;
        Quad jumpQuad;
        BooleanExpression expression;
        Identifier identifier;
        StatementString statementString1, statementString2;
        CaseBlockString caseBlockString;
        switch (productionNum) {
            case 0:
                trueQuad = new Quad();
                falseQuad = new Quad();
                jumpQuad = new Quad();
                expression = (BooleanExpression) children.get(2);
                statementString1 = (StatementString) children.get(6);
                statementString2 = (StatementString) children.get(10);
                expression.semanticAction(quadQueue, symbolList);

                trueQuad.setOperator("jnz");
                trueQuad.setVariableA(expression.getVariableName());
                trueQuad.setVariableB("_");
                trueQuad.setResult("" + (quadQueue.nxq() + 2));
                quadQueue.add(trueQuad);

                falseQuad.setOperator("j");
                falseQuad.setVariableA("_");
                falseQuad.setVariableB("_");
                falseQuad.setResult("0");
                quadQueue.add(falseQuad);

                statementString1.semanticAction(quadQueue, symbolList);

                jumpQuad.setOperator("j");
                jumpQuad.setVariableA("_");
                jumpQuad.setVariableB("_");
                jumpQuad.setResult("0");
                quadQueue.add(jumpQuad);
                falseQuad.setResult("" + quadQueue.nxq());

                statementString2.semanticAction(quadQueue, symbolList);
                jumpQuad.setResult("" + quadQueue.nxq());
                break;
            case 1:
                trueQuad = new Quad();
                falseQuad = new Quad();
                expression = (BooleanExpression) children.get(2);
                statementString1 = (StatementString) children.get(6);
                expression.semanticAction(quadQueue, symbolList);

                trueQuad.setOperator("jnz");
                trueQuad.setVariableA(expression.getVariableName());
                trueQuad.setVariableB("_");
                trueQuad.setResult("" + (quadQueue.nxq() + 2));
                quadQueue.add(trueQuad);

                falseQuad.setOperator("j");
                falseQuad.setVariableA("_");
                falseQuad.setVariableB("_");
                falseQuad.setResult("0");
                quadQueue.add(falseQuad);

                statementString1.semanticAction(quadQueue, symbolList);

                falseQuad.setResult("" + quadQueue.nxq());
                break;
            case 2:
                identifier = (Identifier) children.get(2);
                caseBlockString = (CaseBlockString) children.get(5);
                caseBlockString.semanticAction(quadQueue, symbolList, identifier, 0);
                break;
        }
    }
}
