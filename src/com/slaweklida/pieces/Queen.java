package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Queen extends Piece {

    public Queen(boolean isPieceBlack){
        super("Q", (isPieceBlack ? 9819 : 9813), 7, 7, 7, 7, 7, isPieceBlack); //przekazujemy tylko czy królowa jest biały czy czarny
        System.out.println("Stworzono królową");
    }



}
