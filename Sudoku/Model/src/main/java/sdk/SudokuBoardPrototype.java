package sdk;

public class SudokuBoardPrototype {
    public SudokuBoard templateBoard;

    public SudokuBoardPrototype(SudokuBoard board) {
        templateBoard = board;
    }

    public SudokuBoard createClone() throws CloneNotSupportedException {
        return templateBoard.clone();
    }
}
