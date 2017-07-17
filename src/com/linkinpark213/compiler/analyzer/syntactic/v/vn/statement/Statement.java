package com.linkinpark213.compiler.analyzer.syntactic.v.vn.statement;

import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class Statement extends VN {
    public Statement() {
        super();

        /*
        * <Statement>  ::=  <Definition Statement>
        *                   | <Procedure Definition Statement>
        *                   | <Procedure Call Statement>
        *                   | <Assignment Statement>
        *                   | <Branch Statement>
        *                   | <Loop Statement>
        * */
        ArrayList<V> definitionProduction = new ArrayList<V>();
        ArrayList<V> procedureDefinitionProduction = new ArrayList<V>();
        ArrayList<V> procedureCallProduction = new ArrayList<V>();
        ArrayList<V> assignmentProduction = new ArrayList<V>();
        ArrayList<V> branchProduction = new ArrayList<V>();
        ArrayList<V> loopProduction = new ArrayList<V>();

        DefinitionStatement definitionStatement = new DefinitionStatement();
        definitionProduction.add(definitionStatement);
        ProcedureDefinitionStatement procedureDefinitionStatement = new ProcedureDefinitionStatement();
        procedureDefinitionProduction.add(procedureDefinitionStatement);
        ProcedureCallStatement procedureCallStatement = new ProcedureCallStatement();
        procedureCallProduction.add(procedureCallStatement);
        AssignmentStatement assignmentStatement = new AssignmentStatement();
        assignmentProduction.add(assignmentStatement);
        BranchStatement branchStatement = new BranchStatement();
        branchProduction.add(branchStatement);
        LoopStatement loopStatement = new LoopStatement();
        loopProduction.add(loopStatement);

        productions.add(definitionProduction);
        productions.add(procedureDefinitionProduction);
        productions.add(procedureCallProduction);
        productions.add(assignmentProduction);
        productions.add(branchProduction);
        productions.add(loopProduction);
    }

    @Override
    public void semanticAction(QuadQueue quadQueue) {
        super.semanticAction(quadQueue);
        switch (productionNum) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
