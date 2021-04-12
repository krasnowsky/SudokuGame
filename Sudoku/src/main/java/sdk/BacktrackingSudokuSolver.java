package sdk;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BacktrackingSudokuSolver implements SudokuSolver {

    //array of numbers to be shuffled
    private final int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    //const
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    //shuffling algorithm
    private void shuffleArray(int[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    //checks whether numbers in row do not repeat
    private boolean isInRow(int row, int number, SudokuBoard board) {
        for (int i = 0; i < SIZE; i++) {
            if (board.get(row, i) == number) {
                return true;
            }
        }
        return false;
    }

    //checks whether numbers in column do not repeat
    private boolean isInColumn(int column, int number, SudokuBoard board) {
        for (int i = 0; i < SIZE; i++) {
            if (board.get(i, column) == number) {
                return true;
            }
        }
        return false;
    }

    //checks whether numbers in 3x3 box do not repeat
    private boolean isInBox(int row, int column, int number, SudokuBoard board) {
        int actualRow = row - row % 3;
        int actualColumn = column - column % 3;

        for (int i = actualRow; i < actualRow + 3; i++) {
            for (int j = actualColumn; j < actualColumn + 3; j++) {
                if (board.get(i, j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks whether everything is correct
    private boolean isOkay(int row, int column, int number, SudokuBoard board) {
        return !isInRow(row, number, board)
                && !isInColumn(column, number, board)
                && !isInBox(row, column, number, board);
    }

    //actual solving sudoku algorithm
    @Override
    public boolean solve(SudokuBoard board) { //shuffle local collection
        shuffleArray(numbers);
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board.get(row, column) == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOkay(row, column, numbers[number - 1], board)) {
                            board.set(row, column, numbers[number - 1]);
                            if (solve(board)) {
                                return true;
                            }
                        } else {
                            board.set(row, column, EMPTY);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
