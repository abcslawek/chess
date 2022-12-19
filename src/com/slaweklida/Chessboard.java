package com.slaweklida;

import com.slaweklida.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Chessboard {

    private Field[][] fields;

    //constructor
//    public Chessboard(boolean whitesFirst) {
//        this.fields = new Field[8][8];
//        //REMEMBER THAT e.g. D5 is [3][4] in arrays
//        //making whites
//        this.fields[0][0] = new Field(new Rook(false), 'A', 1, true);
//        this.fields[1][0] = new Field(new Knight(false), 'B', 1, false);
//        this.fields[2][0] = new Field(new Bishop(false), 'C', 1, true);
//        this.fields[3][0] = new Field(new Queen(false), 'D', 1, false);
//        this.fields[4][0] = new Field(new King(false), 'E', 1, true);
//        this.fields[5][0] = new Field(new Bishop(false), 'F', 1, false);
//        this.fields[6][0] = new Field(new Knight(false), 'G', 1, true);
//        this.fields[7][0] = new Field(new Rook(false), 'H', 1, false);
//
//        //making white paws
//        for (int c = 0; c < 8; c++) {
//            char letter = (char) (c + 65);
//            this.fields[c][1] = new Field(new Pawn(false), letter, 2, false);
//            c++;
//            letter = (char) (c + 65);
//            this.fields[c][1] = new Field(new Pawn(false), letter, 2, true);
//        }
//
//        //making battlefield
//        for (int r = 2; r < 6; r++) {
//            for (int c = 0; c < 8; c++) {
//                char letter = (char) (c + 65);
//                this.fields[c][r] = new Field(null, letter, r + 1, true);
//                c++;
//                letter = (char) (c + 65); //musimy zaktualizować literę kolumny
//                this.fields[c][r] = new Field(null, letter, r + 1, false);
//            }
//            r++;
//            for (int c = 0; c < 8; c++) { //drugi for ze zmienioną kolejnością koloru pól
//                char letter = (char) (c + 65);
//                this.fields[c][r] = new Field(null, letter, r + 1, false);
//                c++;
//                letter = (char) (c + 65); //musimy zaktualizować literę kolumny
//                this.fields[c][r] = new Field(null, letter, r + 1, true);
//            }
//        }
//
//        //making black paws, row loop starts from 0
//        for (int c = 0; c < 8; c++) {
//            char letter = (char) (c + 65);
//            this.fields[c][6] = new Field(new Pawn(true), letter, 7, true);
//            c++;
//            letter = (char) (c + 65);
//            this.fields[c][6] = new Field(new Pawn(true), letter, 7, false);
//        }
//
//        //making blacks
//        this.fields[0][7] = new Field(new Rook(true), 'A', 8, false);
//        this.fields[1][7] = new Field(new Knight(true), 'B', 8, true);
//        this.fields[2][7] = new Field(new Bishop(true), 'C', 8, false);
//        this.fields[3][7] = new Field(new Queen(true), 'D', 8, true);
//        this.fields[4][7] = new Field(new King(true), 'E', 8, false);
//        this.fields[5][7] = new Field(new Bishop(true), 'F', 8, true);
//        this.fields[6][7] = new Field(new Knight(true), 'G', 8, false);
//        this.fields[7][7] = new Field(new Rook(true), 'H', 8, true);
//    }

    //test chessboard
    public Chessboard(boolean whitesFirst) {
        this.fields = new Field[8][8];
        //making battlefield
        for (int r = 0; r < 8; r++) {
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

        this.fields[3][4].setPiece(new Queen(false));
        this.fields[3][2].setPiece(new Pawn(false));
        this.fields[3][1].setPiece(new Bishop(false));
        this.fields[5][6].setPiece(new Pawn(true));
        this.fields[6][7].setPiece(new Bishop(true));
        this.fields[1][4].setPiece(new Knight(true));
        this.fields[7][4].setPiece(new Knight(false));
        this.fields[5][2].setPiece(new Knight(true));
        this.fields[6][1].setPiece(new Knight(true));
        this.fields[3][5].setPiece(new Bishop(true));
        this.fields[2][3].setPiece(new Rook(false));
        this.fields[1][7].setPiece(new King(true));
        this.fields[1][0].setPiece(new King(false));


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
        availableQueensMoves("Q", true);
    }

    public void showReverseChessboard() {
        for (int r = 0; r <= fields.length - 1; r++) {
            for (int c = fields.length - 1; c >= 0; c--) {
                System.out.print(this.fields[c][r].getFieldNameOrPiece() + "\t");
            }
            System.out.println();
        }
    }

    public boolean makeMove(String move, boolean whitesMove) {
        //np. d3, Ra4, Nc3 (Nac3), Bb2, Qf6 (Qcd3), Kh4

        if (move.length() == 2 || move.length() == 4) { //jeśli d3, czyli pion
            return movePawn(move.toUpperCase(Locale.ROOT), whitesMove);
        } else if (move.length() == 3) { //jeśli np. Ra4
            String piece = "" + move.charAt(0);
            String column = ("" + move.charAt(1)).toUpperCase(Locale.ROOT);
            int row = Integer.parseInt("" + move.charAt(2));
            //List<Field> availableMoves = availableMoves(piece, whitesMove);
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

    public List<Field> availableQueensMoves(String piece, boolean whitesMove) { //bierki z ourColumn i ourRow
        List<Field> availableHorizontalFields = new ArrayList<>();
        List<Field> availableVerticalFields = new ArrayList<>();
        List<Field> availableRisingDiagonalFields = new ArrayList<>();
        List<Field> availableFallingDiagonalFields = new ArrayList<>();
        List<Field> availableFields = new ArrayList<>();

        //whitesMove
        for (int c = 0; c < 8; c++) {
            for (int r = 0; r < 8; r++) {
                //boolean encounteredBlack = false;
                if (this.fields[c][r].getPiece() != null && this.fields[c][r].getPiece().getName().equals(piece) && !this.fields[c][r].getPiece().isPieceBlack) {
                    Piece temp = this.fields[c][r].getPiece();
                    System.out.println("Znaleziona biała królowa: " + temp.getName() + " na polu: " + this.fields[c][r].getFieldName() + " może wykonać ruchy: ");

                    //poziomo
                    for (int cc = 0; cc < 8; cc++)
                        availableHorizontalFields.add(this.fields[cc][r]); //dodajemy wszystkie poziome pola do listy
                    //ustalamy zakres dostępnych pól z poziomych pól
                    int leftBeginning = 0;
                    int rightEnd = 7;
                    for (int i = 0; i < c; i++)
                        if (availableHorizontalFields.get(i).getPiece() != null) leftBeginning = i;
                    for (int i = 7; i > c; i--)
                        if (availableHorizontalFields.get(i).getPiece() != null) rightEnd = i;

                    if (availableHorizontalFields.get(leftBeginning).getPiece() != null && !availableHorizontalFields.get(leftBeginning).getPiece().isPieceBlack)
                        leftBeginning += 1;
                    if (availableHorizontalFields.get(rightEnd).getPiece() != null && !availableHorizontalFields.get(rightEnd).getPiece().isPieceBlack)
                        rightEnd -= 1;

                    availableHorizontalFields = availableHorizontalFields.subList(leftBeginning, rightEnd + 1); //ustala nowy zakres dostępnych pól
                    availableHorizontalFields.remove(this.fields[c][r]); //usuwa królową z listy

                    //pionowo
                    for (int rr = 0; rr < 8; rr++) availableVerticalFields.add(this.fields[c][rr]);
                    //ustalamy zakres dostępnych pól z pionowych pól
                    int downBeginning = 0;
                    int upEnd = 7;
                    for (int i = 0; i < r; i++)
                        if (availableVerticalFields.get(i).getPiece() != null) downBeginning = i;

                    for (int i = 7; i > r; i--)
                        if (availableVerticalFields.get(i).getPiece() != null) upEnd = i;

                    if (availableVerticalFields.get(downBeginning).getPiece() != null && !availableVerticalFields.get(downBeginning).getPiece().isPieceBlack)
                        downBeginning += 1;
                    if (availableVerticalFields.get(upEnd).getPiece() != null && !availableVerticalFields.get(upEnd).getPiece().isPieceBlack)
                        upEnd -= 1;

                    availableVerticalFields = availableVerticalFields.subList(downBeginning, upEnd + 1); //ustala nowy zakres dostępnych pól
                    availableVerticalFields.remove(this.fields[c][r]); //usuwa królową z listy

                    //BYKU OGARNIJ TE RÓŻNICE ŻE JAK NA POCZĄTKU I KOŃCU JEST FIGURA CZARNA TO USUWA Z ZAKRESU ALBO NIE ITD, GENERALNIE POZIOMO/PIONOWO/SKOSY DO UZUPEŁNIENIA

                    //skośnie rosnąco
                    int counter = 0;
                    int ourPositionInTheList = 0;
                    for (int cc = 0; cc < 8; cc++) {
                        for (int rr = 0; rr < 8; rr++) {
                            if ((Math.abs(cc - c) == Math.abs(rr - r)) && ((cc <= c && rr <= r) || (cc >= c && rr >= r))) { //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                                availableRisingDiagonalFields.add(this.fields[cc][rr]);
                                if (this.fields[cc][rr] == this.fields[c][r])
                                    ourPositionInTheList = counter; //jeśli trafimy na królową to zapamiętujemy jej położenie na liście
                                counter++;
                            }
                        }
                    }
                    counter--; //musimy skonwertować counter na tablicowy counter :)
                    //ustalamy zakres dostępnych pól z skośnie rosnących pól
                    int downLeftBeginning = 0;
                    int upRightEnd = counter;
                    for (int i = 0; i < ourPositionInTheList; i++)
                        if (availableRisingDiagonalFields.get(i).getPiece() != null) downLeftBeginning = i;
                    for (int i = counter; i > ourPositionInTheList; i--)
                        if (availableRisingDiagonalFields.get(i).getPiece() != null) upRightEnd = i;
                    if (availableRisingDiagonalFields.get(downLeftBeginning).getPiece() != null && !availableRisingDiagonalFields.get(downLeftBeginning).getPiece().isPieceBlack)
                        downLeftBeginning += 1;
                    if (availableRisingDiagonalFields.get(upRightEnd).getPiece() != null && !availableRisingDiagonalFields.get(upRightEnd).getPiece().isPieceBlack)
                        upRightEnd -= 1;

                    availableRisingDiagonalFields = availableRisingDiagonalFields.subList(downLeftBeginning, upRightEnd + 1); //ustala nowy zakres dostępnych pól
                    availableRisingDiagonalFields.remove(this.fields[c][r]); //usuwa królową z listy

                    //skośnie opadająco
                    counter = 0;
                    ourPositionInTheList = 0;
                    for (int cc = 0; cc < 8; cc++) {
                        for (int rr = 0; rr < 8; rr++) {
                            if ((Math.abs(cc - c) == Math.abs(rr - r)) && ((cc < c && rr > r) || (cc > c && rr < r))) { //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                                availableFallingDiagonalFields.add(this.fields[cc][rr]);
                                if (this.fields[cc][rr] == this.fields[c][r])
                                    ourPositionInTheList = counter; //jeśli trafimy na królową to zapamiętujemy jej położenie na liście
                                counter++;
                            }
                        }
                    }
                    counter--; //musimy skonwertować counter na tablicowy counter :)
                    //ustalamy zakres dostępnych pól z skośnie opadających pól
                    int upLeftBeginning = 0;
                    int downRightEnd = counter;
                    for (int i = 0; i < ourPositionInTheList; i++)
                        if (availableFallingDiagonalFields.get(i).getPiece() != null) upLeftBeginning = i;
                    for (int i = counter; i > ourPositionInTheList; i--)
                        if (availableFallingDiagonalFields.get(i).getPiece() != null) downRightEnd = i;

                    if (availableFallingDiagonalFields.get(upLeftBeginning).getPiece() != null && !availableFallingDiagonalFields.get(upLeftBeginning).getPiece().isPieceBlack)
                        upLeftBeginning += 1;
                    if (availableFallingDiagonalFields.get(downRightEnd).getPiece() != null && !availableFallingDiagonalFields.get(downRightEnd).getPiece().isPieceBlack)
                        downRightEnd -= 1;
                    availableFallingDiagonalFields = availableFallingDiagonalFields.subList(upLeftBeginning, downRightEnd + 1); //ustala nowy zakres dostępnych pól
                    availableFallingDiagonalFields.remove(this.fields[c][r]); //usuwa królową z listy
                }
            }
        }

        //blacksMove...

        //finally
        availableFields.addAll(availableHorizontalFields);
        availableFields.addAll(availableVerticalFields);
        availableFields.addAll(availableRisingDiagonalFields);
        availableFields.addAll(availableFallingDiagonalFields);
        for (Field i : availableFields) System.out.println(i.getFieldName());
        return availableFields;
    }

    public boolean movePawn(String move, boolean whitesMove) {
        //spliting "move"
        String ourColumn = "";
        int ourRow = 0;
        String opponentsColumn = "";
        int opponentsRow = 0;

        boolean pawnFights = false;

        if (move.length() == 2) {
            ourColumn = "" + move.charAt(0);
            opponentsColumn = ourColumn;
            opponentsRow = Integer.parseInt("" + move.charAt(1));
        } else {
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
            ourRow = whitePiecesClosestRowInIndicatedColumn(ourColumn, opponentsRow, "P");
            if (opponentsRow < ourRow) { //czy pion porusza się w tył?
                System.out.println("Pion porusza się tylko do przodu");
                return false;
            }
            if (isIndicatedFieldInRangeOfPiece(ourColumn, ourRow, opponentsRow)) {
                if (hasIndicatedFieldOurWhitePiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnMoves(opponentsColumn, opponentsRow, ourColumn, ourRow)) {
                    this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece().setForwardRange(1);
                    return true;
                }
            }
        } else if (whitesMove && pawnFights) { //D(column):E(opponentsColumn)5(opponentsRow)
            System.out.println("Pion będzie bił (weszliśmy)");
            ourRow = whitePiecesClosestRowInIndicatedColumn(ourColumn, opponentsRow, "P");
            if (opponentsRow < ourRow) { //czy pion porusza się w tył?
                System.out.println("Pion porusza się tylko do przodu");
                return false;
            }
            if (ourRow != 0 && isIndicatedOpponentInRangeOfOurPawn(ourColumn, ourRow, opponentsColumn, opponentsRow)) {
                if (hasIndicatedFieldOurWhitePiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnKills(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        } else if (!whitesMove && !pawnFights) {
            ourRow = blackPiecesClosestRowInIndicatedColumn(ourColumn, opponentsRow, "P");
            if (opponentsRow > ourRow) { //czy pion porusza się w tył?
                System.out.println("Pion porusza się tylko do przodu");
                return false;
            }
            if (isIndicatedFieldInRangeOfPiece(ourColumn, ourRow, opponentsRow)) {
                if (hasIndicatedFieldOurBlackPiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnMoves(opponentsColumn, opponentsRow, ourColumn, ourRow)) {
                    this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece().setForwardRange(1);
                    return true;
                }
            }
        } else if (!whitesMove && pawnFights) { //D(column):E(opponentsColumn)5(opponentsRow)
            System.out.println("Pion będzie bił (weszliśmy)");
            ourRow = blackPiecesClosestRowInIndicatedColumn(ourColumn, opponentsRow, "P");
            if (opponentsRow > ourRow) { //czy pion porusza się w tył?
                System.out.println("Pion porusza się tylko do przodu");
                return false;
            }
            if (ourRow != 0 && isIndicatedOpponentInRangeOfOurPawn(ourColumn, ourRow, opponentsColumn, opponentsRow)) {
                if (hasIndicatedFieldOurBlackPiece(opponentsColumn, opponentsRow)) return false;
                else if (pawnKills(opponentsColumn, opponentsRow, ourColumn, ourRow)) return true;
            }
        }
        return false;
    }

    public int whitePiecesClosestRowInIndicatedColumn(String ourColumn, int opponentsRow, String name) { //zwraca listę wszystkich rzędów na których występuje dana figura w danej kolumnie
        int closestRow = Integer.MAX_VALUE;
        int r = 0; //rząd z naszą bierką najbliższy rzędowi przeciwnika
        for (r = 0; r < 8; r++) { //szuka czy w podanej kolumnie znajduje się pion
            if (this.fields[columnToNumber(ourColumn)][r].getPiece() != null && // i jeśli znalezione pole posiada bierkę
                    !this.fields[columnToNumber(ourColumn)][r].getPiece().isPieceBlack() && // i jeśli bierka jest biała
                    this.fields[columnToNumber(ourColumn)][r].getPiece().getName().equals(name)) {// i jeśli ta bierka to bierka którą chcemy
                if (Math.abs(rowToArrayRow(opponentsRow) - r) < Math.abs(rowToArrayRow(opponentsRow) - closestRow)) {
                    closestRow = r;
                }
                System.out.println("Znaleziono białą bierkę w kolumnie " + ourColumn + " i w rzędzie " + (r + 1));
            }
        }
        if (closestRow == Integer.MAX_VALUE) {
            System.out.println("whitePiecesClosestRowInIndicatedColumn() -> Nie znaleziono, return 0");
            return 0;
        } else {
            System.out.println("Najbliższa bierka to ta w rzędzie " + (closestRow + 1));
            return closestRow + 1;
        }
    }

    public int blackPiecesClosestRowInIndicatedColumn(String column, int opponentsRow, String name) { //zwraca listę wszystkich rzędów na których występuje dana figura w danej kolumnie
        int closestRow = Integer.MAX_VALUE;
        int r = 0; //rząd z naszą bierką najbliższy rzędowi przeciwnika
        for (r = 0; r < 8; r++) { //szuka czy w podanej kolumnie znajduje się pion
            if (this.fields[columnToNumber(column)][r].getPiece() != null && // i jeśli znalezione pole posiada bierkę
                    this.fields[columnToNumber(column)][r].getPiece().isPieceBlack() && // i jeśli bierka jest czarna
                    this.fields[columnToNumber(column)][r].getPiece().getName().equals(name)) { // i jeśli ta bierka to pion
                if (Math.abs(rowToArrayRow(opponentsRow) - r) < Math.abs(rowToArrayRow(opponentsRow) - closestRow)) {
                    closestRow = r;
                }
                System.out.println("Znaleziono czarną bierkę w kolumnie " + column + " i w rzędzie " + (r + 1));
            }
        }
        if (closestRow == Integer.MAX_VALUE) {
            System.out.println("blackPiecesClosestRowInIndicatedColumn() -> Nie znaleziono, return 0");
            return 0;
        } else {
            System.out.println("Najbliższa bierka to ta w rzędzie " + (closestRow + 1));
            return closestRow + 1;
        }
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

    public boolean isIndicatedOpponentInRangeOfOurPawn(String ourColumn, int ourRow, String opponentsColumn,
                                                       int opponentsRow) {
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

    public boolean moveOrFight(String ourColumn, int opponentsRow, int ourRow) {
        if (isIndicatedFieldEmpty(ourColumn, opponentsRow) || hasIndicatedFieldOpponentsBlackPiece(ourColumn, opponentsRow) || hasIndicatedFieldOpponentsWhitePiece(ourColumn, opponentsRow)) {
            this.fields[columnToNumber(ourColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][ourRow].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
            this.fields[columnToNumber(ourColumn)][ourRow].setPiece(null); //kasujemy piona ze starego piona
            return true;
        } else return false;
    }

    //pawn moves (wyjątkowa metoda tylko dla piona)
    public boolean pawnMoves(String opponentsColumn, int opponentsRow, String ourColumn, int ourRow) {
        if (isIndicatedFieldEmpty(opponentsColumn, opponentsRow)) {
            this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
            this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(null); //kasujemy piona ze starego piona
            return true;
        } else return false;
    }

    //(wyjątkowa metoda tylko dla piona)
    public boolean pawnKills(String opponentsColumn, int opponentsRow, String ourColumn, int ourRow) {
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

    public String numberToColumn(int column) {
        if (column == 0) return "A";
        if (column == 1) return "B";
        if (column == 2) return "C";
        if (column == 3) return "D";
        if (column == 4) return "E";
        if (column == 5) return "F";
        else return "H"; //H
    }

    public int rowToArrayRow(int row) {
        return row - 1;
    }

    public int arrayRowToRow(int arrayRow) {
        return arrayRow + 1;
    }


}
