package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class Rook extends Piece {

    public Rook(boolean isPieceBlack){
        super("R", isPieceBlack); //przekazujemy tylko czy wieża jest biały czy czarny
        System.out.println("Stworzono wieżę");
    }

    public void move(){
        System.out.println("Wykonano ruch wieżą");
    }

    public void attack(){
        System.out.println("Zaatakowano wieżą");
    }


}
