package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Knight extends Piece {

    private String name = "Knight";
    private int range = 2;
    private boolean isPieceBlack;

    public Knight(boolean isPieceBlack){
        this.isPieceBlack = isPieceBlack;
        System.out.println("Stworzono skoczka");
    }

    public void move(){
        System.out.println("Wykonano ruch skoczkiem");
    }

    public void attack(){
        System.out.println("Zaatakowano skoczkiem");
    }

}
