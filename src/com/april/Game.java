package com.april;

public class Game {

    private int[][] masComp;
    private int[][] masPlayer;
    private boolean compTurn;
    private int endg; // 0 - game goes on, 1 - player won, 2 - comp won

    public Game() {
        this.masComp = new int[10][10];
        this.masPlayer = new int[10][10];
    }

    public int[][] getMasComp() {
        return this.masComp;
    }

    public int getPlayerFieldValueAt(int x, int y) {
        return this.masPlayer[y][x];
    }

    public int getCompFieldValueAt(int x, int y) {
        return this.masComp[y][x];
    }

    public int getEndg() {
        return this.endg;
    }

    public boolean getCompTurn() {
        return this.compTurn;
    }

    public void start() {
        for (int i = 0; i < masComp.length; i++) {
            for (int k = 0; k < masPlayer.length; k++) {
                this.masComp[i][k] = 0;
                this.masPlayer[i][k] = 0;
            }
        }
        endg = 0;
        compTurn = false;

        placeSheeps(masPlayer);
        placeSheeps(masComp);
    }

    private void placeSheeps(int[][] masPlayer) {
        makeSheep(masPlayer, 4);
        for (int i = 1; i <= 2; i++) {
            makeSheep(masPlayer, 3);
        }
        for (int i = 1; i <= 3; i++) {
            makeSheep(masPlayer, 2);
        }
        make1(masPlayer);
    }

    private void setMasValue(int[][] mas, int i, int p, int val) {
        if (testMasPoz(i, p)) {
            mas[i][p] = val;
        }
    }

    private void make1(int[][] mas) {
        for (int k = 1; k <= 4; k++) {
            while (true) {
                int i = (int)(Math.random() * 10);
                int j = (int)(Math.random() * 10);

                if (mas[i][j] == 0) {
                    mas[i][j] = 1;
                    okrBegin(mas, i, j, -1);
                    break;
                }
            }
        }
    }

