package com.slaweklida;

import java.util.Scanner;

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

        switch (choice) {
            case 1:
                Chessboard chessboard = new Chessboard(true);
                boolean shouldContinue = false;

                while (!shouldContinue) {
                    System.out.println("1. Wykonaj ruch");
                    System.out.println("2. Wyświetl szachownicę");
                    System.out.println("3. Wyjdź z gry");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            String move = scanner.nextLine();
                            chessboard.makeMove(move, whitesMove);
                            chessboard.showChessboard();
                            //zmiana zawodnika
                            if (!whitesMove) whitesMove = true;
                            else whitesMove = false;
                            break;
                        case 2:
                            chessboard.showChessboard();
                            break;
                        case 3:
                            shouldContinue = true;
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public void playerVsComputer() {
        System.out.println("Wybrano tryb player vs computer");
    }

}
