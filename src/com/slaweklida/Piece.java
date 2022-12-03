package com.slaweklida;

public abstract class Piece {
    private String name;
    private int range;
    private boolean isPieceBlack;

    public abstract void move();
    public abstract void attack();
}
