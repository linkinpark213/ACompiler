package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.VT;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class VN implements V, Cloneable {
    protected ArrayList<V> children;
    protected ArrayList<ArrayList<V>> productions;
    protected int productionNum;
    protected boolean nullable;
    protected ArrayList<Quad> quads;
    protected int tempID;
    protected String variableName;

    public VN() {
        children = new ArrayList<V>();
        productions = new ArrayList<ArrayList<V>>();
        nullable = false;
        quads = new ArrayList<Quad>();
        productionNum = -1;
        tempID = 0;
        variableName = "";
    }

    public void semanticAction(QuadQueue quadQueue) {
//        DFS
        for (V child : children) {
            if (child instanceof VN) {
                ((VN) child).semanticAction(quadQueue);
            }
        }
    }

    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        for (int i = 0; i < productions.size(); i++) {
            ArrayList<V> production = productions.get(i);
            if (production.size() == 0)
                return true;
            for (int j = 0; j < production.size(); j++) {
                V v = production.get(j);
                if (v instanceof VN) {
//                      Descend if it's a Vn
                    VN vn = (VN) v;
                    if (tokenQueue.size() == 0 && !((VN) v).isNullable()) break;
                    if (vn.analyze(tokenQueue, symbolList)) {
                        this.addChild(vn.getClone());
                        System.out.println("Adding child " + vn.getVariableName());
                    } else break;
                } else {
                    //  Move in if it's an expected Vt
                    VT vt = (VT) v;
                    if (tokenQueue.size() <= tokenQueue.getTotalTokenCount() - tokenQueue.getFarthestTokenNum()) {
                        tokenQueue.setFarthestTokenNum(tokenQueue.getTotalTokenCount() - tokenQueue.size());
                        tokenQueue.setFarthestExpectation(vt.toExactString());
                    }
                    if (tokenQueue.size() == 0) break;
                    if (vt.checkSymbol(tokenQueue.get(0), symbolList)) {
                        VT temp = vt.getClone();
                        temp.setToken(tokenQueue.remove(0));
                        this.addChild(temp);
                    } else break;
                }
                if (j == production.size() - 1) {
//                      If production-check is finished
                    productionNum = i;
                    return true;
                }
            }
//            This is when a production check fails
            rollBack(tokenQueue, symbolList);
            children.clear();
        }
        return false;
    }

    public void rollBack(TokenQueue tokenQueue, SymbolList symbolList) {
        for (int i = children.size() - 1; i >= 0; i--) {
            V v = children.get(i);
            if (v instanceof VN)
                ((VN) v).rollBack(tokenQueue, symbolList);
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

    public int getTempID() {
        return tempID;
    }

    public void setTempID(int tempID) {
        this.tempID = tempID;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        return "VN";
    }

    @Override
    public void printSyntacticalAnalysisTree(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t\t");
        }
        System.out.println(this.getClass().getSimpleName());
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printSyntacticalAnalysisTree(depth + 1);
        }
    }
}
