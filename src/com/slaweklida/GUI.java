package com.slaweklida;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.awt.Font;


public class GUI implements ActionListener {

    private static JDBCDemo jdbcDemo;
    private static JLabel label;
    private static JLabel chessboardColorLabel;
    private static JLabel enterNick;
    private static JPanel chessboardView;
    private static JButton[][] guiFields;
    private static JFrame frame;
    private static JButton newGame;
    private static JButton playerVsPlayer;
    private static JButton playerVsComputer;
    private static JButton playAsWhite;
    private static JButton playAsBlack;
    private static JButton pause;
    public static JButton highscore;
    private static JButton brownColor;
    private static JButton blueColor;
    private static JButton greyColor;
    private static JButton addNickname;
    private static Chessboard chessboard;
    private static Game game;
    private static Set<String> everyAvailableMoves;
    private static boolean whitesMove;
    private static String hashMove = "";
    private static String ourField = "";
    private static String opponentsField = "";
    private static String bestMove = "";
    private static String nickname = "";
    private static boolean hasFirstField = false;
    private static boolean reverse;
    private static boolean vsComputer;
    private static boolean movesEnd = true;
    private static boolean isPause = false;
    private static boolean computerIsThinking;
    private static Color firstColor = new Color(180, 136, 98);
    private static Color secondColor = new Color(240, 216, 180);
    private static JTextField nicknameField;
    private static Font font;
    private static final int yy = 30;
    public static HighscoreWindow highscoreWindow;
    //IKONA
    private static ImageIcon icon = new ImageIcon("src/com/slaweklida/point.png");
    private static ImageIcon okIcon = new ImageIcon("src/com/slaweklida/okIcon.png");


    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, FontFormatException {

        //JDBC
        jdbcDemo = new JDBCDemo();

        //RAMKA
        frame = new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());
        frame.setSize(860, 680);

        //PANEL
        chessboardView = new JPanel();
        chessboardView.setLayout(null);
        frame.add(chessboardView);

        //NICKNAME FIELD
        nicknameField = new JTextField();
        nicknameField.setBounds(660, 20 + yy, 120, 30);
        nicknameField.setFont(new Font("", Font.BOLD, 20));
        nicknameField.setBackground(Color.WHITE);

