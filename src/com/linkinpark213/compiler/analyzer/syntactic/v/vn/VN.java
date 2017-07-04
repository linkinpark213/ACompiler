package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class VN implements V {
    protected ArrayList<V> children;
    protected ArrayList<ArrayList<V>> productions;

    public VN() {
        children = new ArrayList<V>();
        productions = new ArrayList<ArrayList<V>>();
    }

    public boolean analyze(VN parent, ArrayList<Symbol> symbolQueue) {
        for (int i = 0; i < productions.size(); i++) {
            ArrayList<V> production = productions.get(i);
            for (int j = 0; j < production.size(); j++) {
                V v = production.get(j);
                if (v instanceof VN) {
                    //  Descend if it's a Vn
                    VN vn = (VN) v;
                    if (vn.analyze(this, symbolQueue)) {
                        this.addChild(vn.clone());
                    } else break;
                } else {
                    //  Move in if it's an expected Vt
                    VT vt = (VT) v;
                    if (vt.checkSymbol(symbolQueue.get(0))) {
                        symbolQueue.remove(0);
                        this.addChild(vt.clone());
                    } else break;
                }
                if (j == production.size() - 1)
                    return true;
            }
        }
        return false;
    }

    public VN clone() {
        return this.clone();
    }

    public ArrayList<V> getChildren() {
        return children;
    }

    public void addChild(V child) {
        children.add(child);
    }
}
