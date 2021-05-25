package sdk;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// TODO:
// - separate initializing and solving into two different methods
// - get ride of board of ints and change it with board of fields
// - check whether getRow, getBox, getColumn works as supposed

public class SudokuBoard implements Serializable, Cloneable {
    //solver
    private final SudokuSolver sudokuSolver;

    //actual sudoku board
    private final List<SudokuField> sudokuBoard;

    //constructor with specified solver
    public SudokuBoard(SudokuSolver solver) {
        sudokuBoard = Arrays.asList(new SudokuField[81]);
        this.sudokuSolver = solver;
    }

    //solves sudoku game and actually init it and init function should be in different method
    public void solveGame() {
        makeBoard();
        sudokuSolver.solve(this);
    }

    public void display() {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[9];
            for (int j = 0; j < 9; j++) {
                row[j] = getCellValue(i * 9 + j);
            }
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\n");
    }

    public void makeNewBoard(List<Integer> intList) {
        createFields();
        cellsToZero();

        for (int i = 0; i < 81; i++) {
            setCellValue(i, intList.get(i));
        }
    }

    private void cellsToZero() {
        for (int x = 0; x < 81; x++) {
            setCellValue(x, 0);
        }
    }

    public int getCellValue(int cell) {
        return sudokuBoard.get(cell).getFieldValue();
    }

    public SudokuRow getRow(int rowNumber) {
        List<SudokuField> row;
        row = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            row.set(i, sudokuBoard.get(rowNumber * 9 + i));

        }
        return new SudokuRow(row);
    }

    public SudokuColumn getColumn(int columnNumber) {
        List<SudokuField> column;
        column = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            column.set(i, sudokuBoard.get(columnNumber + 9 * i));
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int boxNumber) {
        List<SudokuField> box;
        box = Arrays.asList(new SudokuField[9]);
        int rowFactor = boxNumber % 3;
        int colFactor = boxNumber / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box.set(3 * i + j, sudokuBoard.get(9 * i + (j + rowFactor * 3) + 27 * colFactor));
            }
        }
        return new SudokuBox(box);
    }

    public void setCellValue(int cell, int value) {
        sudokuBoard.get(cell).setFieldValue(value);
        int row = cell / 9;
        int column = cell % 9;
        int rowFactor = row / 3;
        int colFactor = column / 3;
        sudokuBoard.get(cell).addObservers(getRow(row),
        getColumn(column), getBox(rowFactor * 3 + colFactor));
        sudokuBoard.get(cell).notifyObservers();
        sudokuBoard.get(cell).setFieldValue(value);
    }

    public boolean isInRow(int number, int row) {
        for (int i = 0; i < 9; i++) {
            if (number == getCellValue(row * 9 + i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isInColumn(int number, int column) {
        for (int i = 0; i < 9; i++) {
            if (number == getCellValue(column + 9 * i)) {
                return false;
            }
        }
        return true;
    }

    public void setBoardForGame(int hints) {
        createPuzzle(randomizeHints(hints));
    }

    public boolean isInSquare(int number, int row, int column) {
        int rowFactor = row / 3 * 3;//we get first row from that square
        int colFactor = column / 3 * 3;//we get first column from that square
        for (int i = rowFactor; i < rowFactor + 3; i++) {
            for (int j = colFactor; j < colFactor + 3; j++) {
                if (number == getCellValue(i * 9 + j)) {
                    return false;
                }
            }
        }
        return true;
    }

    //checks whole board
    public boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!(getColumn(i).verify() && getRow(i).verify()
                    && getBox(i).verify())) {
                return false;
            }
        }
        return true;
    }

    private void makeBoard() {
        initFields();
        Integer[] intArray = {1,2,3,4,5,6,7,8,9};
        List<Integer> firstRow = Arrays.asList(intArray);
        Collections.shuffle(firstRow);

        for (int i = 0; i < 9; i++) {
            setCellValue(i, intArray[i]);
        }
    }

    private void initFields() {
        for (int x = 0; x < 81; x++) {
            sudokuBoard.set(x, new SudokuField());
        }
        for (int i = 0; i < 81; i++) {
            setCellValue(i, 0);
        }
    }

    private void createPuzzle(List<Integer> cells) {
        for (Integer cell : cells) {
            setCellValue(cell, 0);
        }
    }

    private List<Integer> randomizeHints(int hints) {
        List<Integer> array;
        array = Arrays.asList(new Integer[hints]);
        for (int x = 0; x < hints; x++) {
            array.set(x, -1);
        }
        Random dice = new Random();
        for (int j = 0; j < hints; j++) {
            int number = dice.nextInt(81);
            boolean isUnique = true;
            for (int x : array) {
                if (x == number) {
                    isUnique = false;
                    j--;
                }
            }
            if (isUnique) {
                array.set(j, number);
            }
        }
        Collections.sort(array);
        return array;
    }

    /*public void display() {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[9];
            for (int j = 0; j < 9; j++) {
                row[j] = getCellValue(i * 9 + j);
            }
            System.out.println(Arrays.toString(row));
        }
    }*/

    @Override
    public String toString() {
        return "SudokuBoard{"
                +
                "sudokuSolver="
                +
                sudokuSolver
                +
                ", sudokuBoard="
                +
                sudokuBoard
                +
                '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(sudokuSolver).append(sudokuBoard).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(sudokuSolver, that.sudokuSolver)
                .append(sudokuBoard, that.sudokuBoard)
                .isEquals();
    }

    private void createFields() {
        for (int x = 0; x < 81; x++) {
            sudokuBoard.set(x, new SudokuField());
        }
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard clonedBoard = new SudokuBoard(this.sudokuSolver);
        clonedBoard.createFields();
        for (int z = 0; z < 81; z++) {
            clonedBoard.setCellValue(z, this.getCellValue(z));
        }
        return clonedBoard;
    }
}
