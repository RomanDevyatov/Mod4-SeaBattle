package com.april;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private int mX, mY;

    public class MyMouse1  implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1 && e.getClickCount() == 1) {
                mX = e.getX();
                mY = e.getY();
                if (mX >= 100 && mY >= 100 && mX < 400 && mY < 400) {
                    int i = (mY - 100) / 30;
                    int j = (mX - 100) / 30;

                    if (game.getCompFieldValueAt(i, j) <= 4) {
                        game.shotPlayer(i, j);
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class MyMouse2 implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mX = e.getX();
            mY = e.getY();
            if (mX >= 100 && mY >= 100 && mX < 400 && mY < 400) {
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public MyPanel() {
        addMouseListener(new MyMouse1());
        addMouseMotionListener(new MyMouse2());
        setFocusable(true);

        this.game = new Game();
        this.game.start();
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

        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 10; p++) {
                if (this.game.getPlayerFieldValueAt(i, p) >= 1 &&  this.game.getPlayerFieldValueAt(i, p) <= 4) {
                        gr.drawImage(paluba, 500 + p * 30, 100 + i * 30, 30, 30, null);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 10; p++) {
                if (game.getCompFieldValueAt(i, p) != 0) {
                    if (game.getCompFieldValueAt(i, p) >= 8 && game.getCompFieldValueAt(i, p) <= 11){
                        gr.drawImage(wounded, 100 + p * 30, 100 + i * 30, 30, 30, null);
                    } else if (game.getCompFieldValueAt(i, p) >= 15){
                        gr.drawImage(killed, 100 + p * 30, 100 + i * 30, 30, 30, null);
                    } else if (game.getCompFieldValueAt(i, p) >= 5){
                        gr.drawImage(bomb, 100 + p * 30, 100 + i * 30, 30, 30, null);
                    }

                }
                if (game.getPlayerFieldValueAt(i, p) != 0) {
                    if (game.getPlayerFieldValueAt(i, p) >= 8 && game.getPlayerFieldValueAt(i, p) <= 11){
                        gr.drawImage(wounded, 500 + p * 30, 100 + i * 30, 30, 30, null);
                    } else if (game.getPlayerFieldValueAt(i, p) >= 15){
                        gr.drawImage(killed, 500 + p * 30, 100 + i * 30, 30, 30, null);
                    } else if (game.getPlayerFieldValueAt(i, p) >= 5){
                        gr.drawImage(bomb, 500 + p * 30, 100 + i * 30, 30, 30, null);
                    }

                }

            }
        }

        gr.setColor(Color.BLUE);
        for (int i = 0; i <= 10; i++) {
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

        if (mX >= 100 && mY >= 100 && mX < 400 && mY < 400) {
            if (game.getEndg() == 0 && !game.getCompTurn()) {
                int i = (mY - 100) / 30;
                int j = (mX - 100) / 30;
                if (game.getCompFieldValueAt(i, j) <= 4) {
                    gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
                }
            }
        }

        if (game.getEndg() == 1) {
            gr.drawImage(end1, 300, 200, 300, 100, null);
        } else if (game.getEndg() == 2) {
            gr.drawImage(end2, 300, 200, 300, 100, null);
        }



    }
}

   }