    private void makeSheep(int[][] mas, int kolPalub) {
        while (true) {
            boolean flag = false;

            int i = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);

            int napr = (int) (Math.random() * 4); // 0 - 3
            //0 - up, 1 - right, 2 - down, 3 - left
            if (testNewPaluba(mas, i, j)) {
                if (napr == 0) {
                    if (testNewPaluba(mas, i - (kolPalub - 1), j)) {
                        flag = true;
                    }
                } else if (napr == 1) {
                    if (testNewPaluba(mas, i, j + kolPalub - 1)) {
                        flag = true;
                    }
                } else if (napr == 2) {
                    if (testNewPaluba(mas, i + kolPalub - 1, j)) {
                        flag = true;
                    }
                } else if (napr == 3) {
                    if (testNewPaluba(mas, i, j - (kolPalub - 1))) {
                        flag = true;
                    }
                }

                if (flag) {
                    mas[i][j] = kolPalub;
                    okrBegin(mas, i, j, -2);

                    if (napr == 0) { // up
                        for (int k = kolPalub - 1; k >= 1; k--) {
                            mas[i - k][j] = kolPalub;
                            okrBegin(mas, i - k, j, -2);
                        }
                    } else if (napr == 1) { // right
                        for (int k = kolPalub - 1; k >= 1; k--) {
                            mas[i][j + k] = kolPalub;
                            okrBegin(mas, i, j + k, -2);
                        }
                    } else if (napr == 2) { // down
                        for (int k = kolPalub - 1; k >= 1; k--) {
                            mas[i + k][j] = kolPalub;
                            okrBegin(mas, i + k, j, -2);
                        }
                    } else if (napr == 3) { // left
                        for (int k = kolPalub - 1; k >= 1; k--) {
                            mas[i][j - k] = kolPalub;
                            okrBegin(mas, i, j - k, -2);
                        }
                    }
                    break;
                }
            }
        }
        okrEnd(mas);
    }

    public void shotPlayer(int j, int i) {
        masComp[i][j] += 7;
        testUbit(masComp, i, j);
        testEndGame();
        if (masComp[i][j] < 8) {
            compTurn = true;
            while (compTurn == true) {
                compTurn = shootComp();
            }
        }
    }

    private void testUbit(int[][] mas, int i, int j) {
        switch (mas[i][j]) {
            case 8:
                mas[i][j] += 7;
                okrPodbit(mas, i, j);
                break;
            case 9:
                analyzeUbit(mas, i, j, 2);
                break;
            case 10:
                analyzeUbit(mas, i, j, 3);
                break;
            case 11:
                analyzeUbit(mas, i, j, 4);
                break;
        }
    }

    private void analyzeUbit(int[][] mas, int i, int j, int kolPalub) {
        int kolRanen = 0;
        for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
            for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
                if (testMasPoz(k, g) && mas[k][g] == kolPalub + 7) {
                    kolRanen++;
                }
            }
        }
        if (kolRanen == kolPalub) {
            for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
                for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
                    if (testMasPoz(k, g) && mas[k][g] == kolPalub + 7) {
                        mas[k][g] += 7;
                        okrPodbit(mas, k, g);
                    }
                }
            }
        }
    }

    private void make4(int[][] mas) {
        int i = (int) (Math.random() * 10);
        int j = (int) (Math.random() * 10);
        mas[i][j] = 4;
        okrBegin(mas, i, j, -2);

        int napr = (int) (Math.random() * 4); // 0 - 3
        //0 - up, 1 - right, 2 - down, 3 - left
        switch (napr) {
            case 0:
                if (!testNewPaluba(mas, i - 3, j)) {
                    napr = 2;
                }
                break;
            case 1:
                if (!testNewPaluba(mas, i, j + 3)) {
                    napr = 3;
                }
                break;
            case 2:
                if (!testNewPaluba(mas, i + 3, j)) {
                    napr = 0;
                }
                break;
            case 3:
                if (!testNewPaluba(mas, i, j - 3)) {
                    napr = 1;
                }
                break;
        }

        if (napr == 0) { // up
            mas[i - 3][j] = 4;
            okrBegin(mas, i - 3, j, -2);
            mas[i - 2][j] = 4;
            okrBegin(mas, i - 2, j, -2);
            mas[i - 1][j] = 4;
            okrBegin(mas, i - 1, j, -2);
        } else if (napr == 1) { // right
            mas[i][j + 3] = 4;
            okrBegin(mas, i, j + 3, -2);
            mas[i][j + 2] = 4;
            okrBegin(mas, i, j + 2, -2);
            mas[i][j + 1] = 4;
            okrBegin(mas, i, j + 1, -2);
        } else if (napr == 2) { // down
            mas[i + 3][j] = 4;
            okrBegin(mas, i + 3, j, -2);
            mas[i + 2][j] = 4;
            okrBegin(mas, i + 2, j, -2);
            mas[i + 1][j] = 4;
            okrBegin(mas, i + 1, j, -2);
        } else if (napr == 3) { // left
            mas[i][j - 3] = 4;
            okrBegin(mas, i, j - 3, -2);
            mas[i][j - 2] = 4;
            okrBegin(mas, i, j - 2, -2);
            mas[i][j - 1] = 4;
            okrBegin(mas, i, j - 1, -2);
        }

        okrEnd(mas);

    }

    private void okrBegin(int[][] mas, int i, int j, int val) {
        setOkr(mas, i - 1, j - 1, val); // top left
        setOkr(mas, i - 1, j, val); // top
        setOkr(mas, i - 1, j + 1, val); // top right
        setOkr(mas, i, j + 1, val); // right
        setOkr(mas, i + 1, j + 1, val); // down right
        setOkr(mas, i + 1, j, val); // bottom
        setOkr(mas, i + 1, j - 1, val); // down left
        setOkr(mas, i, j - 1, val); // left
    }

    private boolean testNewPaluba(int[][] mas, int i, int j) {
        if (!testMasPoz(i, j)) { // testMasPoz(i, j) == false
            return false;
        }
        if (mas[i][j] == 0 || mas[i][j] == -2) {
            return true;
        }
        return false;
    }

    private void okrEnd(int[][] mas) {
        for (int i = 0; i < 10; i++) {
            for (int p = 0; p < 10; p++) {
                if (mas[i][p] == -2) {
                    mas[i][p] = -1;
                }
            }
        }
    }

    private void setOkr(int[][] mas, int i, int p, int val) {
        if (testMasPoz(i, p) && mas[i][p] == 0) {
            setMasValue(mas, i, p, val);
        }
    }

    private void setOkrPodbit(int[][] mas, int i, int j) {
        if (testMasPoz(i, j) == true){
            if (mas[i][j] == -1 || mas[i][j] == 6) {
                mas[i][j]--;
            }
        }
    }

    private void okrPodbit(int[][] mas, int i, int j) {
        for (int k = i - 1; k < i + 2; k++) {
            for (int s = j - 1; s < j + 2; s++) {
                if (k != s) {
                    setOkrPodbit(mas, k, s);
                }
            }
        }
    }

    private boolean shootComp() {
        boolean res = false;
        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (masPlayer[i][j] >= 9 && masPlayer[i][j] <= 11) {
                    flag = true;
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int s = j - 1; s < j + 2; s++) {
                            if (Math.abs(k - s) == 1 && testMasPoz(k, s) && masPlayer[k][s] <= 4 && masPlayer[k][s] != -2) {
                                masPlayer[k][s] += 7;
                                testUbit(masPlayer, k, s);
                                if (masPlayer[k][s] >= 8) {
                                    res = true;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (!flag) {
            for (int k = 1; k <= 100; k++) {
                int i = (int) (Math.random() * 10);
                int j = (int) (Math.random() * 10);
                if (masPlayer[i][j] <= 4 && masPlayer[i][j] != -2) {
                    masPlayer[i][j] += 7;
                    testUbit(masPlayer, i, j);
                    if (masPlayer[i][j] >= 8) {
                        res = true;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (masPlayer[i][j] <= 4 && masPlayer[i][j] != -2) {
                            masPlayer[i][j] += 7;
                            testUbit(masPlayer, i, j);
                            if (masPlayer[i][j] >= 8) {
                                res = true;
                            }
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        testEndGame();
        return res;
    }

    private boolean testMasPoz(int i, int p) {
        if (i >= 0 && i <= 9 && p >= 0 && p <= 9) {
            return true;
        } else {
            return false;
        }
    }

    private int[] makeShot() {
        int i = (int) (Math.random() * 10);
        int j = (int) (Math.random() * 10);
        int[] mas = new int[2];
        mas[0] = i;
        mas[1] = j;

        return mas;
    }

    private void testEndGame(){
        int testNumber = 15 * 1 * 4 + 16 * 2 * 3 + 17 * 3 * 2 + 18 * 4 * 1; // ==330
        int kolComp =0, kolPlayer = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (masPlayer[i][j] >= 15) {
                    kolPlayer += masPlayer[i][j];
                }
                if (masComp[i][j] >= 15) {
                    kolComp += masComp[i][j];
                }
            }
        }
        if (kolPlayer == testNumber) {
            endg = 2;
        } else if (kolComp == testNumber) {
            endg = 1;
        }
    }

    private int getNapr() {
        return (int) (Math.random() * 4);
    }
}
