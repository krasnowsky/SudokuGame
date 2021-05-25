package sdk;

public class SudokuBoardDaoFactory {
    public Dao getFileDao(String filename) {
        FileSudokuBoardDao dao = new FileSudokuBoardDao(filename);
        return dao;
    }
}
