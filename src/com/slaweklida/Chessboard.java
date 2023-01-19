package com.slaweklida;

import com.slaweklida.pieces.*;

import java.util.*;

public class Chessboard implements Cloneable{

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Background
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE


    // Pola
    private Field[][] fields;
    private String checkedField = "";
    private String checkedMatedField = "";
    private boolean whitesWon = false;
    private boolean blacksWon = false;
    private boolean stalemate = false;


    // Metody
    public boolean isWhitesWon() {
        return whitesWon;
    }

    public boolean isBlacksWon() {
        return blacksWon;
    }

    public boolean isStalemate() {
        return stalemate;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    // Konstruktor
    public Chessboard() {
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
//        //our pieces
//        this.fields[3][4].setPiece(new King(false));
//        this.fields[6][1].setPiece(new King(true));
//
//        this.fields[7][4].setPiece(new Queen(false));
//        this.fields[7][3].setPiece(new Queen(false));
//        this.fields[1][0].setPiece(new Queen(false));
//        this.fields[4][0].setPiece(new Queen(false));
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
            System.out.print(r + 1 + "\t"); //numery rzędów
            for (int c = 0; c < fields.length; c++) {
                if (this.fields[c][r].getFieldName().equals(this.checkedField)) { //sprawdzamy czy to pole nie jest checkowane
                    if (this.isBlacksWon() || this.isWhitesWon())
                        System.out.print(GREEN_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli MAT to kolorujemy je na zielono
                    else
                        System.out.print(RED_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli nie to kolorujemy je na czerwono
                } else {
                    if (this.fields[c][r].getPiece() != null)
                        System.out.print((c % 2 != 0 ? BLUE_BACKGROUND : WHITE_BACKGROUND) + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET);
                    else System.out.print((c % 2 != 0 ? BLUE_BACKGROUND : WHITE_BACKGROUND) + "\t" + RESET);
                }
            }
            System.out.println();
            r--; //pola muszą być pokolorowane naprzemiennie
            System.out.print(r + 1 + "\t"); //numery rzędów
            for (int c = 0; c < this.fields.length; c++) {
                if (this.fields[c][r].getFieldName().equals(this.checkedField)) { //sprawdzamy czy to pole nie jest checkowane
                    if (this.isBlacksWon() || this.isWhitesWon())
                        System.out.print(GREEN_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli MAT  to kolorujemy je na zielono
                    else
                        System.out.print(RED_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli nie to kolorujemy je na czerwono
                } else {
                    if (this.fields[c][r].getPiece() != null) {
                        System.out.print((c % 2 != 0 ? WHITE_BACKGROUND : BLUE_BACKGROUND) + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET);
                    } else System.out.print((c % 2 != 0 ? WHITE_BACKGROUND : BLUE_BACKGROUND) + "\t" + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("\tA\t B\t C\t D\t E\t F\t G\t H");
    }

    public void showReverseChessboard() {
        System.out.println("H\t G\t F\t E\t D\t C\t B\t A");
        for (int r = 0; r < this.fields.length - 1; r++) {
            for (int c = fields.length - 1 ; c >= 0; c--) {
                if (this.fields[c][r].getFieldName().equals(this.checkedField)) { //sprawdzamy czy to pole nie jest checkowane
                    if (this.isBlacksWon() || this.isWhitesWon())
                        System.out.print(GREEN_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli MAT to kolorujemy je na zielono
                    else
                        System.out.print(RED_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli nie to kolorujemy je na czerwono
                } else {
                    if (this.fields[c][r].getPiece() != null)
                        System.out.print((c % 2 == 0 ? BLUE_BACKGROUND : WHITE_BACKGROUND) + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET);
                    else System.out.print((c % 2 == 0 ? BLUE_BACKGROUND : WHITE_BACKGROUND) + "\t" + RESET);
                }
            }
            System.out.print(r + 1 + "\t"); //numery rzędów
            System.out.println();
            r++; //pola muszą być pokolorowane naprzemiennie

            for (int c = 0; c < this.fields.length; c++) {
                if (this.fields[c][r].getFieldName().equals(this.checkedField)) { //sprawdzamy czy to pole nie jest checkowane
                    if (this.isBlacksWon() || this.isWhitesWon())
                        System.out.print(GREEN_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli MAT  to kolorujemy je na zielono
                    else
                        System.out.print(RED_BACKGROUND + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET); //jeśli nie to kolorujemy je na czerwono
                } else {
                    if (this.fields[c][r].getPiece() != null) {
                        System.out.print((c % 2 != 0 ? WHITE_BACKGROUND : BLUE_BACKGROUND) + (char) this.fields[c][r].getPiece().getImage() + "\t" + RESET);
                    } else System.out.print((c % 2 != 0 ? WHITE_BACKGROUND : BLUE_BACKGROUND) + "\t" + RESET);
                }
            }
            System.out.print(r + 1 + "\t"); //numery rzędów
            System.out.println();
        }

    }

    public boolean makeMove(Set<String> everyAvailableMoves, String move, boolean whitesMove) {
        //System.out.println("Wyszukany ruch to: " + move);
        if (everyAvailableMoves.contains(move.toUpperCase(Locale.ROOT))) { //jeśli lista dostępnych ruchów zawiera ruch, który podaliśmy
            //rozkładamy podany ruch (stringa) na kolejne zmienne
            String ourColumn = ("" + move.charAt(0)).toUpperCase(Locale.ROOT);
            int ourRow = Integer.parseInt("" + move.charAt(1));
            String opponentsColumn = ("" + move.charAt(3)).toUpperCase(Locale.ROOT);
            int opponentsRow = Integer.parseInt("" + move.charAt(4));

            if (this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName().equals("P")) {
                this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().setForwardRange(1); //zmiana range'u pionka po ruchu
                if (whitesMove ? (opponentsRow == 8) : (opponentsRow == 1)) //tutaj była zmiana, uproszczenie zapisu
                    this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(new Queen(!whitesMove)); //po osiągnięciu ostatniego rzędu pion zmienia się na królową
            }
            //System.out.println("Wykonaliśmy ruch: " + move);
            return moveOrFight(whitesMove, ourColumn, ourRow, opponentsColumn, opponentsRow, false);
        } else {
            System.out.println("makeMove() -> nie weszło do żadnej kategorii metody (move.length() == ?)");
        }
        return false;
    }

    public Set<String> everyAvailableMove(boolean whitesMove) {
        Set<String> availableStringMoves = new HashSet<>();
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && whitesMove != this.fields[cc][rr].getPiece().isPieceBlack) {
                    Set<Field> temp = availablePiecesMoves(this.fields[cc][rr].getPiece().getName(), numberToColumn(cc), arrayRowToRow(rr), whitesMove);
                    //System.out.print(this.fields[cc][rr].getFieldName() + " : ");
                    for (Field f : temp) {
                        if (!isOurKingCheckedAfterOurMove(numberToColumn(cc), arrayRowToRow(rr), "" + f.getColumn(), f.getRow(), whitesMove)) {
                            availableStringMoves.add(this.fields[cc][rr].getFieldName() + ":" + f.getFieldName()); //dodajemy każdy możliwy ruch
                            //System.out.print(f.getFieldName() + ", ");
                        }
                    }
                    //System.out.println();
                }
            }
        }
        //System.out.println("-----------------------------");
        return availableStringMoves;
    }

    public Set<Field> availablePiecesMoves(String piece, String ourColumn, int ourRow, boolean whitesMove) {
        if (piece.equals("Q"))
            return availableMoves(ourColumn, ourRow, true, true, true, false, false, false, "Q", whitesMove);
        if (piece.equals("B"))
            return availableMoves(ourColumn, ourRow, false, false, true, false, false, false, "B", whitesMove);
        if (piece.equals("R"))
            return availableMoves(ourColumn, ourRow, true, true, false, false, false, false, "R", whitesMove);
        if (piece.equals("N"))
            return availableMoves(ourColumn, ourRow, false, false, false, true, false, false, "N", whitesMove);
        if (piece.equals("K"))
            return availableMoves(ourColumn, ourRow, false, false, false, false, true, false, "K", whitesMove);
        if (piece.equals("P"))
            return availableMoves(ourColumn, ourRow, false, false, false, false, false, true, "P", whitesMove);
        return null;
    }

    public Set<Field> availableMoves(String ourColumn, int ourRow, boolean horizontal, boolean vertical, boolean diagonal, boolean leaping, boolean kingy, boolean pawny, String piece, boolean whitesMove) {
        List<Field> availableHorizontalFields = new ArrayList<>();
        List<Field> availableVerticalFields = new ArrayList<>();
        List<Field> availableRisingDiagonalFields = new ArrayList<>();
        List<Field> availableFallingDiagonalFields = new ArrayList<>();
        List<Field> availableLeapingFields = new ArrayList<>();
        List<Field> availableKingyFields = new ArrayList<>();
        List<Field> availablePawnyFields = new ArrayList<>();
        Set<Field> availableFields = new HashSet<>(); //używamy setu aby wyeliminować powtarzające się pola

        if (this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece() != null && this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName().equals(piece) && whitesMove != this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().isPieceBlack) {
//            System.out.println("Znaleziona bierka: " + this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getName() + " na polu: " + this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getFieldName() + " może wykonać ruchy: ");

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

            //skoczek
            if (leaping) {
                for (int cc = 0; cc < 8; cc++) {
                    for (int rr = 0; rr < 8; rr++) {
                        if (Math.abs(cc - columnToNumber(ourColumn)) + Math.abs(rr - rowToArrayRow(ourRow)) == 3 && //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                                Math.abs(cc - columnToNumber(ourColumn)) > 0 && Math.abs(cc - columnToNumber(ourColumn)) < 3 &&
                                Math.abs(rr - rowToArrayRow(ourRow)) > 0 && Math.abs(rr - rowToArrayRow(ourRow)) < 3 &&
                                (this.fields[cc][rr].getPiece() == null || this.fields[cc][rr].getPiece() != null &&
                                        whitesMove == this.fields[cc][rr].getPiece().isPieceBlack)) {
                            availableLeapingFields.add(this.fields[cc][rr]);
                        }
                    }
                }
            }

            if (kingy) {
                for (int cc = 0; cc < 8; cc++) {
                    for (int rr = 0; rr < 8; rr++) {
                        if (Math.abs(cc - columnToNumber(ourColumn)) + Math.abs(rr - rowToArrayRow(ourRow)) <= 2 && //z tw. talesa oraz w konkretnych ćwiartkach układu wsp
                                Math.abs(cc - columnToNumber(ourColumn)) >= 0 && Math.abs(cc - columnToNumber(ourColumn)) <= 1 &&
                                Math.abs(rr - rowToArrayRow(ourRow)) >= 0 && Math.abs(rr - rowToArrayRow(ourRow)) <= 1 &&
                                (this.fields[cc][rr].getPiece() == null || this.fields[cc][rr].getPiece() != null &&
                                        whitesMove == this.fields[cc][rr].getPiece().isPieceBlack)) {
                            availableKingyFields.add(this.fields[cc][rr]);
                        }
                    }
                }
            }

            if (pawny) {
                int columnsDiff;
                int rowsDiff;
                int ourRange;
                for (int cc = 0; cc < 8; cc++) {
                    for (int rr = 0; rr < 8; rr++) {
                        columnsDiff = cc - columnToNumber(ourColumn);
                        rowsDiff = rr - rowToArrayRow(ourRow);
                        ourRange = this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece().getForwardRange();

                        if (this.fields[cc][rr].getPiece() == null && Math.abs(columnsDiff) == 0 && (whitesMove ? (rowsDiff <= ourRange && rowsDiff > 0) : (rowsDiff >= -ourRange && rowsDiff < 0)) ||
                                (this.fields[cc][rr].getPiece() != null && whitesMove == this.fields[cc][rr].getPiece().isPieceBlack && Math.abs(columnsDiff) == 1 && (whitesMove ? (rowsDiff == 1) : (rowsDiff == -1)))) {
                            availablePawnyFields.add(this.fields[cc][rr]);
                        }
                    }
                }
            }
        }
        //finally
        availableFields.addAll(availableHorizontalFields);
        availableFields.addAll(availableVerticalFields);
        availableFields.addAll(availableRisingDiagonalFields);
        availableFields.addAll(availableFallingDiagonalFields);
        availableFields.addAll(availableLeapingFields);
        availableFields.addAll(availableKingyFields);
        availableFields.addAll(availablePawnyFields);

        return availableFields;
    }

    public List<Field> skippingObstacles(Field ourField, List<Field> availableFields, int ourPositionInTheList, int counter, boolean whitesMove) {
        int begin = 0;
        int end = counter; //poziomo i pionowo end = 7
        boolean isPieceCovering = false;
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

    public boolean isOurCheckMate(boolean whitesMove) {
        //szachujemy i sprawdzamy czy ten szach jest matem
        //sprawdzamy dostępne ruchy przeciwnika po tym ruchu
        Set<Field> tempSet;
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && whitesMove == this.fields[cc][rr].getPiece().isPieceBlack) {
                    tempSet = availablePiecesMoves(this.fields[cc][rr].getPiece().getName(), numberToColumn(cc), arrayRowToRow(rr), !whitesMove);
                    for (Field f : tempSet) {
                        if (!isOurKingCheckedAfterOurMove(numberToColumn(cc), arrayRowToRow(rr), "" + f.getColumn(), f.getRow(), !whitesMove)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isOurKingCheckedAfterOurMove(String ourColumn, int ourRow, String opponentsColumn, int opponentsRow, boolean whitesMove) {
        //zapamiętujemy położenie figur i wykonujemy testowy ruch aby sprawdzić czy nasz król nie będzie po nim szachowany
        Piece ourTemp = null;
        Piece opponentsTemp = null;
        if (this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece() != null)
            ourTemp = this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece(); //przechowujemy bierkę którą przesuwamy na inne pole
        if (this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece() != null)
            opponentsTemp = this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].getPiece(); //przechowujemy bierkę z pola na które idziemy
        moveOrFight(whitesMove, ourColumn, ourRow, opponentsColumn, opponentsRow, true); //tymczasowo przesuwamy bierkę i sprawdzamy czy król wtedy jest atakowany
        Set<Field> tempSet;

        //szukam naszego króla i zapisuję go
        int kingsColumn = 0;
        int kingsRow = 0;
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && this.fields[cc][rr].getPiece().getName().equals("K") && whitesMove != this.fields[cc][rr].getPiece().isPieceBlack) {
                    kingsColumn = cc;
                    kingsRow = rr;
                }
            }
        }

        //sprawdzam czy wroga bierka nie widzi naszego króla (nie posiada jego współrzędnych w dostępnych polach)
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && whitesMove == this.fields[cc][rr].getPiece().isPieceBlack) {
                    tempSet = availablePiecesMoves(this.fields[cc][rr].getPiece().getName(), numberToColumn(cc), arrayRowToRow(rr), !whitesMove);
                    if (tempSet.contains(this.fields[kingsColumn][kingsRow])) {
//                        System.out.println("Wroga bierka na: " + this.fields[cc][rr].getFieldName() + " widzi naszego króla z pola: " + this.fields[kingsColumn][kingsRow].getFieldName() + " po tym ruchu");
                        this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(ourTemp); //przesuwamy naszą bierkę na poprzednie pole
                        this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(opponentsTemp); //przywracamy wykasowaną bierkę
                        tempSet.clear();
                        return true;
                    }
                }
            }
        }
        //przywracamy stan początkowy
        this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(ourTemp);
        this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(opponentsTemp);
        return false;
    }

    public Field findKingsField(boolean whitesKing) {
        //szukam wrogiego króla i zapisuję go
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && this.fields[cc][rr].getPiece().getName().equals("K") && whitesKing != this.fields[cc][rr].getPiece().isPieceBlack) {
                    return this.fields[cc][rr];
                }
            }
        }
        return null;
    }

    public Field theirKingsCheckedFieldAfterOurMove(String ourNewColumn, int ourNewRow, boolean whitesMove) {
        //zwraca pole z checkowanym królem lub null
        if (availablePiecesMoves(this.fields[columnToNumber(ourNewColumn)][rowToArrayRow(ourNewRow)].getPiece().getName(), ourNewColumn, ourNewRow, whitesMove).contains(findKingsField(!whitesMove))) {
            if (isOurCheckMate(whitesMove)) { //jeśli ten check jest też matem
                if (whitesMove) this.whitesWon = true;
                else this.blacksWon = true;
            }
            return findKingsField(!whitesMove);
        } else return null;
    }

    public boolean moveOrFight(boolean whitesMove, String ourColumn, int ourRow, String opponentsColumn, int opponentsRow, boolean test) {
        if (!test) {
            this.checkedField = ""; //zerujemy globalną zmienną przechowującą checkowane pole
        }

        //edycja szachownicy
        this.fields[columnToNumber(opponentsColumn)][rowToArrayRow(opponentsRow)].setPiece(this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].getPiece()); //we wskazanym miejscu wstawiamy piona ze starego pola
        this.fields[columnToNumber(ourColumn)][rowToArrayRow(ourRow)].setPiece(null); //kasujemy bierkę ze starego piona

        if (!test) {
            //czy po tym ruchu zchekowaliśmy wrogiego króla
            Field theirKingsCheckedFieldAfterOurMove = theirKingsCheckedFieldAfterOurMove(opponentsColumn, opponentsRow, whitesMove);
            if (theirKingsCheckedFieldAfterOurMove != null) {
                //System.out.println("Checkujemy wrogiego," + (whitesMove ? " czarnego " : " białego ") + "króla");
                this.checkedField = theirKingsCheckedFieldAfterOurMove.getFieldName(); //wskazujemy checkowane pole globalnej zmiennej
            }
        }
        return true;
    }

    public void showScore(){
        System.out.println("Wynik białych to: " + score(true));
        System.out.println("Wynik czarnych to: " + score(false));
    }

    public int score(boolean whitesScore){
        int score = 0;
        for (int cc = 0; cc < 8; cc++) {
            for (int rr = 0; rr < 8; rr++) {
                if (this.fields[cc][rr].getPiece() != null && whitesScore != this.fields[cc][rr].getPiece().isPieceBlack) {
                    score += this.fields[cc][rr].getPiece().getWeight();
                }
            }
        }
        return score;
    }


    //converters
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
        if (column == 6) return "G";
        else return "H"; //H
    }

    public int rowToArrayRow(int row) {
        return row - 1;
    }

    public int arrayRowToRow(int arrayRow) {
        return arrayRow + 1;
    }

    public void copy(Chessboard x){
        this.setFields(x.getFields());
    }
}
