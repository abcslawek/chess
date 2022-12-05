package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Knight extends Piece {

    public Knight(boolean isPieceBlack){
        super("N", isPieceBlack); //przekazujemy tylko czy knight jest biały czy czarny
        System.out.println("Stworzono gońca");
    }

    public void move(){
        System.out.println("Wykonano ruch skoczkiem");
    }

    public void attack(){
        System.out.println("Zaatakowano skoczkiem");
    }

}
