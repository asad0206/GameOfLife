import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.*;

public class GameOfLife extends JFrame {
    int size = 50;
    boolean cellsMap[][];
    JButton cells[][];

    // Constructor
    public GameOfLife() {

        Random rnd = new Random();

        cellsMap = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(500, 500);
        setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cellsMap[i][j] = rnd.nextInt(100) < 30;
                JButton temp = new JButton();
                if (cellsMap[i][j]) {
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
    }

    public static void main(String[] args) {
        // Method
        // Todo method declare

        new GameOfLife();
    }
}
