package com.april;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        MyPanel pan = new MyPanel();
        Container cont = getContentPane();
        cont.add(pan);

        setTitle("Игра \"Морской бой\"");
        setBounds(0, 0, 1800, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}
