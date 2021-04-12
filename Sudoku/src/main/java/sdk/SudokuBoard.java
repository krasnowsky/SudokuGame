package sdk;

import sdk.components.SudokuBox;
import sdk.components.SudokuColumn;
import sdk.components.SudokuField;
import sdk.components.SudokuRow;

public class SudokuBoard {

    //EXERCISE 3
    //actual sudoku board
    private final int[][] board = new int[9][9];

    //solver
    private final SudokuSolver solver;

    //constructor with specified solver
    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    //obtains a value of a board at specific coordinates
    public int get(int x, int y) {
        return board[x][y];
    }

    //sets a values of a board at specific coordinates
    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    //solves sudoku game and actually init it
    public void solveGame() {
        solver.solve(this);
    }

    // EXERCISE 4
    //creation of sudoku fields
    private SudokuField[][] sudokuBoard  = new SudokuField[9][9];

    //checks whole board
    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!(getRow(i).verify() || getColumn(i).verify())) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!getBox(i,j).verify()) {
                    return false;
                }
            }
        }

        return true;
    }

    public SudokuRow getRow(int y) {
        return new SudokuRow(sudokuBoard[y]);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            column[i] = sudokuBoard[i][x];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] box = new SudokuField[9];
        x = (x / 3) * 3;
        y = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(sudokuBoard[y + i], x, box, i, 3);
        }

        return new SudokuBox(box);
    }
}
