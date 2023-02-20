package com.slaweklida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HighscoreWindow {

    JFrame frame = new JFrame();
    public static JLabel first = new JLabel();
    public static JLabel second = new JLabel();
    public static JLabel third = new JLabel();
    public static JLabel iconLabel = new JLabel();
    private static JPanel panel = new JPanel();
    private static ImageIcon icon = new ImageIcon("D:\\Java\\Projekty\\Chess\\src\\com\\slaweklida\\crownIcon.png");


    HighscoreWindow(){
        first.setFont(new Font(null, Font.PLAIN, 25));
        first.setBounds(30, 30,330, 30);

        second.setFont(new Font(null, Font.PLAIN, 25));
        second.setBounds(30, 70,330, 30);

        third.setFont(new Font(null, Font.PLAIN, 25));
        third.setBounds(30, 110,330, 30);


        //WYKONANIE WIELU AKCJI PRZY ZAMKNIĘCIU OKNA
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
                GUI.test.setEnabled(true);
            }
        });

        iconLabel.setIcon(icon);
        iconLabel.setBounds(260,30,100,100);


        frame.add(iconLabel);
        frame.add(first);
        frame.add(second);
        frame.add(third);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(220,130,420,230);
    }


}
