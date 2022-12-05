package com.slaweklida;

public abstract class Piece {
    protected String name;
    protected int range;
    protected boolean isPieceBlack;

    public Piece(String name, boolean isPieceBlack){
        this.name = name;
        this.isPieceBlack = isPieceBlack;
    }

    public void move(){
        System.out.println("Piece moved");
    }
    public void attack(){
        System.out.println("Piece attacked");
    }

    public String getName() {
        return name;
    }

    public boolean isPieceBlack() {
        return isPieceBlack;
    }
}
