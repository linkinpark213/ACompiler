package com.linkinpark213.compiler.analyzer.semantic;

import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;
import com.linkinpark213.compiler.error.AnalysisError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class SemanticAnalyzer {

    public void analyze(Program program, QuadQueue quadQueue, SymbolList symbolList) throws AnalysisError {
        program.semanticAction(quadQueue, symbolList);
    }
}
