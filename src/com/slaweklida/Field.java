package com.slaweklida;

import java.util.Locale;

public class Field {

    private Piece piece;
    private char column;
    private int row;
    private boolean isFieldBlack;

    //constructor
    public Field(Piece piece, char column, int row, boolean isFieldBlack) {
        this.piece = piece;
        this.column = column;
        this.row = row;
        this.isFieldBlack = isFieldBlack;
    }

    //getters
    public Piece getPiece() {
        return piece;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isFieldBlack() {
        return isFieldBlack;
    }

    //setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //other methods
    public String getFieldName() {
        return "" + this.column + Integer.toString(this.row);
    }




}
