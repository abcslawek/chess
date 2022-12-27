package com.slaweklida;

import com.slaweklida.pieces.*;

import java.util.*;

public class Chessboard {

    public static final String ANSI_BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String ANSI_RED_BACKGROUND = "\033[41m";    // RED
    public static final String ANSI_GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String ANSI_YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String ANSI_BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String ANSI_PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String ANSI_CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String ANSI_WHITE_BACKGROUND = "\033[47m";  // WHITE
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_BLACK = "\033[0;30m";   // BLACK
    public static final String ANSI_RED = "\033[0;31m";     // RED
    public static final String ANSI_GREEN = "\033[0;32m";   // GREEN
    public static final String ANSI_YELLOW = "\033[0;33m";  // YELLOW
    public static final String ANSI_BLUE = "\033[0;34m";    // BLUE
    public static final String ANSI_PURPLE = "\033[0;35m";  // PURPLE
    public static final String ANSI_CYAN = "\033[0;36m";    // CYAN
    public static final String ANSI_WHITE = "\033[0;37m";   // WHITE
    //bold high intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    //high intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK

    private Field[][] fields;

    //    constructor
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

//    //test chessboard
//    public Chessboard(boolean whitesFirst) {
//        this.fields = new Field[8][8];
//        //making battlefield
//        for (int r = 0; r < 8; r++) {
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
//        this.fields[0][0].setPiece(new Queen(false));
//        this.fields[0][2].setPiece(new Rook(true));
//        this.fields[2][2].setPiece(new Rook(true));
//        this.fields[2][0].setPiece(new Rook(true));
//
//
//    }

    //getters
    public Field[][] getFields() {
        return this.fields;
    }

    public Field getField(int c, int r) {
        return this.fields[c][r];
    }

