package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class King extends Piece {
    private String name = "K";
    private int range = 1;
    private boolean isPieceBlack;

    public King(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono króla");
    }

    public void move(){
        System.out.println("Wykonano ruch królem");
    }

    public void attack(){
        System.out.println("Zaatakowano królem");
    }
}
