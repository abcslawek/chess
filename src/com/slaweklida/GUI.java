package com.slaweklida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class GUI implements ActionListener {

    private static JLabel label;
    private static JPanel chessboardView;
    private static JTextField userText;
    private static JButton[][] guiFields;
    private static JFrame frame;
    private static JButton mainMenu;
    private static JButton quit;
    private static JButton newGame;
    private static JButton playerVsPlayer;
    private static JButton playerVsComputer;
    private static JButton playAsWhite;
    private static JButton playAsBlack;
    private static JButton pause;
    private static Chessboard chessboard;
    private static Game game;
    private static Set<String> everyAvailableMoves;
    private static boolean whitesMove;
    private static String hashMove = "";
    private static boolean hasFirstField = false;
    private static boolean reverse;
    private static boolean vsComputer;
    private static boolean movesEnd = true;
    private static boolean isPause = false;

    public static void main(String[] args) {
        //RAMKA
        frame = new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //prevent frame from being resized
        frame.setSize(860, 680);

        //PANEL
        chessboardView = new JPanel();
        chessboardView.setLayout(null);
        frame.add(chessboardView);

        //LABEL
        label = new JLabel("");
        label.setBounds(660, 360, 160, 30);
//        label.setHorizontalTextPosition(JLabel.CENTER);
//        label.setVerticalTextPosition(JLabel.TOP);
        chessboardView.add(label);

//        userText = new JTextField(20);
//        userText.setBounds(100, 20, 165, 25);
//        panel.add(userText);


        //PRZYCISKI SZACHOWNICY
        guiFields = new JButton[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                guiFields[c][r] = new JButton(new AbstractAction("typeFieldName") {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movesEnd = false;
                        JButton o = (JButton) e.getSource();
                        String ourField = o.getName();
                        System.out.println(ourField);
                        if (!hasFirstField) {
                            hashMove = ourField + ":";
                            hasFirstField = true;
                        } else {
                            hashMove += ourField;
                            hasFirstField = false;
                            System.out.println("hashMove to " + hashMove);
                            if (everyAvailableMoves.contains(hashMove)) {
                                colourFieldsDefault();
                                chessboard.makeMove(everyAvailableMoves, hashMove, whitesMove, false);
                                refreshChessboard(reverse);

                                //CZY SZACH
                                if (chessboard.isCheck()) colourCheckedField();
                                else colourFieldsDefault();

                                //ZMIANA GRACZA
                                whitesMove = !whitesMove;

                                //DOSTĘPNE RUCHY NOWEGO GRACZA
                                everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);

                                //CZY PAT
                                isStalemate();

                                //KONIEC RUCHU
                                movesEnd = true;

                                //POKOLOROWANIE POLA STARTOWEGO I KONCOWEGO
                                colourFields(hashMove);

                            } else {
                                System.out.println("Niedostępny ruch");
                            }
                        }

                        // ****************************************************** RUCH KOMPUTERA
                        if (!chessboard.isBlacksWon() && !chessboard.isWhitesWon()) {
                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //RUCH KOMPUTERA
                                    if (vsComputer && movesEnd) {
                                        //activateFields(false);
                                        label.setText("Komputer myśli");
                                        try {
                                            Thread.sleep(600);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                        computersMove();

                                        //activateFields(true);
                                    }
                                }
                            });
                            t.start();

                        }
                        //*****************************************************
                    }
                });

                guiFields[c][r].setText("");
                guiFields[c][r].setBounds(80 * c, 80 * r, 80, 80);
                guiFields[c][r].addActionListener(new GUI());
                guiFields[c][r].setFont(new Font("Comic Sans", Font.BOLD, 45)); //zmienia czcionkę tekstu na przycisku
