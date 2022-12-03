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

    public String getFieldName(){
        if(this.isFieldBlack) return "B";//("" + this.column + Integer.toString(this.row)).toLowerCase(Locale.ROOT);
        else return "W";//"" + this.column + Integer.toString(this.row);

    }

}
