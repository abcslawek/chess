package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Rook extends Piece {

    public Rook(boolean isPieceBlack) {
        super("R", 7, 7, 7, 0, 0, isPieceBlack); //przekazujemy tylko czy wieża jest biały czy czarny
        System.out.println("Stworzono wieżę");
    }


}
