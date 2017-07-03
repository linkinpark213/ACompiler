package com.linkinpark213.compiler.analyzer.syntactic.v.vn.expression;

import com.linkinpark213.compiler.analyzer.syntactic.v.vn.VN;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public interface Expression extends VN {
    public int getType();
    public int getValue();
}
