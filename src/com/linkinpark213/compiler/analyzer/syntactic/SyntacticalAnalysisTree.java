package com.linkinpark213.compiler.analyzer.syntactic;

import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class SyntacticalAnalysisTree {
    private VN root;

    public SyntacticalAnalysisTree() {

    }

    public VN getRoot() {
        return root;
    }

    public void setRoot(VN root) {
        this.root = root;
    }
}