//                guiFields[c][r].setForeground(Color.cyan); //zmienia kolor czcionki na przycisku
//                if ((c + r) % 2 != 0) guiFields[c][r].setBackground(new Color(180, 136, 98));
//                else guiFields[c][r].setBackground(new Color(240, 216, 180));
                if ((c + r) % 2 != 0) guiFields[c][r].setBackground(new Color(180, 136, 98));
                else guiFields[c][r].setBackground(new Color(240, 216, 180));
                chessboardView.add(guiFields[c][r]);
            }
        }


        //PRZYCISK NOWA GRA
        newGame = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(false);
                game = new Game();
                newGame.setEnabled(false); //po wciśnięciu button się deaktywuje
                playerVsPlayer.setEnabled(true); //po wciśnięciu button się aktywuje
                playerVsComputer.setEnabled(true); //po wciśnięciu button się aktywuje
            }
        });
        newGame.setText("Nowa gra");
        newGame.setName("nowaGra");
        newGame.setBounds(660, 60, 160, 30);
        chessboardView.add(newGame);

        //PRZYCISK PvP
        playerVsPlayer = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerVsPlayer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playerVsComputer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playAsWhite.setEnabled(true);
                playAsBlack.setEnabled(true);
                vsComputer = false;
            }
        });
        playerVsPlayer.setText("Player vs Player");
        playerVsPlayer.setName("playerVsPlayer");
        playerVsPlayer.setBounds(660, 120, 160, 30);
        playerVsPlayer.setEnabled(false);
        chessboardView.add(playerVsPlayer);


        //PRZYCISK PvC
        playerVsComputer = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerVsComputer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playerVsPlayer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playAsWhite.setEnabled(true);
                playAsBlack.setEnabled(true);
                vsComputer = true;

            }
        });
        playerVsComputer.setText("Player vs Computer");
        playerVsComputer.setName("playerVsComputer");
        playerVsComputer.setBounds(660, 160, 160, 30);
        playerVsComputer.setEnabled(false);
        chessboardView.add(playerVsComputer);

        //PRZYCISK GRA BIALYMI
        playAsWhite = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true); //po wciśnięciu button się deaktywuje
                colourFieldsDefault();
                label.setText("");
                reverse = false;
                whitesMove = true;
                chessboard = new Chessboard();
                System.out.println("Stworzono nową szachownicę z bierkami");
                everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);
                System.out.println("Stworzono listę z możliwymi ruchami bierek");

                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        try {
                            guiFields[c][r].setText("" + ((char) (chessboard.getField(c, 7 - r).getPiece().getImage())));
                        } catch (NullPointerException f) {
                            guiFields[c][r].setText("");
                        }
                        guiFields[c][r].setName(numberToColumn(c) + (7 - r + 1));
                    }
                }


                playAsWhite.setEnabled(false); //po wciśnięciu button się deaktywuje
                playAsBlack.setEnabled(false); //po wciśnięciu button się deaktywuje
            }
        });
        playAsWhite.setText("Gra białymi");
        playAsWhite.setName("playAsWhite");
        playAsWhite.setBounds(660, 220, 160, 30);
        playAsWhite.setEnabled(false);
        chessboardView.add(playAsWhite);

        //PRZYCISK GRA CZARNYMI
        playAsBlack = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true); //po wciśnięciu button się deaktywuje
                colourFieldsDefault();
                label.setText("");
                reverse = true;
                whitesMove = true;
                chessboard = new Chessboard();
                System.out.println("Stworzono nową szachownicę z bierkami");
                everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);
                System.out.println("Stworzono listę z możliwymi ruchami bierek");

                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        try {
                            guiFields[c][r].setText("" + ((char) (chessboard.getField(7 - c, r).getPiece().getImage())));
                        } catch (NullPointerException f) {
                            guiFields[c][r].setText("");
                        }
                        guiFields[c][r].setName(numberToColumn(7 - c) + (r + 1));
                    }
                }


                playAsBlack.setEnabled(false); //po wciśnięciu button się deaktywuje
                playAsWhite.setEnabled(false); //po wciśnięciu button się deaktywuje

                //TUTAJ WYKONAJ RUCH KOMPUTERA DLA BIALYCH **************************
                if (vsComputer) {
                    if (!chessboard.isBlacksWon() && !chessboard.isWhitesWon()) {
                        Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //RUCH KOMPUTERA JESLI JEST CZARNY
                                if (vsComputer && movesEnd) {
                                    //activateFields(false);
                                    label.setText("Komputer myśli");
                                    try {
                                        Thread.sleep(600);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                    computersMove();
                                    label.setText("");
                                    //activateFields(true);
                                }
                            }
                        });
                        t2.start();
                    }
                }
            }
        });
        playAsBlack.setText("Gra czarnymi");
        playAsBlack.setName("playAsBlack");
        playAsBlack.setBounds(660, 260, 160, 30);
        playAsBlack.setEnabled(false);
        chessboardView.add(playAsBlack);


        //PRZYCISK PAUZA
        pause = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newGame.setEnabled(!isPause); //po wciśnięciu button się deaktywuje
                activateFields(isPause);
                if(!isPause) pause.setBackground(new Color(165, 246, 158));
                else pause.setBackground(null);
                isPause = !isPause;
            }
        });
        pause.setText("Pause");
        pause.setName("pause");
        pause.setBounds(700, 320, 80, 30);
        pause.setEnabled(false);
        chessboardView.add(pause);


        frame.setVisible(true);
    }

    public static void computersMove() {

        String bestMove = game.minimax(chessboard, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, whitesMove, 1).getBestMove();
        chessboard.makeMove(chessboard.everyAvailableMove(whitesMove), bestMove, whitesMove, false);
        colourFieldsDefault();
        refreshChessboard(reverse);
        if (chessboard.isCheck()) colourCheckedField();
        else colourFieldsDefault();
        colourFields(bestMove);
        whitesMove = !whitesMove;
        everyAvailableMoves = chessboard.everyAvailableMove(whitesMove);
        isStalemate();
    }

    public static String numberToColumn(int column) {
        if (column == 0) return "A";
        if (column == 1) return "B";
        if (column == 2) return "C";
        if (column == 3) return "D";
        if (column == 4) return "E";
        if (column == 5) return "F";
        if (column == 6) return "G";
        else return "H"; //H
    }

    public static int columnToNumber(String column) {
        if (column.equals("A")) return 0;
        if (column.equals("B")) return 1;
        if (column.equals("C")) return 2;
        if (column.equals("D")) return 3;
        if (column.equals("E")) return 4;
        if (column.equals("F")) return 5;
        if (column.equals("G")) return 6;
        else return 7; //H
    }

    public static void refreshChessboard(boolean reverse) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                try {
                    if (!reverse)
                        guiFields[c][r].setText("" + ((char) (chessboard.getField(c, 7 - r).getPiece().getImage())));
                    else guiFields[c][r].setText("" + ((char) (chessboard.getField(7 - c, r).getPiece().getImage())));
                } catch (NullPointerException e) {
                    guiFields[c][r].setText("");
                }
            }
        }
    }

    public static void colourCheckedField() {
        String checkedField = chessboard.getCheckedField();
        if (!checkedField.equals("")) {
            int r = Integer.parseInt("" + checkedField.charAt(1)) - 1;
            int c = columnToNumber("" + checkedField.charAt(0));
            if (!reverse) guiFields[c][7 - r].setBackground(Color.RED);
            else guiFields[7 - c][r].setBackground(Color.RED);


            //CZY TEŻ JEST MAT?
            if (chessboard.isWhitesWon() || chessboard.isBlacksWon()) {
                if (!reverse) guiFields[c][7 - r].setBackground(Color.GREEN);
                else guiFields[7 - c][r].setBackground(Color.GREEN);
                newGame.setEnabled(true);
                label.setText("Wygrana " + (chessboard.isWhitesWon() ? "białych" : "czarnych"));
                pause.setEnabled(false);
            } else label.setText("");

        }
    }

    public static void colourFieldsDefault() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((c + r) % 2 != 0) guiFields[c][r].setBackground(new Color(180, 136, 98));
                else guiFields[c][r].setBackground(new Color(240, 216, 180));
            }
        }
        label.setText("");
    }

    public static void colourFields(String fields){
        int ourRow = Integer.parseInt("" + fields.charAt(1)) - 1;
        int ourColumn = columnToNumber("" + fields.charAt(0));
        int opponentsRow = Integer.parseInt("" + fields.charAt(4)) - 1;
        int opponentsColumn = columnToNumber("" + fields.charAt(3));

        if (!reverse) {
            guiFields[ourColumn][7 - ourRow].setBackground(new Color(234, 233, 129));
            guiFields[opponentsColumn][7 - opponentsRow].setBackground(new Color(234, 233, 129));
        }
        else {
            guiFields[7 - ourColumn][ourRow].setBackground(new Color(234, 233, 129));
            guiFields[7 - opponentsColumn][opponentsRow].setBackground(new Color(234, 233, 129));
        }
    }

    public static void isStalemate() {
        if (everyAvailableMoves.isEmpty() && !chessboard.isWhitesWon() && !chessboard.isBlacksWon()) {
            newGame.setEnabled(true);
            label.setText("Pat");
        }
    }

    public static void activateFields(boolean on) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                guiFields[c][r].setEnabled(on);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}