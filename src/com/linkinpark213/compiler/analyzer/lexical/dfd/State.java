package com.linkinpark213.compiler.analyzer.lexical.dfd;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class State {
    private int stateNum;
    private ArrayList<InputHandler> inputHandlers;
    private ArrayList<State> nextStateList;

    public State(int stateNum) {
        this.stateNum = stateNum;
        inputHandlers = new ArrayList<InputHandler>();
        nextStateList = new ArrayList<State>();
    }

    public void addNextState(State nextState, InputHandler e) {
        inputHandlers.add(e);
        nextStateList.add(nextState);
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }

    public State nextState(char c) {
        for (int i = 0; i < inputHandlers.size(); i++) {
            if(inputHandlers.get(i).handle(c)) {
                return nextStateList.get(i);
            }
        }
        return null;
    }
}
