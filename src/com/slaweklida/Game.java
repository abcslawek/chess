package com.slaweklida;
import java.util.Scanner;

public class Game {


    public Game(){
        System.out.println("Stworzono nową grę");
    }

    public void playerVsPlayer(){
        System.out.println("Wybrano tryb player vs player");
        System.out.println("1. Gra białymi");
        System.out.println("2. Gra czarnymi");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Chessboard chessboard = new Chessboard(true);
                System.out.println("1. Wykonaj ruch");
                System.out.println("2. Wyświetl szachownicę");
                int option = scanner.nextInt();
                switch (option){
                    case 2 -> chessboard.showChessboard();
                }
                break;
            case 2:

            default:
                break;
        }
    }

    public void playerVsComputer(){
        System.out.println("Wybrano tryb player vs computer");
    }

}
