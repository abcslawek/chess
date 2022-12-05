package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Queen extends Piece {
    private String name = "Q";
    private int range = 7;
    private boolean isPieceBlack;

    public Queen(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono królową");
    }

    public void move(){
        System.out.println("Wykonano ruch królową");
    }

    public void attack(){
        System.out.println("Zaatakowano królową");
    }
}
