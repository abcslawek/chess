package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class King extends Piece {

    public King(boolean isPieceBlack){
        super("K",1,1,1,1, isPieceBlack); //przekazujemy tylko czy king jest biały czy czarny
        System.out.println("Stworzono króla");
    }



}
