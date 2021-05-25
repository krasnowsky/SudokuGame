package sdk;

import java.io.*;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {
    private String filename;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        writer = new ObjectOutputStream(new FileOutputStream(filename));
        writer.writeObject(obj);
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        reader = new ObjectInputStream(new FileInputStream(filename));
        return (SudokuBoard) reader.readObject();
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
        if (reader != null) {
            reader.close();
        }
    }
}
