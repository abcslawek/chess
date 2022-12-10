package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Knight extends Piece {

    public Knight(boolean isPieceBlack){
        super("N",2, 2, 2, 0,0, isPieceBlack); //przekazujemy tylko czy knight jest biały czy czarny
        System.out.println("Stworzono gońca");
    }



}
