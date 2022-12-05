package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Queen extends Piece {

    public Queen(boolean isPieceBlack){
        super("Q", isPieceBlack); //przekazujemy tylko czy królowa jest biały czy czarny
        System.out.println("Stworzono królową");
    }

    public void move(){
        System.out.println("Wykonano ruch królową");
    }

    public void attack(){
        System.out.println("Zaatakowano królową");
    }
}
