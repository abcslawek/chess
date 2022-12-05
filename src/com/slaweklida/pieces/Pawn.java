package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isPieceBlack){
        super("P", isPieceBlack); //przekazujemy tylko czy pion jest biały czy czarny
        System.out.println("Stworzono piona");
    }

    public void move(){
        System.out.println("Wykonano ruch pionem");
    }

    public void attack(){
        System.out.println("Zaatakowano pionem");
    }

}
