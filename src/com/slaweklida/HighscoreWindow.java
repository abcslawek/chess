package com.slaweklida;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HighscoreWindow {

    JFrame frame = new JFrame();
    public static JLabel first = new JLabel();
    public static JLabel second = new JLabel();
    public static JLabel third = new JLabel();
    public static JLabel iconLabel = new JLabel();
    public static JTable table;
    public static JScrollPane scrollPane;
    private static JPanel panel = new JPanel();
    private static ImageIcon icon = new ImageIcon("D:\\Java\\Projekty\\Chess\\src\\com\\slaweklida\\crownIcon.png");


    public HighscoreWindow(JDBCDemo jdbcDemo) {
//        String[][] data = {{"101", "Amit", "670000"},
//                {"102", "Jai", "780000"},
//                {"101", "Sachin", "700000xxxxxxxxxxxxxxxxxxxxxxxxxxxx"}};

        String[][] data = jdbcDemo.highscore();
        String[] column = {"POSITION", "NICK", "WINS AGAINST PC"};
        table = new JTable(data, column);
        table.setEnabled(false);

        //USTAWIENIE NAGLOWKA TABELI
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        table.setTableHeader(header);

        //DOSTOSOWANIE SZEROKOSCI KOLUMNY DO TEKSTU
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        //table.getColumnModel().getColumn(2).setPreferredWidth(10);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 480, 500);
        scrollPane.setPreferredSize(table.getPreferredSize());
        frame.add(scrollPane);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(170, 100, 480, 500);


        //PO ZAMKNIĘCIU OKNA PRZYCISK HIGHSCORE AKTYWUJE SIĘ
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
                GUI.highscore.setEnabled(true);
            }
        });
    }


}
