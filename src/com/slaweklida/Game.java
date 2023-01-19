package com.slaweklida;

import com.slaweklida.pieces.Queen;

import java.util.*;

public class Game {
    public Game() {
        System.out.println("Stworzono nową grę");
    }



    public void playerVsPlayer() {
        System.out.println("Wybrano tryb player vs player");
        System.out.println("1. Gra białymi");
        System.out.println("2. Gra czarnymi");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        boolean whitesMove = true;
        Chessboard chessboard = new Chessboard();
        boolean shouldContinue = false;

        while (!shouldContinue) {
            if (choice == 1) chessboard.showChessboard();
            else chessboard.showReverseChessboard();

            //set wszystkich dostępnych ruchów do wykonania
            Set<String> everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);

            //pokazuje wynik gracza
            chessboard.showScore();

            //sprawdza czy jest pat
            if (everyAvailableMoves.isEmpty()) {
                System.out.println("Pat");
                shouldContinue = true;
            } else {
                System.out.println("1. Wykonaj ruch");
                System.out.println("2. Wyświetl szachownicę");
                System.out.println("3. Wyjdź z gry");
                int option = scanner.nextInt();
                boolean correctMove = false;
                scanner.nextLine();
                switch (option) {
                    case 1:
                        while (!correctMove) { //sprawdza czy ruch jest poprawny
                            String move = scanner.nextLine();
                            correctMove = chessboard.makeMove(everyAvailableMoves, move, whitesMove);
                        }
                        //zmiana zawodnika
                        //trzeba ustalić, że jeśli nie da się wykonać takiego ruchu np. białymi to trzeba próbować aż do skutku
                        if (!whitesMove) whitesMove = true;
                        else whitesMove = false;
                        System.out.println("Zmiana zawodnika na " + (whitesMove ? "białego" : "czarnego"));

                        //czy mat
                        if (chessboard.isWhitesWon()) {
                            System.out.println("Białe wygrały");
                            shouldContinue = true;
                            if (choice == 1) chessboard.showChessboard();
                            else chessboard.showReverseChessboard();
                        } else if (chessboard.isBlacksWon()) {
                            System.out.println("Czarne wygrały");
                            shouldContinue = true;
                            if (choice == 1) chessboard.showChessboard();
                            else chessboard.showReverseChessboard();
                        }
                        break;
                    case 2:
                        if (choice == 1) chessboard.showChessboard();
                        else chessboard.showReverseChessboard();
                        break;
                    case 3:
                        shouldContinue = true;
                        break;
                }
            }
        }
    }

    public void playerVsComputer() {
        System.out.println("Wybrano tryb player vs computer");
        System.out.println("1. Gra białymi");
        System.out.println("2. Gra czarnymi");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        boolean whitesMove = true;
        Chessboard chessboard = new Chessboard();
        boolean shouldContinue = false;

        while (!shouldContinue) {
            if (choice == 1) chessboard.showChessboard();
            else chessboard.showReverseChessboard();

            //set wszystkich dostępnych ruchów do wykonania
            Set<String> everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);

            //pokazuje wynik gracza
            //chessboard.showScore();
            evaluate(chessboard, true);

            //sprawdza czy jest pat
            if (everyAvailableMoves.isEmpty()) {
                System.out.println("Pat");
                shouldContinue = true;
            } else {
                //jeśli gramy czarnymi to komputer wykonuje pierwszy ruch
                if (choice == 2) {
                    System.out.println("Teraz komputer wykona ruch");
                    chessboard.makeMove(everyAvailableMoves, minimax(chessboard, 2, 0, 0, whitesMove).getBestMove(), whitesMove);

                    //zmiana zawodnika
                    //trzeba ustalić, że jeśli nie da się wykonać takiego ruchu np. białymi to trzeba próbować aż do skutku
                    if (!whitesMove) whitesMove = true;
                    else whitesMove = false;
                    System.out.println("Zmiana zawodnika na " + (whitesMove ? "białego" : "czarnego"));

                    //czy mat
                    if (chessboard.isWhitesWon()) {
                        System.out.println("Białe wygrały");
                        shouldContinue = true;
                        if (choice == 1) chessboard.showChessboard();
                        else chessboard.showReverseChessboard();
                    } else if (chessboard.isBlacksWon()) {
                        System.out.println("Czarne wygrały");
                        shouldContinue = true;
                        if (choice == 1) chessboard.showChessboard();
                        else chessboard.showReverseChessboard();
                    }
                }

                //czy jest mat po ruchu komputera
                if (chessboard.isWhitesWon()) {
                    System.out.println("Białe wygrały");
                    shouldContinue = true;
                    if (choice == 1) chessboard.showChessboard();
                    else chessboard.showReverseChessboard();
                } else if (chessboard.isBlacksWon()) {
                    System.out.println("Czarne wygrały");
                    shouldContinue = true;
                    if (choice == 1) chessboard.showChessboard();
                    else chessboard.showReverseChessboard();
                }

                //teraz my wykonujemy ruch
                System.out.println("1. Wykonaj ruch");
                System.out.println("2. Wyświetl szachownicę");
                System.out.println("3. Wyjdź z gry");
                int option = scanner.nextInt();
                boolean correctMove = false;
                scanner.nextLine();
                switch (option) {
                    case 1:
                        //wykonanie ruchu
                        while (!correctMove) { //sprawdza czy ruch jest poprawny
                            String move = scanner.nextLine();
                            correctMove = chessboard.makeMove(everyAvailableMoves, move, whitesMove);
                        }

                        //ukazanie tablicy
                        if (choice == 1) chessboard.showChessboard();
                        else chessboard.showReverseChessboard();

                        //trzeba ustalić, że jeśli nie da się wykonać takiego ruchu np. białymi to trzeba próbować aż do skutku

                        //zmiana zawodnika
                        whitesMove = !whitesMove;
                        System.out.println("Zmiana zawodnika na " + (whitesMove ? "białego" : "czarnego"));

                        //czy mat
                        if (chessboard.isWhitesWon()) {
                            System.out.println("Białe wygrały");
                            shouldContinue = true;
                            if (choice == 1) chessboard.showChessboard();
                            else chessboard.showReverseChessboard();
                        } else if (chessboard.isBlacksWon()) {
                            System.out.println("Czarne wygrały");
                            shouldContinue = true;
                            if (choice == 1) chessboard.showChessboard();
                            else chessboard.showReverseChessboard();
                        }
                        break;

                    case 2:
                        if (choice == 1) chessboard.showChessboard();
                        else chessboard.showReverseChessboard();
                        break;
                    case 3:
                        shouldContinue = true;
                        break;
                }
            }
            if (choice == 1) {
                //teraz komputer wykonuje ruch
                //System.out.println("Teraz komputer wykona ruch");
                chessboard.makeMove(chessboard.everyAvailableMove(whitesMove), minimax(chessboard, 2, 0, 0, whitesMove).getBestMove(), whitesMove);
                //System.out.println("Komputer wykonał ruch");

                //zmiana zawodnika
                whitesMove = !whitesMove;
                System.out.println("Zmiana zawodnika na " + (whitesMove ? "białego" : "czarnego"));
            }
        }

    }

    public int evaluate(Chessboard chessboard, boolean showScore) { //1-white, 2-black
        int evaluate = chessboard.score(true) - chessboard.score(false);
        if(showScore) System.out.println("Evaluate = " + evaluate);
        return evaluate;
    }

    public Result minimax(Chessboard chessboard, int depth, int alpha, int beta, boolean whitesMove) {
        Result result = new Result("", 0);
        if (depth == 0 || chessboard.isWhitesWon() || chessboard.isBlacksWon() || chessboard.isStalemate()) {
            //System.out.println("Depth = 0 albo szach mat albo pat");
            result.setBestMove("Depth = 0");
            result.setMaxMinEval(evaluate(chessboard, false));
            return result;
        }

        Set<String> moves = chessboard.everyAvailableMove(whitesMove);

        //randomowy move z listy, bestMove póki co to randomowy move
        int size = moves.size();
        int item = new Random().nextInt(size);
        int i = 0;
        String bestMove = "";
        for (String move : moves) {
            if (i == item) bestMove = move;
            i++;
        }

        //początkowa ocena to 0
        int currentEval = 0;

        if (whitesMove) {
            int maxEval = Integer.MIN_VALUE;
            for (String move : moves) {

                //ZAPIS FIGURY Z POLA PRZECIWNIKA
                String opponentsColumn = ("" + move.charAt(3)).toUpperCase(Locale.ROOT);
                int opponentsRow = Integer.parseInt("" + move.charAt(4));
                Piece tempPiece = new Queen(true);
                boolean wasPiece = false;
                try {
                    tempPiece = chessboard.getField(chessboard.columnToNumber(opponentsColumn), chessboard.rowToArrayRow(opponentsRow)).getPiece();
                    wasPiece = true;
                }catch (NullPointerException e){
                    //System.out.println("Na tym polu nie było figury do zapamiętania");
                }

                //WYKONANIE RUCHU
                //System.out.println("Wykonujemy ruch w forze w metodzie minimax białymi " + move);
                chessboard.makeMove(moves, move, whitesMove);

                //OCENA
                result = minimax(chessboard, depth - 1, alpha, beta, false);
                currentEval = result.getMaxMinEval();

                //COFANIE RUCHU
                //System.out.println("Cofamy ruch białymi");
                String ourColumn = ("" + move.charAt(0)).toUpperCase(Locale.ROOT);
                int ourRow = Integer.parseInt("" + move.charAt(1));
                chessboard.moveOrFight(true, opponentsColumn, opponentsRow, ourColumn, ourRow, true);
                if(wasPiece) chessboard.getField(chessboard.columnToNumber(opponentsColumn), chessboard.rowToArrayRow(opponentsRow)).setPiece(tempPiece);

                //CZY NOWA OCENA JEST LEPSZA OD OSTATNIEJ?
                if (currentEval > maxEval) {
                    maxEval = currentEval;
                    bestMove = move;
                }
//                //przycinanie alfa beta, jeśli
//                if (alpha > currentEval) alpha = alpha;
//                else alpha = currentEval;
//                if (beta <= alpha) break;
            }
            result.setBestMove(bestMove);
            result.setMaxMinEval(maxEval);
            return result;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (String move : moves) {

                //ZAPIS FIGURY Z POLA PRZECIWNIKA
                String opponentsColumn = ("" + move.charAt(3)).toUpperCase(Locale.ROOT);
                int opponentsRow = Integer.parseInt("" + move.charAt(4));
                Piece tempPiece = new Queen(true);
                boolean wasPiece = false;
                try {
                    tempPiece = chessboard.getField(chessboard.columnToNumber(opponentsColumn), chessboard.rowToArrayRow(opponentsRow)).getPiece();
                    wasPiece = true;
                }catch (NullPointerException e){
                    //System.out.println("Na tym polu nie było figury do zapamiętania");
                }

                //WYKONANIE RUCUH
                //System.out.println("Wykonujemy ruch w forze w metodzie minimax czarnymi " + move);
                chessboard.makeMove(moves, move, whitesMove);

                //OCENA
                result = minimax(chessboard, depth - 1, alpha, beta, true);
                currentEval = result.getMaxMinEval();

                //COFANIE RUCHU
                //System.out.println("Cofamy ruch czarnymi");
                String ourColumn = ("" + move.charAt(0)).toUpperCase(Locale.ROOT);
                int ourRow = Integer.parseInt("" + move.charAt(1));
                chessboard.moveOrFight(false, opponentsColumn, opponentsRow, ourColumn, ourRow, true);
                if(wasPiece) chessboard.getField(chessboard.columnToNumber(opponentsColumn), chessboard.rowToArrayRow(opponentsRow)).setPiece(tempPiece);

                //CZY NOWA OCENA JEST LEPSZA OD OSTATNIEJ
                if (currentEval < minEval) {
                    minEval = currentEval;
                    bestMove = move;
                }
//                //alpha beta
//                if (beta < currentEval) beta = beta;
//                else beta = currentEval;
//                if (beta <= alpha) break;
            }
            result.setBestMove(bestMove);
            result.setMaxMinEval(minEval);
            return result;
        }
    }







}
