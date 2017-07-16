package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
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
    protected ArrayList<Quad> quads;

    public VN() {
        children = new ArrayList<V>();
        productions = new ArrayList<ArrayList<V>>();
        nullable = false;
        quads = new ArrayList<Quad>();
    }

    protected void semanticAction(int productionNum) {
        //  Do nothing?
    }

    protected void semanticRollback() {
        //  Will be implemented in the children classes
    }

    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        for (int i = 0; i < productions.size(); i++) {
            ArrayList<V> production = productions.get(i);
            if (production.size() == 0) return true;
            for (int j = 0; j < production.size(); j++) {
                V v = production.get(j);
                if (v instanceof VN) {
                    //  Descend if it's a Vn
                    VN vn = (VN) v;
                    if (tokenQueue.size() == 0 && !((VN) v).isNullable()) break;
                    if (vn.analyze(this, tokenQueue, quadQueue)) {
                        this.addChild(vn.getClone());
                    } else break;
                } else {
                    //  Move in if it's an expected Vt
                    VT vt = (VT) v;
                    if (tokenQueue.size() == 0) break;
                    if (vt.checkSymbol(tokenQueue.get(0))) {
                        VT temp = vt.getClone();
                        temp.setToken(tokenQueue.remove(0));
                        this.addChild(temp);
                    } else break;
                }
                if (j == production.size() - 1) {
                    //  If production-check is finished
                    //  Perform semantic actions here
                    semanticAction(i);
                    return true;
                }
            }
            returnTokens(tokenQueue);
            children.clear();
        }
        return false;
    }

    public void returnTokens(ArrayList<Token> tokenQueue) {
        for (int i = children.size() - 1; i >= 0; i--) {
            V v = children.get(i);
            if (v instanceof VN)
                ((VN) v).returnTokens(tokenQueue);
            else {
                tokenQueue.add(0, ((VT) v).getToken());
            }
        }
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
