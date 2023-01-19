package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class King extends Piece {

    public King(boolean isPieceBlack){
        super("K", (isPieceBlack ? 9818 : 9812),1,1,1,1, 1, isPieceBlack, 900); //przekazujemy tylko czy king jest biały czy czarny
        //System.out.println("Stworzono króla");

    }
}
