package com.slaweklida;

public abstract class Piece {
    protected String name;
    protected int sidewaysRange;
    protected int forwardRange;
    protected int backwardRange;
    protected int diagonallyRange;
    protected boolean isPieceBlack;

    //constructor
    public Piece(String name, int sidewaysRange, int forwardRange, int backwardRange, int diagonallyRange, boolean isPieceBlack) {
        this.name = name;
        this.sidewaysRange = sidewaysRange;
        this.forwardRange = forwardRange;
        this.backwardRange = backwardRange;
        this.diagonallyRange = diagonallyRange;
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

    public int getDiagonallyRange() {
        return diagonallyRange;
    }

    public boolean isPieceBlack() {
        return isPieceBlack;
    }
}
