package com.slaweklida;

import java.util.Locale;

public class Field {

    private Piece piece;
    private char column;
    private int row;
    private boolean isFieldBlack;

    public Field(Piece piece, char column, int row, boolean isFieldBlack){
        this.piece = piece;
        this.column = column;
        this.row = row;
        this.isFieldBlack = isFieldBlack;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getFieldName(){
//        if(this.piece.isPieceBlack() && this.piece != null) return "b" + this.piece.getName();
//        else if(!this.piece.isPieceBlack() && this.piece != null)return "w" + this.piece.getName();
//        else if(this.isFieldBlack && this.piece == null) return "" + this.column + Integer.toString(this.row);
//        else if(!this.isFieldBlack && this.piece == null) return ("" + this.column + Integer.toString(this.row)).toLowerCase(Locale.ROOT);
//        else return "x";
        return this.piece.getName();
    }

}
