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
            letter = (char) (c + 65);
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
            letter = (char) (c + 65);
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

    public boolean makeMove(String move, boolean whitesMove) {
        //np. d3, Ra4, Nc3 (Nac3), Bb2, Qf6 (Qcd3), Kh4
        String piece = "";
        String startColumnOrRow = "";
        String column = "";
        int row;

        if (move.length() == 2) { //jeśli d3, czyli pion
            column = "" + move.charAt(0);
            row = Integer.parseInt("" + move.charAt(1));

            int arrayColumn = columnToNumber(column); //zmieniamy numer kolumny na numer odpowiadający tabeli
            int arrayRow = row - 1; //zmieniamy numer rzędu na numer odpowiadający tabeli

            //tymczasowe sprawdzanie wprowadzonego pola przez gracza
            try {
                Field tempField = this.fields[arrayColumn][arrayRow];
                System.out.println("Wybrane pole to: " + tempField.getFieldName());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wybrane pole to: ---");
            }


            if (whitesMove && row < 9) { //jest tylko 8 rzędów
                for (int r = 0; r < 8; r++) { //szuka czy w podanej kolumnie znajduje się pion
                    if (this.fields[arrayColumn][r].getPiece() != null && // i jeśli znalezione pole posiada bierkę
                            !this.fields[arrayColumn][r].getPiece().isPieceBlack() && // i jeśli bierka jest biała
                            this.fields[arrayColumn][r].getPiece().getName().equals("P")) { // i jeśli ta bierka to pion
                        System.out.println("Znaleziono białego piona w kolumnie " + column + " i w rzędzie " + (r + 1)); // ERROR: PRZY PODANIU D7 WYSKAKUJE C7

                        if (arrayRow - r <= this.fields[arrayColumn][r].getPiece().getForwardRange() && arrayRow - r <= 1) { //czy pole jest w zasięgu piona
                            System.out.println("Pole jest w zasięgu piona");
                            if (this.fields[arrayColumn][arrayRow].getPiece() != null && !this.fields[arrayColumn][arrayRow].getPiece().isPieceBlack) { //WARIANT GDY NA WSKAZANYM POLU JEST NASZA BIERKA
                                System.out.println("Na tym polu znajduje się nasza bierka, jeszcze raz");
                                return false;
                            } else if (this.fields[arrayColumn][arrayRow].getPiece() == null) { //WARIANT GDY WSKAZANE POLE JEST PUSTE
                                System.out.println("Pole jest puste, wskakuj byku");
                                this.fields[arrayColumn][arrayRow].setPiece(this.fields[arrayColumn][r].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
                                this.fields[arrayColumn][r].setPiece(null); //kasujemy piona ze starego piona
                                return true;
                            }else if(this.fields[arrayColumn][arrayRow].getPiece() != null && this.fields[arrayColumn][arrayRow].getPiece().isPieceBlack) { //WARIANT GDY NA WSKAZANYM POLU JEST BIERKA PRZECIWNIKA
                                System.out.println("Na tym polu znajduje się bierka przeciwnika, wjeżdżaj w nią");
                                this.fields[arrayColumn][arrayRow].setPiece(this.fields[arrayColumn][r].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
                                this.fields[arrayColumn][r].setPiece(null); //kasujemy piona ze starego piona
                                return true;
                            }
                        } else {
                            System.out.println("Błędny ruch, pole poza zasięgiem piona");
                            return false;
                        }
                    }
                }
                System.out.println("Błędny ruch, jeszcze raz");
                return false;
            }
            if (!whitesMove && row < 9) {
                System.out.println("Ruchy czarnych w budowie");
                return true;
            }

        }
        if (move.length() == 3) { //jeśli np. Ra4
            piece = "" + move.charAt(0);
            column = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            row = Integer.parseInt("" + move.charAt(2));
        }
        if (move.length() == 4) { //jeśli np. Nac3
            piece = "" + move.charAt(0);
            startColumnOrRow = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            column = ("" + move.charAt(2)).toUpperCase(Locale.ROOT);
            row = Integer.parseInt("" + move.charAt(3));
        }
        System.out.println("Błędny ruch, jeszcze raz");
        return false;
    }


    public int columnToNumber(String column) {
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

    public Field getField(int c, int r) {
        return this.fields[c][r];
    }


}
