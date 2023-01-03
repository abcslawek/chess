package com.slaweklida.pieces;

import com.slaweklida.Piece;

public class King extends Piece {

//    private char column;
//    private int row;
//
    public King(boolean isPieceBlack){
        super("K", (isPieceBlack ? 9818 : 9812),1,1,1,1, 1, isPieceBlack); //przekazujemy tylko czy king jest biały czy czarny
        System.out.println("Stworzono króla");

    }

//    public char getColumn() {
//        return column;
//    }
//
//    public void setColumn(char column) {
//        this.column = column;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public void setRow(int row) {
//        this.row = row;
//    }
}
