package com.slaweklida;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Chess");
        while(true){
            System.out.println("Wybierz jedną z opcji:\n1. Nowa gra\n2. Wyjście");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
//                    newGame();
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
