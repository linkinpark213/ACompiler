package com.linkinpark213.compiler.analyzer.semantic;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class QuadQueue {
    private ArrayList<Quad> quadList;
    private int tempID;
    private static final int BASE_ADDRESS = 100;

    public QuadQueue() {
        tempID = 0;
        quadList = new ArrayList<Quad>();
    }

    public void add(Quad quad) {
        quad.setAddress(quadList.size() + BASE_ADDRESS);
        quadList.add(quad);
    }

    public int getAddress(int quadID) {
        return quadID + BASE_ADDRESS;
    }

    public int nxq() {
        return 100 + this.getQuadList().size();
    }

    public void remove(int index) {
        quadList.remove(index);
    }

    public int newTemp() {
        return ++tempID;
    }

    public ArrayList<Quad> getQuadList() {
        return quadList;
    }

    public void backPatch(int quadID, int targetQuadID) {
        while (quadID != 0) {
            Quad quad = quadList.get(quadID - BASE_ADDRESS);
            quadID = Integer.parseInt(quad.getResult());
            quad.setResult("" + (targetQuadID - BASE_ADDRESS));
        }
    }

    public void merge(int chainA, int chainB) {
        int quadID = Math.max(chainA, chainB);
        while (Integer.parseInt(quadList.get(quadID).getResult()) != 0) {
            Quad quad = quadList.get(quadID);
            quadID = Integer.parseInt(quad.getResult());
        }
        quadList.get(quadID).setResult("" + Math.min(chainA, chainB));
    }
}
