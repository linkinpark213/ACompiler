package com.linkinpark213.compiler.analyzer.semantic;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/16 0016.
 */
public class QuadQueue {
    private ArrayList<Quad> quadList;
    private int tempID;

    public QuadQueue() {
        tempID = 0;
        quadList = new ArrayList<Quad>();
    }

    public void add(Quad quad) {
        quad.setAddress(quadList.size());
        quadList.add(quad);
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
            Quad quad = quadList.get(quadID);
            quad.setResult("" + targetQuadID);
            quadID = Integer.parseInt(quad.getResult());
        }
    }

    public void merge(int chainA, int chainB) {

    }
}