        //LIMIT POLA TEKSTOWEGO, BACKSPACE/DELETE SĄ DOZWOLONE
        nicknameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                int length = nicknameField.getText().length();
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    if (!nicknameField.isEditable()) {
                        nicknameField.setEditable(true);
                    }

                } else /*if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')*/ {
                    if (length <= 8) {
                        nicknameField.setEditable(true);
                    } else {
                        nicknameField.setEditable(false);
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
        });

        //AKTYWATOR I DEAKTYWATOR PRZYCISKU 'DODAJ GRACZA'
        nicknameField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (nicknameField.getText().equals("") || nicknameField.getText().length() >= 10) {
                    addNickname.setEnabled(false);
                } else {
                    addNickname.setEnabled(true);
                }
            }
        });
        chessboardView.add(nicknameField);

        //PRZYCISK DODAJ NICK GRACZA
        addNickname = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newGame.setEnabled(true);
                nicknameField.setEnabled(false);
                addNickname.setEnabled(false);
                nickname = nicknameField.getText();
                System.out.println("Nickname to " + nickname);

                //SPRAWDZENIE CZY GRACZ JEST W BAZIE DANYCH, JESLI NIE TO UTWÓRZ NOWEGO
                if (jdbcDemo.isPlayerInDatabase(nickname)) {
                    System.out.println(nickname + " jest już w bazie");
                } else {
                    jdbcDemo.addPlayer(nickname);
                    System.out.println(nickname + " został dodany");
                }
            }

        });
        addNickname.setBounds(790, 20 + yy, 30, 30);
        addNickname.setFont(new Font("", Font.BOLD, 10));
        addNickname.setIcon(okIcon);
        addNickname.setEnabled(false);
        chessboardView.add(addNickname);

        //LABEL ENTER NICK
        enterNick = new JLabel("Enter your nick");
        enterNick.setBounds(660, 0 + yy, 160, 15);
        chessboardView.add(enterNick);

        //LABEL
        label = new JLabel("");
        label.setBounds(660, 360 + yy, 160, 30);
        chessboardView.add(label);

        //TEKST KOLOR SZACHOWNICY
        chessboardColorLabel = new JLabel("Choose chessboard's color");
        chessboardColorLabel.setBounds(660, 500 + yy, 160, 30);
        chessboardView.add(chessboardColorLabel);

        //PRZYCISK KOLORU SZACHOWNICY
        brownColor = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstColor = new Color(180, 136, 98);
                secondColor = new Color(240, 216, 180);
                colourFieldsDefault();

                if (!vsComputer) colourTwoFields(hashMove);
                else if (hashMove.isEmpty()) colourTwoFields(bestMove); //do poprawy
                else colourTwoFields(hashMove);
                colourCheckedField();
            }
        });
        brownColor.setBounds(670, 540 + yy, 30, 30);
        brownColor.setBackground(new Color(180, 136, 98));
        chessboardView.add(brownColor);

        blueColor = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstColor = new Color(45, 116, 255);
                secondColor = new Color(162, 188, 239);
                colourFieldsDefault();

                if (!vsComputer) colourTwoFields(hashMove);
                else if (hashMove.isEmpty()) colourTwoFields(bestMove); //do poprawy
                else colourTwoFields(hashMove);
                colourCheckedField();
            }
        });
        blueColor.setBounds(720, 540 + yy, 30, 30);
        blueColor.setBackground(new Color(45, 116, 255));
        chessboardView.add(blueColor);

        greyColor = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstColor = new Color(119, 119, 119);
                secondColor = new Color(197, 197, 197);
                colourFieldsDefault();

                if (!vsComputer) colourTwoFields(hashMove);
                else if (hashMove.isEmpty()) colourTwoFields(bestMove); //do poprawy
                else colourTwoFields(hashMove);
                colourCheckedField();
            }
        });
        greyColor.setBounds(770, 540 + yy, 30, 30);
        greyColor.setBackground(new Color(119, 119, 119));
        chessboardView.add(greyColor);

        //PRZYCISKI SZACHOWNICY
        guiFields = new JButton[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {

                guiFields[c][r] = new JButton(new AbstractAction("typeFieldName") {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movesEnd = false;
                        JButton o = (JButton) e.getSource();
                        if (!computerIsThinking && !isPause) {
                            if (!hasFirstField) {
                                ourField = o.getName();
                                System.out.println(ourField);
                                hashMove = ourField + ":";
                                hasFirstField = true;
                                colourOneField(ourField, true);
                                colourFields(ourField, true);

                            } else {
                                opponentsField = o.getName();
                                colourOneField(ourField, false);
                                colourFields(ourField, false);
                                hashMove += opponentsField;
                                hasFirstField = false;
                                System.out.println("hashMove to " + hashMove);
                                if (everyAvailableMoves.contains(hashMove)) {
                                    colourFieldsDefault();
                                    chessboard.makeMove(everyAvailableMoves, hashMove, whitesMove, false);

                                    //DŹWIĘK
                                    try {
                                        playSound("src/moveSound.wav");
                                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                        ex.printStackTrace();
                                    }

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
                                    colourTwoFields(hashMove);

                                } else {
                                    colourOneField(ourField, false);
                                    System.out.println("Niedostępny ruch");
                                }
                            }
                        }

                        // ****************************************************** RUCH KOMPUTERA
                        if (!chessboard.isBlacksWon() && !chessboard.isWhitesWon()) {
                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    //RUCH KOMPUTERA
                                    if (vsComputer && movesEnd) {
                                        computerIsThinking = true;
                                        label.setText("PC is thinking...");
                                        try {
                                            Thread.sleep(600);
                                        } catch (InterruptedException ex) {
                                            ex.printStackTrace();
                                        }
                                        computersMove();

                                        //DŹWIĘK
                                        try {
                                            playSound("src/moveSound.wav");
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                            ex.printStackTrace();
                                        }
                                        computerIsThinking = false;
                                        hashMove = "";
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
                //guiFields[c][r].setFont(new Font("Serif", Font.BOLD, 45)); //zmienia czcionkę tekstu na przycisku

                //ZALADOWANIE NOWEJ CZCIONKI
                font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/slaweklida/CASEFONT.TTF"));
                guiFields[c][r].setFont(font.deriveFont(Font.BOLD, 45)); //zmienia czcionkę tekstu na przycisku

//                guiFields[c][r].setForeground(Color.cyan); //zmienia kolor czcionki na przycisku
                if ((c + r) % 2 != 0) guiFields[c][r].setBackground(firstColor);
                else guiFields[c][r].setBackground(secondColor);
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
        newGame.setEnabled(false);
        newGame.setText("New game");
        newGame.setName("nowaGra");
        newGame.setBounds(660, 60 + yy, 160, 30);
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
        playerVsPlayer.setBounds(660, 120 + yy, 160, 30);
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
        playerVsComputer.setText("Player vs PC");
        playerVsComputer.setName("playerVsComputer");
        playerVsComputer.setBounds(660, 160 + yy, 160, 30);
        playerVsComputer.setEnabled(false);
        chessboardView.add(playerVsComputer);

        //PRZYCISK GRA BIALYMI
        playAsWhite = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bestMove = "";
                hashMove = "";
                activateFields(true);
                pause.setEnabled(true); //po wciśnięciu button się deaktywuje
                pause.setBackground(null);
                isPause = false;
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
        playAsWhite.setText("as whites");
        playAsWhite.setName("playAsWhite");
        playAsWhite.setBounds(660, 220 + yy, 160, 30);
        playAsWhite.setEnabled(false);
        chessboardView.add(playAsWhite);

        //PRZYCISK GRA CZARNYMI
        playAsBlack = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bestMove = "";
                hashMove = "";
                activateFields(true);
                pause.setEnabled(true); //po wciśnięciu button się deaktywuje
                pause.setBackground(null);
                isPause = false;
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
                    computerIsThinking = true;
                    if (!chessboard.isBlacksWon() && !chessboard.isWhitesWon()) {
                        Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //RUCH KOMPUTERA JESLI JEST CZARNY
                                if (vsComputer && movesEnd) {
                                    //activateFields(false);
                                    label.setText("PC is thinking...");
                                    try {
                                        Thread.sleep(600);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                    computersMove();
                                    //DŹWIĘK
                                    try {
                                        playSound("src/moveSound.wav");
                                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                                        ex.printStackTrace();
                                    }
                                    label.setText("");
                                }
                            }
                        });
                        t2.start();
                    }
                    computerIsThinking = false;
                }
            }
        });
        playAsBlack.setText("as blacks");
        playAsBlack.setName("playAsBlack");
        playAsBlack.setBounds(660, 260 + yy, 160, 30);
        playAsBlack.setEnabled(false);
        chessboardView.add(playAsBlack);

        //PRZYCISK PAUZA
        pause = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame.setEnabled(!isPause); //po wciśnięciu button się deaktywuje
                activateFields(isPause);
                if (!isPause) pause.setBackground(new Color(165, 246, 158));
                else pause.setBackground(null);
                isPause = !isPause;
            }
        });
        pause.setText("Pause");
        pause.setName("pause");
        pause.setBounds(700, 320 + yy, 80, 30);
        pause.setEnabled(false);
        chessboardView.add(pause);

        //PRZYCISK HIGHSCORE
        highscore = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highscore.setEnabled(false);
                highscoreWindow = new HighscoreWindow(jdbcDemo);
            }
        });
        highscore.setText("Highscore");
        highscore.setName("test");
        highscore.setBounds(660, 390 + yy, 160, 30);
        highscore.setEnabled(true);
        chessboardView.add(highscore);

        frame.setVisible(true);
    }

    public static void computersMove() {
        bestMove = game.minimax(chessboard, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, whitesMove, 1).getBestMove();
        chessboard.makeMove(chessboard.everyAvailableMove(whitesMove), bestMove, whitesMove, false);
        colourFieldsDefault();
        refreshChessboard(reverse);
        if (chessboard.isCheck()) colourCheckedField();
        else colourFieldsDefault();
        colourTwoFields(bestMove);
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

    public static void playSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
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
        if (!checkedField.equals("") /*&& !isPause*/) {
            int r = Integer.parseInt("" + checkedField.charAt(1)) - 1;
            int c = columnToNumber("" + checkedField.charAt(0));
            if (!reverse) guiFields[c][7 - r].setBackground(Color.RED);
            else guiFields[7 - c][r].setBackground(Color.RED);

            //CZY TEŻ JEST MAT?
            if ((chessboard.isWhitesWon() || chessboard.isBlacksWon())) {
                if (!reverse) guiFields[c][7 - r].setBackground(Color.GREEN);
                else guiFields[7 - c][r].setBackground(Color.GREEN);


                if (!isPause) {
                    try {
                        playSound("src/gameOverSound.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }

                    newGame.setEnabled(true);
                    label.setText((chessboard.isWhitesWon() ? "Whites" : "Blacks") + " won");

                    //ZAPIS DO BAZY DANYCH
                    if ((vsComputer && !reverse && chessboard.isWhitesWon()) || (vsComputer && reverse && chessboard.isBlacksWon()))
                        jdbcDemo.saveScore(nickname);
                }
                isPause = true;
                pause.setEnabled(false);
            } else label.setText("");

        }
    }

    public static void colourFieldsDefault() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if ((c + r) % 2 != 0) guiFields[c][r].setBackground(firstColor);
                else guiFields[c][r].setBackground(secondColor);
            }
        }
        label.setText("");
    }

    public static void colourOneField(String field, boolean colour) {
        int r = Integer.parseInt("" + field.charAt(1)) - 1;
        int c = columnToNumber("" + field.charAt(0));
        if (colour) {
            if (!reverse) guiFields[c][7 - r].setBackground(new Color(171, 234, 129));
            else guiFields[7 - c][r].setBackground(new Color(171, 234, 129));
        } else {
            if (!reverse) {
                if ((c + r) % 2 != 0) guiFields[c][7 - r].setBackground(secondColor);
                else guiFields[c][7 - r].setBackground(firstColor);
            } else {
                if ((c + r) % 2 != 0) guiFields[7 - c][r].setBackground(secondColor);
                else guiFields[7 - c][r].setBackground(firstColor);
            }
        }
        colourCheckedField();
    }

    public static void colourTwoFields(String fields) {
        int ourRow = Integer.parseInt("" + fields.charAt(1)) - 1;
        int ourColumn = columnToNumber("" + fields.charAt(0));
        int opponentsRow = Integer.parseInt("" + fields.charAt(4)) - 1;
        int opponentsColumn = columnToNumber("" + fields.charAt(3));

        if (!reverse) {
            guiFields[ourColumn][7 - ourRow].setBackground(new Color(234, 233, 129));
            guiFields[opponentsColumn][7 - opponentsRow].setBackground(new Color(234, 233, 129));
        } else {
            guiFields[7 - ourColumn][ourRow].setBackground(new Color(234, 233, 129));
            guiFields[7 - opponentsColumn][opponentsRow].setBackground(new Color(234, 233, 129));
        }
    }

    public static void colourFields(String ourField, boolean colour) {
        for (String s : everyAvailableMoves) {
            if (s.startsWith(ourField)) {
                colourOneField(s.substring(3), colour);
            }
        }
    }

    /*public static void iconOneField(String field) {
        int opponentsRow = Integer.parseInt("" + field.charAt(4)) - 1;
        int opponentsColumn = columnToNumber("" + field.charAt(3));
        if (!reverse) {
            guiFields[opponentsColumn][7 - opponentsRow].setIcon(icon);
        } else {
            guiFields[7 - opponentsColumn][opponentsRow].setIcon(icon);
        }
    }*/

    public static void isStalemate() {
        if (everyAvailableMoves.isEmpty() && !chessboard.isWhitesWon() && !chessboard.isBlacksWon()) {
            newGame.setEnabled(true);
            label.setText("Stalemate");
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