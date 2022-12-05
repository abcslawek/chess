package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Bishop extends Piece {

    public Bishop(boolean isPieceBlack){
        super("B", isPieceBlack); //przekazujemy tylko czy goniec jest biały czy czarny
        System.out.println("Stworzono gońca");
    }

    @Override
    public void move(){
        System.out.println("Wykonano ruch gońcem");
    }

    @Override
    public void attack(){
        System.out.println("Zaatakowano gońcem");
    }
}
