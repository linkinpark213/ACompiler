package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.syntactic.v.vn.StatementString;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class SyntacticalAnalysisTree {
    private StatementString root;

    public SyntacticalAnalysisTree() {
        root = new StatementString();
    }

    public VN getRoot() {
        return root;
    }

    public void setRoot(StatementString root) {
        this.root = root;
    }
}
