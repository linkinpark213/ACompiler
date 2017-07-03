package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.syntactic.v.V;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public interface VN extends V {
    public void analyze();
    public ArrayList<V> getChildren();
    public void addChild();
}
