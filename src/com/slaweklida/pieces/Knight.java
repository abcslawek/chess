package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Knight extends Piece {

    public Knight(boolean isPieceBlack){
        //super("N",(isPieceBlack ? 9822 : 9816),2, 2, 2, 0,0, isPieceBlack, 30); //przekazujemy tylko czy knight jest biały czy czarny
        super("N",(isPieceBlack ? 109 : 110),2, 2, 2, 0,0, isPieceBlack, 30); //przekazujemy tylko czy knight jest biały czy czarny
        //System.out.println("Stworzono gońca");
    }



}
