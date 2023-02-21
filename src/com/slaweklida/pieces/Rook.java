package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Rook extends Piece {

    public Rook(boolean isPieceBlack) {
        //super("R", (isPieceBlack ? 9820 : 9814), 7, 7, 7, 0, 0, isPieceBlack, 50); //przekazujemy tylko czy wieża jest biały czy czarny
        super("R", (isPieceBlack ? 116 : 114), 7, 7, 7, 0, 0, isPieceBlack, 50); //przekazujemy tylko czy wieża jest biały czy czarny
        //System.out.println("Stworzono wieżę");
    }


}
