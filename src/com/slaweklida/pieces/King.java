package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class King extends Piece {

    public King(boolean isPieceBlack){
        super("K", isPieceBlack); //przekazujemy tylko czy king jest biały czy czarny
        System.out.println("Stworzono króla");
    }

    public void move(){
        System.out.println("Wykonano ruch królem");
    }

    public void attack(){
        System.out.println("Zaatakowano królem");
    }
}
