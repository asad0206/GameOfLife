import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class GameOfLife extends JFrame {
    int size = 50;
    boolean cellsMap[][];
    JButton cells[][];

    // ? Constructor
    public GameOfLife() {

        // * Random numbers >_<
        Random rnd = new Random();

        /**
         * * cellsMap - to get all the places of cells on the map
         * * cells - to make changes to the cells
         */
        cellsMap = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(500, 500);
        setLayout(new GridLayout(size, size));

        // ? adding colors to cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellsMap[i][j] = rnd.nextInt(100) < 30;
                JButton temp = new JButton();
                if (cellsMap[i][j]) { // * if the cell is alive
                    temp.setBackground(Color.GREEN);
                } else {
                    temp.setBackground(Color.WHITE);
                }
                add(temp);
                cells[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // * Rules for species
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // * Temporary array to hold the cell values
                boolean[][] temp = new boolean[size][size];

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        int count = countNeighbours(i, j);

                        // ! Rules
                        if (cellsMap[i][j]) { // * if the cell is alive
                            if (count < 2)
                                // * Cell dies
                                temp[i][j] = false;
                            if (count == 3 || count == 2)
                                // ? Cell will be alive
                                temp[i][j] = true;
                            if (count > 3)
                                // * Cell will die
                                temp[i][j] = false;
                        } else {
                            if (count == 3)
                                // ? Becomes alive in the next round
                                temp[i][j] = true;
                        }
                    }
                }

                cellsMap = temp;

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (cellsMap[i][j]) {
                            // ? Random color for the cells
                            cells[i][j].setBackground(
                                    new Color(rnd.nextInt(255),
                                            rnd.nextInt(255),
                                            rnd.nextInt(255),
                                            rnd.nextInt(255)));
                        } else {
                            cells[i][j].setBackground(Color.WHITE);
                        }

                    }
                }
            }
        });

        timer.start();
    }

    int countNeighbours(int x, int y) {
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (cellsMap[i][j]) {
                        count++;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        if (cellsMap[x][y]) {
            count--;
        }

        return count;
    }

    public static void main(String[] args) {
        // Method
        new GameOfLife();
    }
}
