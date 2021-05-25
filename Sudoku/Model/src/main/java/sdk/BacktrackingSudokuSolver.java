package sdk;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    //constants
    private static final int EMPTY = 0;
    private static final int SIZE = 9;


    //actual solving sudoku algorithm
    @Override
    public boolean solve(SudokuBoard board) {
        int row;
        int column = 0;
        boolean isEmpty = false;
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> listRow = Arrays.asList(intArray);
        Collections.shuffle(listRow);

        for (row = 1; row < SIZE; row++) {
            for (column = 0; column < SIZE; column++) {
                if (board.getCellValue(row * SIZE + column) == EMPTY) {
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }

        if (!isEmpty) {
            return true;
        }

        for (int o = 0;o < 9;o++) {
            int number = intArray[o];
            if (board.isInRow(number, row)
                    && board.isInColumn(number, column)
                    && board.isInSquare(number, row, column)) {
                board.setCellValue(row * 9 + column, number);

                if (solve(board)) {
                    return true;
                }
                board.setCellValue(row * 9 + column, 0);
            }
        }
        return false;
    }
}
