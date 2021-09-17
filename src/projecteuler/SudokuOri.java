package projecteuler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @see "https://projecteuler.net/problem=96"
 */
public class SudokuOri {

    private static final int[][] PUZZLE = new int[9][9];

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String line;
        String gridName = null;
        int counter = 0;
        int sum = 0;

        String inputFile = "resources/sudoku/unsolved.txt";
        String outputFile = "resources/sudoku/solved.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("Grid")) {
                    gridName = line;
                    continue;
                }

                fillPuzzle(line, counter++);

                if (counter == 9) {
                    solve();
                    printSolution(bw, gridName);
                    counter = 0;
                    sum += Integer.parseInt("" + PUZZLE[0][0] + PUZZLE[0][1] + PUZZLE[0][2]);
                }
            }
        }
        long end = System.currentTimeMillis() - start;

        System.out.println("Sum: " + sum);
        System.out.println("Elapsed Time: " + end + " ms");
    }

    private static void fillPuzzle(String line, int counter) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < 9; i++) {
            PUZZLE[counter][i] = chars[i] - '0';
        }
    }

    /**
     * Brute force backtracking
     *
     * @return
     */
    private static boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (PUZZLE[row][col] != 0) {
                    continue;
                }

                boolean[] available = findAvailable(row, col);

                for (int i = 1; i <= 9; i++) {
                    if (available[i]) {
                        PUZZLE[row][col] = i;
                        if (solve()) {
                            return true;
                        }
                    }
                }

                PUZZLE[row][col] = 0;
                return false;
            }
        }
        return true;
    }

    /**
     * 1. Check the same column.
     * 2. Check the same row.
     * 3. Check respective 3 x 3 box.
     *
     * @param rowIndex current row index of PUZZLE
     * @param colIndex current column index of PUZZLE
     * @return available digits for the blank space
     */
    private static boolean[] findAvailable(int rowIndex, int colIndex) {
        // represents the 1-9 digits, since array index starts from 0, so initialize size of 10
        boolean[] available = {false, true, true, true, true, true, true, true, true, true};
        for (int i = 0; i < 9; i++) {
            if (PUZZLE[rowIndex][i] != 0) {
                available[PUZZLE[rowIndex][i]] = false;
            }
            if (PUZZLE[i][colIndex] != 0) {
                available[PUZZLE[i][colIndex]] = false;
            }
        }

        int boxRow = findBox(rowIndex);
        int boxCol = findBox(colIndex);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (PUZZLE[row + boxRow][col + boxCol] != 0) {
                    available[PUZZLE[row + boxRow][col + boxCol]] = false;
                }
            }
        }

        return available;
    }

    private static int findBox(int index) {
        return index / 3 * 3;
    }

    /**
     * Write solution to a text file
     *
     * @param bw
     * @param gridName
     * @throws IOException
     */
    private static void printSolution(BufferedWriter bw, String gridName) throws IOException {
        bw.write(gridName);
        bw.newLine();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                bw.write("" + PUZZLE[row][col]);
            }
            bw.newLine();
        }
    }
}
