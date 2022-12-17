package com.slaweklida;

public abstract class Piece {
    protected String name;
    protected int sidewaysRange;
    protected int forwardRange;
    protected int backwardRange;
    protected int diagonallyForwardRange;
    protected int diagonallyBackwardRange;
    protected boolean isPieceBlack;

    //constructor
    public Piece(String name, int sidewaysRange, int forwardRange, int backwardRange, int diagonallyForwardRange, int diagonallyBackwardRange, boolean isPieceBlack) {
        this.name = name;
        this.sidewaysRange = sidewaysRange;
        this.forwardRange = forwardRange;
        this.backwardRange = backwardRange;
        this.diagonallyForwardRange = diagonallyForwardRange;
        this.diagonallyBackwardRange = diagonallyBackwardRange;
        this.isPieceBlack = isPieceBlack;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getSidewaysRange() {
        return sidewaysRange;
    }

    public int getForwardRange() {
        return forwardRange;
    }

    public int getBackwardRange() {
        return backwardRange;
    }

    public int getDiagonallyForwardRange() {
        return diagonallyForwardRange;
    }

    public boolean isPieceBlack() {
        return isPieceBlack;
    }

    //setters
    public void setForwardRange(int forwardRange) {
        this.forwardRange = forwardRange;
    }
}
