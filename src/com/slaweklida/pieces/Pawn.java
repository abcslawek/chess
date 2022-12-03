package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Pawn extends Piece {

    private String name = "Pawn";
    private int range = 1;
    private boolean isPieceBlack;

    public Pawn(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono piona");
    }

    public void move(){
        System.out.println("Wykonano ruch pionem");
    }

    public void attack(){
        System.out.println("Zaatakowano pionem");
    }

}
