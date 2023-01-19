package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Pawn extends Piece {

    public Pawn(boolean isPieceBlack) {
        super("P", (isPieceBlack ? 9823 : 9817), 0, 1, 0, 0, 0, isPieceBlack, 10); //przekazujemy tylko czy pion jest biały czy czarny
        //System.out.println("Stworzono piona");
    }


}
