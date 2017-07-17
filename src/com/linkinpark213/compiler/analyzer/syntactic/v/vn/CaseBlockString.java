package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class CaseBlockString extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        * <Case Block String> ::= <Case Block> ; <Case Block String>
        *                       | <Case Block>
        * */
        ArrayList<V> moreCaseProduction = new ArrayList<V>();
        ArrayList<V> noMoreCaseProduction = new ArrayList<V>();
        moreCaseProduction.add(new CaseBlock());
        moreCaseProduction.add(new Separator(";"));
        moreCaseProduction.add(new CaseBlockString());
        noMoreCaseProduction.add(new CaseBlock());
        productions.add(moreCaseProduction);
        productions.add(noMoreCaseProduction);
        return super.analyze(tokenQueue, symbolList);
    }

    public void semanticAction(QuadQueue quadQueue, Identifier identifier, int chain) {
        CaseBlock caseBlock = (CaseBlock) children.get(0);
        caseBlock.semanticAction(quadQueue, identifier, chain);
        if (productionNum == 0) {
//            Merge the chains
            CaseBlockString caseBlockString = (CaseBlockString) children.get(2);
            caseBlockString.semanticAction(quadQueue, identifier, quadQueue.nxq() - 1);
        } else {
//            Time to back patch
            quadQueue.backPatch(quadQueue.nxq() - 1, quadQueue.nxq());
        }
    }
}
