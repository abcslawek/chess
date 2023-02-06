package com.slaweklida;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Chess");
//        GUI gui = new GUI();
        while (true) {
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1. Nowa gra");
            System.out.println("2. Wyjście");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Game game = new Game();
                    System.out.println("1. Player Vs Player");
                    System.out.println("2. Player Vs Computer");
                    int modeChoice = scanner.nextInt();
                    switch (modeChoice) {
                        case 1 -> game.playerVsPlayer();
                        case 2 -> game.playerVsComputer();
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wybierz jedną z opcji");
                    break;
            }
        }
    }


}
