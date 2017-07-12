package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.symbols.Symbol;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class VN implements V, Cloneable {
    protected ArrayList<V> children;
    protected ArrayList<ArrayList<V>> productions;
    protected boolean nullable;

    public VN() {
        children = new ArrayList<V>();
        productions = new ArrayList<ArrayList<V>>();
        nullable = false;
    }

    public boolean analyze(VN parent, ArrayList<Symbol> symbolQueue) {
        for (int i = 0; i < productions.size(); i++) {
            ArrayList<V> production = productions.get(i);
            if (production.size() == 0) return true;
            ArrayList<Symbol> removedSymbols = new ArrayList<Symbol>();
            for (int j = 0; j < production.size(); j++) {
                V v = production.get(j);
                if (v instanceof VN) {
                    //  Descend if it's a Vn
                    VN vn = (VN) v;
                    if (symbolQueue.size() == 0 && !((VN) v).isNullable()) break;
                    if (vn.analyze(this, symbolQueue)) {
                        this.addChild(vn.getClone());
                    } else break;
                } else {
                    //  Move in if it's an expected Vt
                    VT vt = (VT) v;
                    if (symbolQueue.size() == 0) break;
                    if (vt.checkSymbol(symbolQueue.get(0))) {
                        removedSymbols.add(0, symbolQueue.remove(0));
                        this.addChild(vt.getClone());
                    } else break;
                }
                if (j == production.size() - 1) {
                    //  If production-check is finished
                    return true;
                }
            }
            for (Symbol removedSymbol : removedSymbols
                    ) {
                symbolQueue.add(0, removedSymbol);
            }
        }
        return false;
    }

    public VN getClone() {
        try {
            return (VN) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<V> getChildren() {
        return children;
    }

    public void addChild(V child) {
        children.add(child);
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public String toString() {
        return "VN";
    }

    @Override
    public void printTree(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t\t");
        }
        System.out.println(this.getClass().getSimpleName());
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printTree(depth + 1);
        }
    }
}
