package com.slaweklida;

public class Result {
    private String bestMove;
    private int maxMinEval;

    public Result(String bestMove, int maxMinEval) {
        this.bestMove = bestMove;
        this.maxMinEval = maxMinEval;
    }

    public String getBestMove() {
        return bestMove;
    }

    public void setBestMove(String bestMove) {
        this.bestMove = bestMove;
    }

    public int getMaxMinEval() {
        return maxMinEval;
    }

    public void setMaxMinEval(int maxMinEval) {
        this.maxMinEval = maxMinEval;
    }
}
