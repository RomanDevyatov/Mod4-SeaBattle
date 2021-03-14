package com.april;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyPanel extends JPanel {
    public Game game;
    private Timer tmDraw;
    private Image
                fon,
                paluba,
                killed,
                wounded,
                end1,
                end2,
                bomb;
    private JButton newGameBtn, endGameBtn;

    public MyPanel() {
        this.game = new Game();
        try {
            this.fon = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\fon.jpg"));
            this.paluba = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\paluba.png"));
            this.killed = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\ubit.png"));
            this.wounded = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\ranen.png"));
            this.end1 = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\end1.png"));
            this.end2 = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\end2.png"));
            this.bomb = ImageIO.read(new File("C:\\Users\\r.devyatov\\IDEAprojects\\april\\SeaBattle\\img\\bomba.png"));
        } catch (Exception e) {
            System.out.println("Файл не прочитался");
            e.printStackTrace();
        }

        tmDraw = new Timer(50, new ActionListener( ) {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        tmDraw.start();

        newGameBtn = new JButton();
        newGameBtn.setText("Новая игра");
        newGameBtn.setForeground(Color.BLUE);
        newGameBtn.setFont(new Font("serif", 0, 20));
        newGameBtn.setBounds(130, 450, 200, 80);
        newGameBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                game.start();
            }
        });
        add(newGameBtn);

        endGameBtn = new JButton();
        endGameBtn.setText("Выход");
        endGameBtn.setForeground(Color.BLUE);
        endGameBtn.setFont(new Font("serif", 0, 20));
        endGameBtn.setBounds(530, 450, 200, 80);
        endGameBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        add(endGameBtn);

    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        gr.drawImage(fon, 0,0,900,600, null);

        gr.setFont(new Font("serif", Font.PLAIN, 40));
        gr.setColor(Color.GRAY);

        gr.drawString("Computer", 150, 50);
        gr.drawString("Player", 590, 50);

        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game.getFieldValueAt(i, j) >= 1 &&  game.getFieldValueAt(i, j) <= 4) {
                    gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30 ,null);
                }
            }

            gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
            gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);

            gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
            gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
        }

        gr.setFont(new Font("serif", 0, 20));
        gr.setColor(Color.RED);

        for (int i = 1; i <= 10; i++) {
            gr.drawString(String.valueOf(i), 73, 93 + i * 30);
            gr.drawString(String.valueOf(i), 473, 93 + i * 30);

            gr.drawString(String.valueOf((char)('A' + i - 1)), 78 + i * 30, 93);
            gr.drawString(String.valueOf((char)('A' + i - 1)), 478 + i * 30, 93);
        }



        //gr.drawString("Hello!", 100, 200);

    }
}
