package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Bishop extends Piece {
    private String name = "Bishop";
    private int range = 7;
    private boolean isPieceBlack;

    public Bishop(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono gońca");
    }

    public void move(){
        System.out.println("Wykonano ruch gońcem");
    }

    public void attack(){
        System.out.println("Zaatakowano gońcem");
    }
}
