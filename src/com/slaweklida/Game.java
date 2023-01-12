package com.slaweklida;

import java.util.Scanner;
import java.util.Set;

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
        Chessboard chessboard = new Chessboard(true);
        boolean shouldContinue = false;

        while (!shouldContinue) {
            if (choice == 1) chessboard.showChessboard();
            else chessboard.showReverseChessboard();

            //set wszystkich dostępnych ruchów do wykonania
            Set<String> everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);

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
    }

}
