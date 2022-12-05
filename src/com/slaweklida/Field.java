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

    public String getFieldNameOrPiece(){
        try {
            if(this.piece.isPieceBlack()) return this.piece.getName().toUpperCase(Locale.ROOT);
            else return this.piece.getName().toLowerCase(Locale.ROOT);
        }catch(NullPointerException e){ //jeśli na polu nie ma bierki
            if(this.isFieldBlack) return ("" + this.column + Integer.toString(this.row)).toUpperCase(Locale.ROOT);
            else return ("" + this.column + Integer.toString(this.row)).toLowerCase(Locale.ROOT);
        }
    }

    public String getFieldName(){
        return "" + this.column + Integer.toString(this.row);
    }

}
