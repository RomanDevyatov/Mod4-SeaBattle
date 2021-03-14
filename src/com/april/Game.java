package com.april;

public class Game {

    private int[][] masComp;
    private int[][] masPlayer;

    public Game() {
        this.masComp = new int[10][10];
        this.masPlayer = new int[10][10];
    }

    private int[] makeShot() {
        int i = (int) (Math.random() * 10);
        int j = (int) (Math.random() * 10);
        int[] mas = new int[2];
        mas[0] = i;
        mas[1] = j;
        return mas;
    }

    private int getNapr() {
        return (int) (Math.random() * 4);
    }

    public void start() {
        for (int i = 0; i < masComp.length; i++) {
            for (int k = 0; k < masPlayer.length; k++) {
                masComp[i][k] = 0;
                masPlayer[i][k] = 0;
            }
        }
    }
}
