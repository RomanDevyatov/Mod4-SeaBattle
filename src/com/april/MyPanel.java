package com.april;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    public Game game;
    private Timer tmDraw;

    @Override
    public void paintComponent(Graphics gr) {
        gr.setFont(new Font("serif", Font.PLAIN, 20));
        gr.setColor(Color.RED);
        gr.drawString("Hello!", 100, 200);

    }
}
