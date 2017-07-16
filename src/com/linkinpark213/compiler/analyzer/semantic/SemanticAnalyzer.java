package com.linkinpark213.compiler.analyzer.semantic;

import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class SemanticAnalyzer {
    private SymbolList symbolList;
    private ArrayList<Quad> quadQueue;

    public SemanticAnalyzer() {
        symbolList = new SymbolList();
        quadQueue = new ArrayList<Quad>();
    }

    public void analyze(Program program, QuadQueue quadQueue) {
        program.semanticAction(quadQueue);
    }

    public SymbolList getSymbolList() {
        return symbolList;
    }

    public ArrayList<Quad> getQuadQueue() {
        return quadQueue;
    }
}
