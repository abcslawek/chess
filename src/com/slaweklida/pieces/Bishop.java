package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Bishop extends Piece {

    public Bishop(boolean isPieceBlack) {
        //super("B", (isPieceBlack ? 9821 : 9815), 0, 0, 0, 7, 7, isPieceBlack, 30); //przekazujemy tylko czy goniec jest biały czy czarny
        super("B", (isPieceBlack ? 118 : 98), 0, 0, 0, 7, 7, isPieceBlack, 30); //przekazujemy tylko czy goniec jest biały czy czarny
        //System.out.println("Stworzono gońca");
    }


}
