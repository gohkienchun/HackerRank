package projecteuler;

import java.util.Scanner;

/**
 * Two main components:
 * 1. Find out the available digits for the particular column
 * 2. Apply backtracking, starts from smallest available digit,
 * if not valid digit found, backtrack one step and increment the value to next available digit.
 *
 * @see "https://www.hackerrank.com/contests/projecteuler/challenges/euler096/problem"
 */
public class Sudoku {

    private static final int[][] PUZZLE = new int[9][9];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int counter = 0;
        while (counter < 9) {
            String line = scan.next();
            fillPuzzle(line, counter++);
        }
        scan.close();
        solve();
        printSolution();
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

    private static void printSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(PUZZLE[row][col]);
            }
            System.out.println();
        }
    }
}
