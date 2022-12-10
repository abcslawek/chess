package com.slaweklida;

import com.slaweklida.pieces.*;

import java.util.Locale;

public class Chessboard {

    private Field[][] fields;

    //constructor
    public Chessboard(boolean whitesFirst) {
        this.fields = new Field[8][8];
        //REMEMBER THAT e.g. D5 is [3][4] in arrays
        //making whites
        this.fields[0][0] = new Field(new Rook(false), 'A', 1, true);
        this.fields[1][0] = new Field(new Knight(false), 'B', 1, false);
        this.fields[2][0] = new Field(new Bishop(false), 'C', 1, true);
        this.fields[3][0] = new Field(new Queen(false), 'D', 1, false);
        this.fields[4][0] = new Field(new King(false), 'E', 1, true);
        this.fields[5][0] = new Field(new Bishop(false), 'F', 1, false);
        this.fields[6][0] = new Field(new Knight(false), 'G', 1, true);
        this.fields[7][0] = new Field(new Rook(false), 'H', 1, false);

        //making white paws
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

    //getters
    public Field[][] getFields() {
        return this.fields;
    }

    public Field getField(int c, int r) {
        return this.fields[c][r];
    }

    //other methods
    public void showChessboard() {
        for (int r = fields.length - 1; r >= 0; r--) {
            for (int c = 0; c < fields.length; c++) {
                System.out.print(this.fields[c][r].getFieldNameOrPiece() + "\t");
            }
            System.out.println();
        }
    }

    public void showReverseChessboard(){
        for (int r = 0; r <= fields.length - 1; r++) {
            for (int c = fields.length-1; c >= 0; c--) {
                System.out.print(this.fields[c][r].getFieldNameOrPiece() + "\t");
            }
            System.out.println();
        }
    }

    public boolean makeMove(String move, boolean whitesMove) {
        //np. d3, Ra4, Nc3 (Nac3), Bb2, Qf6 (Qcd3), Kh4

        if (move.length() == 2 || move.length() == 4) { //jeśli d3, czyli pion
            return movePawn(move, whitesMove);
        } else if (move.length() == 3) { //jeśli np. Ra4
            String piece = "" + move.charAt(0);
            String column = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            int row = Integer.parseInt("" + move.charAt(2));
        } else if (move.length() == 5) { //jeśli wieloznaczne np. Nac3
            String piece = "" + move.charAt(0);
            String startColumnOrRow = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            String column = ("" + move.charAt(2)).toUpperCase(Locale.ROOT);
            int row = Integer.parseInt("" + move.charAt(3));
        } else {
            System.out.println("makeMove() -> nie weszło do żadnej kategorii metody (move.length() == ?)");
            return false;
        }
        return false;
    }

    public boolean movePawn(String move, boolean whitesMove) {
        //spliting "move"
        String ourColumn = "";
        int ourRow = 0;
        String opponentsColumn = "";
        int opponentsRow = 0;

        boolean pawnFights = false;

        if(move.length() == 2) {
            ourColumn = "" + move.charAt(0);
            opponentsColumn = ourColumn;
            opponentsRow = Integer.parseInt("" + move.charAt(1));
        }else {
            System.out.println("Pion będzie bił");
            pawnFights = true;
            ourColumn = "" + move.charAt(0);
            opponentsColumn = "" + move.charAt(2);
            opponentsRow = Integer.parseInt("" + move.charAt(3));
        }

        //checking indicated row
        if (opponentsRow < 1 || opponentsRow > 8) { //jest 1,...,8 rzędów
            System.out.println("Jest tylko 8 rzędów, jeszcze raz");
            return false;
        }

        if (whitesMove && !pawnFights) {
            ourRow = whitePiecesRowInIndicatedColumn(ourColumn, "P");
            if (isIndicatedFieldInRangeOfPiece(ourColumn, ourRow, opponentsRow)) {
                if (hasIndicatedFieldOurWhitePiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnMoves(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        }else if(whitesMove && pawnFights){ //D(column):E(opponentsColumn)5(opponentsRow)
            System.out.println("Pion będzie bił (weszliśmy)");
            ourRow = whitePiecesRowInIndicatedColumn(ourColumn, "P");
            if (ourRow != 0 && isIndicatedOpponentInRangeOfOurPawn(ourColumn, ourRow, opponentsColumn, opponentsRow)){
                if (hasIndicatedFieldOurWhitePiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnKills(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        }else if (!whitesMove && !pawnFights) {
            ourRow = blackPiecesRowInIndicatedColumn(ourColumn, "P");
            if (isIndicatedFieldInRangeOfPiece(ourColumn, ourRow, opponentsRow)) {
                if (hasIndicatedFieldOurBlackPiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnMoves(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        }else if(!whitesMove && pawnFights){ //D(column):E(opponentsColumn)5(opponentsRow)
            System.out.println("Pion będzie bił (weszliśmy)");
            ourRow = blackPiecesRowInIndicatedColumn(ourColumn, "P");
            if (ourRow != 0 && isIndicatedOpponentInRangeOfOurPawn(ourColumn, ourRow, opponentsColumn, opponentsRow)){
                if (hasIndicatedFieldOurBlackPiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnKills(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        }


        return false;
    }

    public int whitePiecesRowInIndicatedColumn(String column, String name) {
        for (int r = 0; r < 8; r++) { //szuka czy w podanej kolumnie znajduje się pion
            if (this.fields[columnToNumber(column)][r].getPiece() != null && // i jeśli znalezione pole posiada bierkę
                    !this.fields[columnToNumber(column)][r].getPiece().isPieceBlack() && // i jeśli bierka jest biała
                    this.fields[columnToNumber(column)][r].getPiece().getName().equals(name)) { // i jeśli ta bierka to pion
                System.out.println("Znaleziono białą bierkę w kolumnie " + column + " i w rzędzie " + (r + 1));
                return r + 1;
            }
        }
        System.out.println("whitePiecesRowInIndicatedColumn() -> Nie znaleziono, return 0");
        return 0;
    }

    public int blackPiecesRowInIndicatedColumn(String column, String name) {
        for (int r = 0; r < 8; r++) { //szuka czy w podanej kolumnie znajduje się pion
            if (this.fields[columnToNumber(column)][r].getPiece() != null && // i jeśli znalezione pole posiada bierkę
                    this.fields[columnToNumber(column)][r].getPiece().isPieceBlack() && // i jeśli bierka jest czarna
                    this.fields[columnToNumber(column)][r].getPiece().getName().equals(name)) { // i jeśli ta bierka to pion
                System.out.println("Znaleziono czarną bierkę w kolumnie " + column + " i w rzędzie " + (r + 1));
                return r + 1;
            }
        }
        System.out.println("blackPiecesRowInIndicatedColumn() -> Nie znaleziono, return 0");
        return 0;
    }

    public boolean isIndicatedFieldInRangeOfPiece(String ourColumn, int ourRow, int opponentsRow) {
        //wskazany odjąć wyszukany
        if (Math.abs(rowToArrayRow(opponentsRow) - rowToArrayRow(ourRow)) <= this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getForwardRange() && Math.abs(rowToArrayRow(opponentsRow) - rowToArrayRow(ourRow)) > 0) { //czy pole jest w zasięgu piona
            System.out.println("Pole jest w zasięgu bierki");
            return true;
        } else {
            System.out.println("Pole nie jest w zasięgu bierki");
            return false;
        }
    }

    public boolean isIndicatedOpponentInRangeOfOurPawn(String ourColumn, int ourRow, String opponentsColumn, int opponentsRow){
            return (Math.abs(columnToNumber(ourColumn) - columnToNumber(opponentsColumn)) == 1 && Math.abs(rowToArrayRow(ourRow) - rowToArrayRow(opponentsRow)) == 1);
    }

    public boolean hasIndicatedFieldOurWhitePiece(String opponentsColumn, int opponentsRow) {
        return this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece() != null && !this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece().isPieceBlack;
    }

    public boolean hasIndicatedFieldOurBlackPiece(String column, int row) {
        return this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece() != null && this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece().isPieceBlack;
    }

    public boolean isIndicatedFieldEmpty(String column, int row) {
        return this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece() == null;
    }

    public boolean hasIndicatedFieldOpponentsBlackPiece(String column, int row) {
        return this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece() != null && this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece().isPieceBlack;
    }

    public boolean hasIndicatedFieldOpponentsWhitePiece(String column, int row) {
        return this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece() != null && !this.fields[columnToNumber(column)][rowToArrayRow(row)].getPiece().isPieceBlack;
    }

    public boolean moveOrFight(String column, int row, int ourPiecesRow) {
        if (isIndicatedFieldEmpty(column, row) || hasIndicatedFieldOpponentsBlackPiece(column, row) || hasIndicatedFieldOpponentsWhitePiece(column, row)) {
            this.fields[columnToNumber(column)][rowToArrayRow(row)].setPiece(this.fields[columnToNumber(column)][ourPiecesRow].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
            this.fields[columnToNumber(column)][ourPiecesRow].setPiece(null); //kasujemy piona ze starego piona
            return true;
        } else return false;
    }

    //pawn moves (wyjątkowa metoda tylko dla piona)
    public boolean pawnMoves(String opponentsColumn, int opponentsRow, String ourColumn, int ourRow){
        if (isIndicatedFieldEmpty(opponentsColumn, opponentsRow)) {
            this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
            this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(null); //kasujemy piona ze starego piona
            return true;
        } else return false;
    }
    //(wyjątkowa metoda tylko dla piona)
    public boolean pawnKills(String opponentsColumn, int opponentsRow, String ourColumn, int ourRow){
        if (hasIndicatedFieldOpponentsBlackPiece(opponentsColumn, opponentsRow) || hasIndicatedFieldOpponentsWhitePiece(opponentsColumn, opponentsRow)) {
            this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
            this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(null); //kasujemy piona ze starego piona
            return true;
        } else return false;
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

    public int rowToArrayRow(int row) {
        return row - 1;
    }


}
