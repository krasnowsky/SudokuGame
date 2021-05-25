package sdk;

import java.io.Serializable;
import java.util.List;

public class SudokuColumn extends SudokuElement implements Serializable {

    public SudokuColumn(List<SudokuField> fields) {
        this.sudokuFields = fields;
    }

    public SudokuColumn cloneColumn() throws CloneNotSupportedException {
        SudokuColumn clone = (SudokuColumn) super.clone();
        return clone;
    }
}
