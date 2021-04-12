package sdk.components;

public abstract class SudokuElement {
    protected final SudokuField[] sudokuFields;

    public SudokuElement(SudokuField[] fields) {
        this.sudokuFields = fields;
    }

    public boolean verify() {
        int[] eachNumberInTheBoardCounter = new int[9];

        for (SudokuField field : sudokuFields) {
            if (++eachNumberInTheBoardCounter[field.getFieldValue()] >  1) {
                return false;
            }
        }
        return true;
    }
}
