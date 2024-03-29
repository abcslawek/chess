package com.slaweklida;

public abstract class Piece {
    protected String name;
    protected int image;
    protected int sidewaysRange;
    protected int forwardRange;
    protected int backwardRange;
    protected int diagonallyForwardRange;
    protected int diagonallyBackwardRange;
    protected boolean isPieceBlack;
    protected int weight;

    //constructor
    public Piece(String name, int image, int sidewaysRange, int forwardRange, int backwardRange, int diagonallyForwardRange, int diagonallyBackwardRange, boolean isPieceBlack, int weight) {
        this.name = name;
        this.image = image;
        this.sidewaysRange = sidewaysRange;
        this.forwardRange = forwardRange;
        this.backwardRange = backwardRange;
        this.diagonallyForwardRange = diagonallyForwardRange;
        this.diagonallyBackwardRange = diagonallyBackwardRange;
        this.isPieceBlack = isPieceBlack;
        this.weight = weight;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
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

    public int getWeight() {
        return weight;
    }

    //setters
    public void setForwardRange(int forwardRange) {
        this.forwardRange = forwardRange;
    }
}
