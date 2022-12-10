package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Bishop extends Piece {

    public Bishop(boolean isPieceBlack){
        super("B",0,0,0,7, 7, isPieceBlack); //przekazujemy tylko czy goniec jest biały czy czarny
        System.out.println("Stworzono gońca");
    }



}