    //other methods
    public void showChessboard() {
        for (int r = this.fields.length - 1; r >= 0; r--) {
            System.out.print(r + 1); //numery rzędów
            for (int c = 0; c < fields.length; c++) {
                System.out.print(ANSI_BLACK + ANSI_BLUE_BACKGROUND + " " + this.fields[c][r].getPieceName() + "\t" + ANSI_RESET);
                c++;
                System.out.print(ANSI_BLACK + BLACK_BACKGROUND_BRIGHT + " " + this.fields[c][r].getPieceName() + "\t" + ANSI_RESET);
            }
            System.out.println();
            r--; //pola muszą być pokolorowane naprzemiennie
            System.out.print(r + 1); //numery rzędów
            for (int c = 0; c < this.fields.length; c++) {
                System.out.print(ANSI_BLACK + BLACK_BACKGROUND_BRIGHT + " " + this.fields[c][r].getPieceName() + "\t" + ANSI_RESET);
                c++;
                System.out.print(ANSI_BLACK + ANSI_BLUE_BACKGROUND + " " + this.fields[c][r].getPieceName() + "\t" + ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println("  A\t B\t C\t D\t E\t F\t G\t H");
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
        System.out.println(ANSI_CYAN_BACKGROUND + "ehe" + ANSI_RESET);

        if (move.length() == 2 || move.length() == 4) { //jeśli d3, czyli pion
            return movePawn(move.toUpperCase(Locale.ROOT), whitesMove);
        } else if (move.length() == 5) { //jeśli np. A4:C7 (figura z A4 idzie na C7)
            String ourColumn = ("" + move.charAt(0)).toUpperCase(Locale.ROOT);
            int ourRow = Integer.parseInt("" + move.charAt(1));
            String opponentsColumn = ("" + move.charAt(3)).toUpperCase(Locale.ROOT);
            int opponentsRow = Integer.parseInt("" + move.charAt(4));
            String piece = this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName();

            if (piece.equals("Q") && availableQueensMoves(ourColumn, ourRow, whitesMove).contains(this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)]))
                return moveOrFight(ourColumn, ourRow, opponentsColumn, opponentsRow);
            if (piece.equals("B") && availableBishopsMoves(ourColumn, ourRow, whitesMove).contains(this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)]))
                return moveOrFight(ourColumn, ourRow, opponentsColumn, opponentsRow);
            if (piece.equals("R") && availableRooksMoves(ourColumn, ourRow, whitesMove).contains(this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)]))
                return moveOrFight(ourColumn, ourRow, opponentsColumn, opponentsRow);

            return false;
        } else {
            System.out.println("makeMove() -> nie weszło do żadnej kategorii metody (move.length() == ?)");
            return false;
        }
    }

    public Set<Field> availableQueensMoves(String ourColumn, int ourRow, boolean whitesMove) { //bierki z ourColumn i ourRow
        return availableMoves(ourColumn, ourRow, true, true, true, "Q", whitesMove);
    }

    public Set<Field> availableBishopsMoves(String ourColumn, int ourRow, boolean whitesMove) { //bierki z ourColumn i ourRow
        return availableMoves(ourColumn, ourRow, false, false, true, "B", whitesMove);
    }

    public Set<Field> availableRooksMoves(String ourColumn, int ourRow, boolean whitesMove) { //bierki z ourColumn i ourRow
        return availableMoves(ourColumn, ourRow, true, true, false, "R", whitesMove);
    }

    public Set<Field> availableMoves(String ourColumn, int ourRow, boolean horizontal, boolean vertical, boolean diagonal, String piece, boolean whitesMove) {
        List<Field> availableHorizontalFields = new ArrayList<>();
        List<Field> availableVerticalFields = new ArrayList<>();
        List<Field> availableRisingDiagonalFields = new ArrayList<>();
        List<Field> availableFallingDiagonalFields = new ArrayList<>();
        Set<Field> availableFields = new HashSet<>(); //używamy setu aby wyeliminować powtarzające się pola królowej

        if (this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece() != null && this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName().equals(piece) && whitesMove != this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().isPieceBlack) {
            System.out.println("Znaleziona: " + this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName() + " na polu: " + this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getFieldName() + " może wykonać ruchy: ");

            //poziomo
            if (horizontal) {
                for (int cc = 0; cc < 8; cc++)
                    availableHorizontalFields.add(this.fields[cc][rowToArrayRow(ourRow)]); //dodajemy wszystkie poziome pola do listy
                //ustalamy zakres dostępnych pól z poziomych pól
                availableHorizontalFields = skippingObstacles(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)], availableHorizontalFields, columnToNumber(ourColumn), 7, whitesMove);
            }

            //pionowo
            if (vertical) {
                for (int rr = 0; rr < 8; rr++) availableVerticalFields.add(this.fields[columnToNumber(ourColumn)][rr]);
                //ustalamy zakres dostępnych pól z pionowych pól
                availableVerticalFields = skippingObstacles(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)], availableVerticalFields, rowToArrayRow(ourRow), 7, whitesMove);
            }

            //skośnie
            if (diagonal) {
                //skośnie rosnąco
                int counter = 0; //zmienna ta opisuje liczbę pól w ukosie, która nie zawsze jest taka sama
                int ourPositionInTheList = 0;
                for (int cc = 0; cc < 8; cc++) {
                    for (int rr = 0; rr < 8; rr++) {
                        if ((Math.abs(cc - columnToNumber(ourColumn)) == Math.abs(rr - rowToArrayRow(ourRow))) && ((cc <= columnToNumber(ourColumn) && rr <= rowToArrayRow(ourRow)) || (cc >= columnToNumber(ourColumn) && rr >= rowToArrayRow(ourRow)))) { //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                            availableRisingDiagonalFields.add(this.fields[cc][rr]);
                            if (this.fields[cc][rr] == this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)])
                                ourPositionInTheList = counter; //jeśli trafimy na królową to zapamiętujemy jej położenie na liście
                            counter++;
                        }
                    }
                }
                counter--; //musimy skonwertować counter na tablicowy counter :)
                //ustalamy zakres dostępnych pól z skośnie rosnących pól
                availableRisingDiagonalFields = skippingObstacles(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)], availableRisingDiagonalFields, ourPositionInTheList, counter, whitesMove);

                //skośnie opadająco
                counter = 0;
                ourPositionInTheList = 0;
                for (int cc = 0; cc < 8; cc++) {
                    for (int rr = 0; rr < 8; rr++) {
                        if ((Math.abs(cc - columnToNumber(ourColumn)) == Math.abs(rr - rowToArrayRow(ourRow))) && ((cc <= columnToNumber(ourColumn) && rr >= rowToArrayRow(ourRow)) || (cc >= columnToNumber(ourColumn) && rr <= rowToArrayRow(ourRow)))) { //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                            availableFallingDiagonalFields.add(this.fields[cc][rr]);
                            if (this.fields[cc][rr] == this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)])
                                ourPositionInTheList = counter; //jeśli trafimy na królową to zapamiętujemy jej położenie na liście
                            counter++;
                        }
                    }
                }
                counter--; //musimy skonwertować counter na tablicowy counter :)
                //ustalamy zakres dostępnych pól z skośnie opadających pól
                availableFallingDiagonalFields = skippingObstacles(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)], availableFallingDiagonalFields, ourPositionInTheList, counter, whitesMove);
            }
        }

        //finally
        availableFields.addAll(availableHorizontalFields);
        availableFields.addAll(availableVerticalFields);
        availableFields.addAll(availableRisingDiagonalFields);
        availableFields.addAll(availableFallingDiagonalFields);

//        for (Field i : availableFields) System.out.println(i.getFieldName());
        System.out.println("Wszystkich możliwych ruchów: " + availableFields.size());
        return availableFields;
    }

    public List<Field> skippingObstacles(Field ourField, List<Field> availableFields, int ourPositionInTheList, int counter, boolean whitesMove) {
        int begin = 0;
        int end = counter; //poziomo i pionowo end = 7
        for (int i = 0; i < ourPositionInTheList; i++)
            if (availableFields.get(i).getPiece() != null) begin = i;
        for (int i = counter; i > ourPositionInTheList; i--)
            if (availableFields.get(i).getPiece() != null) end = i;
        if (availableFields.get(begin).getPiece() != null && availableFields.get(begin) != ourField && whitesMove != availableFields.get(begin).getPiece().isPieceBlack) //środkowy warunek w razie gdyby królowa była na skraju
            begin += 1;
        if (availableFields.get(end).getPiece() != null && availableFields.get(end) != ourField && whitesMove != availableFields.get(end).getPiece().isPieceBlack) //środkowy warunek w razie gdyby królowa była na skraju
            end -= 1;
        if (begin > end) { //gdyby z prawej skrajnej strony była po ukosie nasza królowa
            availableFields.clear();
            return availableFields;
        }
        availableFields = availableFields.subList(begin, end + 1); //ustala nowy zakres dostępnych pól
        availableFields.remove(ourField);
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

    public boolean moveOrFight(String ourColumn, int ourRow, String opponentsColumn, int opponentsRow) {
        System.out.println("Weszliśśmy do moveOrFight()");
        this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
        this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(null); //kasujemy piona ze starego piona
        return true;
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
