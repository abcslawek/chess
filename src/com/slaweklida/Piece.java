package com.slaweklida;

public abstract class Piece {
    protected String name;

    protected int sidewaysRange;
    protected int forwardRange;
    protected int backwardRange;
    protected int diagonallyRange;

    protected boolean isPieceBlack;

    public Piece(String name, int sidewaysRange, int forwardRange, int backwardRange, int diagonallyRange, boolean isPieceBlack){
        this.name = name;
        this.sidewaysRange = sidewaysRange;
        this.forwardRange = forwardRange;
        this.backwardRange = backwardRange;
        this.diagonallyRange = diagonallyRange;
        this.isPieceBlack = isPieceBlack;
    }

    public String getName() {
        return name;
    }

    public boolean isPieceBlack() {
        return isPieceBlack;
    }
}
