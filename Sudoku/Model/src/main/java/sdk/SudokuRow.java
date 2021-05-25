package sdk;

import java.io.Serializable;
import java.util.List;

public class SudokuRow extends SudokuElement implements Serializable {

    public SudokuRow(List<SudokuField> fields) {
        this.sudokuFields = fields;
    }

    public SudokuRow cloneRow() throws CloneNotSupportedException {
        SudokuRow clone = (SudokuRow) super.clone();
        return clone;
    }
}
