package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Queen extends Piece {

    public Queen(boolean isPieceBlack){
        //super("Q", (isPieceBlack ? 9819 : 9813), 7, 7, 7, 7, 7, isPieceBlack, 90); //przekazujemy tylko czy królowa jest biały czy czarny
        super("Q", (isPieceBlack ? 119 : 113), 7, 7, 7, 7, 7, isPieceBlack, 90); //przekazujemy tylko czy królowa jest biały czy czarny
        //System.out.println("Stworzono królową");
    }



}
