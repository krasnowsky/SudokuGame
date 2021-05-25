package sdk;

import java.io.Serializable;
import java.util.List;

public class SudokuBox extends SudokuElement implements Serializable {

    public SudokuBox(List<SudokuField> fields) {
        this.sudokuFields = fields;
    }

    public SudokuBox cloneBox() throws CloneNotSupportedException {
        SudokuBox clone = (SudokuBox) super.clone();
        return clone;
    }
}
