package com.slaweklida;

public abstract class Piece {
    private String name;
    private int range;
    private boolean isPieceBlack;

    public abstract void move();
    public abstract void attack();

    public String getName() {
        return name;
    }

    public boolean isPieceBlack() {
        return isPieceBlack;
    }
}
