package com.slaweklida;

import com.slaweklida.pieces.*;

import java.util.Locale;

public class Chessboard {

    private Field[][] fields;

    public Chessboard(boolean whitesFirst) {
        this.fields = new Field[8][8];
        //making whites
        this.fields[0][0] = new Field(new Rook(false), 'A', 1, true);
        this.fields[1][0] = new Field(new Knight(false), 'B', 1, false);
        this.fields[2][0] = new Field(new Bishop(false), 'C', 1, true);
        this.fields[3][0] = new Field(new Queen(false), 'D', 1, false);
        this.fields[4][0] = new Field(new King(false), 'E', 1, true);
        this.fields[5][0] = new Field(new Bishop(false), 'F', 1, false);
        this.fields[6][0] = new Field(new Knight(false), 'G', 1, true);
        this.fields[7][0] = new Field(new Rook(false), 'H', 1, false);

        //making white paws, row loop starts from 0
        for (int c = 0; c < 8; c++) {
            char letter = (char) (c + 65);
            this.fields[c][1] = new Field(new Pawn(false), letter, 2, false);
            c++;
            this.fields[c][1] = new Field(new Pawn(false), letter, 2, true);
        }

        //making battlefield
        for (int r = 2; r < 6; r++) {
            for (int c = 0; c < 8; c++) {
                char letter = (char) (c + 65);
                this.fields[c][r] = new Field(null, letter, r + 1, true);
                c++;
                letter = (char) (c + 65); //musimy zaktualizować literę kolumny
                this.fields[c][r] = new Field(null, letter, r + 1, false);
            }
            r++;
            for (int c = 0; c < 8; c++) { //drugi for ze zmienioną kolejnością koloru pól
                char letter = (char) (c + 65);
                this.fields[c][r] = new Field(null, letter, r + 1, false);
                c++;
                letter = (char) (c + 65); //musimy zaktualizować literę kolumny
                this.fields[c][r] = new Field(null, letter, r + 1, true);
            }
        }

        //making black paws, row loop starts from 0
        for (int c = 0; c < 8; c++) {
            char letter = (char) (c + 65);
            this.fields[c][6] = new Field(new Pawn(true), letter, 7, true);
            c++;
            this.fields[c][6] = new Field(new Pawn(true), letter, 7, false);
        }

        //making blacks
        this.fields[0][7] = new Field(new Rook(true), 'A', 8, false);
        this.fields[1][7] = new Field(new Knight(true), 'B', 8, true);
        this.fields[2][7] = new Field(new Bishop(true), 'C', 8, false);
        this.fields[3][7] = new Field(new Queen(true), 'D', 8, true);
        this.fields[4][7] = new Field(new King(true), 'E', 8, false);
        this.fields[5][7] = new Field(new Bishop(true), 'F', 8, true);
        this.fields[6][7] = new Field(new Knight(true), 'G', 8, false);
        this.fields[7][7] = new Field(new Rook(true), 'H', 8, true);
    }

    public void showChessboard() {
        for (int r = fields.length - 1; r >= 0; r--) {
            for (int c = 0; c < fields.length; c++) {
                System.out.print(this.fields[c][r].getFieldNameOrPiece() + "\t");
            }
            System.out.println();
        }

    }

    public void makeMove(String move){
        //np. d3, Ra4, Nc3 (Nac3), Bb2, Qf6 (Qcd3), Kh4
        String piece = "";
        String startColumnOrRow = "";
        String column = "";
        int row;

        if(move.length() == 2) { //jeśli d3, czyli pion
            column = "" + move.charAt(0);
            row = Integer.parseInt("" + move.charAt(1));

            Field tempField = this.fields[columnToNumber(column)][row - 1];
            System.out.println("Wybrane pole to: " + tempField.getFieldName());
        }

        if(move.length() == 3){ //jeśli np. Ra4
            piece = "" + move.charAt(0);
            column = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            row = Integer.parseInt("" + move.charAt(2));
        }

        if(move.length() == 4){ //jeśli np. Nac3
            piece = "" + move.charAt(0);
            startColumnOrRow = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            column = ("" + move.charAt(2)).toUpperCase(Locale.ROOT);
            row = Integer.parseInt("" + move.charAt(3));
        }

        //pawn



    }

    public int columnToNumber(String column){
        if (column.equals("A")) return 0;
        if (column.equals("B")) return 1;
        if (column.equals("C")) return 2;
        if (column.equals("D")) return 3;
        if (column.equals("E")) return 4;
        if (column.equals("F")) return 5;
        if (column.equals("G")) return 6;
        else return 7; //H
    }

    public Field[][] getFields() {
        return this.fields;
    }

    public Field getField(int c, int r){
        return this.fields[c][r];
    }


}
