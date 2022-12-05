package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isPieceBlack){
        super("P", 0, 1, 0, 0, isPieceBlack); //przekazujemy tylko czy pion jest biały czy czarny
        System.out.println("Stworzono piona");
    }


}
