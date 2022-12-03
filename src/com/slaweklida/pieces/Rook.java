package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Rook extends Piece {
    private String name = "Rook";
    private int range = 7;
    private boolean isPieceBlack;

    public Rook(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono wieżę");
    }

    public void move(){
        System.out.println("Wykonano ruch wieżą");
    }

    public void attack(){
        System.out.println("Zaatakowano wieżą");
    }


}
