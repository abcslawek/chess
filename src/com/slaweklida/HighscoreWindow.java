package com.slaweklida;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HighscoreWindow {

    JFrame frame = new JFrame();
    public static JTable table;
    public static JScrollPane scrollPane;
    public static JLabel noConnection;

    public HighscoreWindow(JDBCDemo jdbcDemo) {
        try {
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

            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 0, 480, 500);
            scrollPane.setPreferredSize(table.getPreferredSize());
            frame.add(scrollPane);
        }catch(Exception e){
            setNoConnectionText();
        }

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

    public void setNoConnectionText(){
        //LABEL NOCONNECTION
        noConnection = new JLabel();
        noConnection.setBounds(160,20,200,30);
        noConnection.setText("NO INTERNET CONNECTION");
        frame.add(noConnection);
    }
}
