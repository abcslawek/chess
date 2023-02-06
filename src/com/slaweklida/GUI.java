package com.slaweklida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public static void main(String[] args) {
        //RAMKA
        frame = new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); //prevent frame from being resized
        frame.setSize(860, 680);


        chessboardView = new JPanel();
        chessboardView.setLayout(null);
        frame.add(chessboardView);
//        label = new JLabel("Szachy");
//        label.setBounds(10, 20, 80, 25);
//        panel.add(label);

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

                    }
                });
                guiFields[c][r].setText(numberToColumn(c) + (8 - r));
                guiFields[c][r].setName(numberToColumn(c) + (8 - r));
                guiFields[c][r].setBounds(80 * c, 80 * r, 80, 80);
                guiFields[c][r].addActionListener(new GUI());
//                guiFields[c][r].setFont(new Font("Comic Sans", Font.BOLD,25)); //zmienia czcionkę tekstu na przycisku
//                guiFields[c][r].setForeground(Color.cyan); //zmienia kolor czcionki na przycisku
                if((c+r) % 2 != 0) guiFields[c][r].setBackground(Color.BLACK);
                else guiFields[c][r].setBackground(Color.WHITE);
                chessboardView.add(guiFields[c][r]);
            }
        }


        //PRZYCISK NOWA GRA
        newGame = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                newGame.setEnabled(false); //po wciśnięciu button się deaktywuje
                chessboardView.add(playerVsPlayer);
                chessboardView.add(playerVsComputer);
            }
        });
        newGame.setText("Nowa gra");
        newGame.setName("nowaGra");
        newGame.setBounds(660, 60, 160,30);
        chessboardView.add(newGame);

        //PRZYCISK PvP
        playerVsPlayer = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerVsPlayer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playerVsComputer.setEnabled(false); //po wciśnięciu button się deaktywuje
                chessboardView.add(playAsWhite);
                chessboardView.add(playAsBlack);
            }
        });
        playerVsPlayer.setText("Player vs Player");
        playerVsPlayer.setName("playerVsPlayer");
        playerVsPlayer.setBounds(660, 120, 160,30);


        //PRZYCISK PvC
        playerVsComputer = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerVsComputer.setEnabled(false); //po wciśnięciu button się deaktywuje
                playerVsPlayer.setEnabled(false); //po wciśnięciu button się deaktywuje
                chessboardView.add(playAsWhite);
                chessboardView.add(playAsBlack);
            }
        });
        playerVsComputer.setText("Player vs Computer");
        playerVsComputer.setName("playerVsComputer");
        playerVsComputer.setBounds(660, 160, 160,30);

                //PRZYCISK Gra białymi
                playAsWhite = new JButton(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {







                        playAsWhite.setEnabled(false); //po wciśnięciu button się deaktywuje
                        playAsBlack.setEnabled(false); //po wciśnięciu button się deaktywuje
                    }
                });
                playAsWhite.setText("Gra białymi");
                playAsWhite.setName("playAsWhite");
                playAsWhite.setBounds(660, 220, 160,30);

                //PRZYCISK Gra białymi
                playAsBlack = new JButton(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playAsBlack.setEnabled(false); //po wciśnięciu button się deaktywuje
                        playAsWhite.setEnabled(false); //po wciśnięciu button się deaktywuje
                    }
                });
                playAsBlack.setText("Gra czarnymi");
                playAsBlack.setName("playAsBlack");
                playAsBlack.setBounds(660, 260, 160,30);









        frame.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        String ourField = o.getName();
        System.out.println(ourField);
    }
}
