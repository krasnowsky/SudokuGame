package sdk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class SudokuElement implements Observer, Cloneable {
    protected List<SudokuField> sudokuFields;

    public SudokuElement() {
        sudokuFields = Arrays.asList(new SudokuField[9]);
    }

    public boolean verify() {
        Set<Integer> foundNumbers = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int potentialValue = sudokuFields.get(i).getFieldValue();
            if (foundNumbers.contains(potentialValue)) {
                return false;
            }
            foundNumbers.add(potentialValue);
        }
        return true;
    }

    @Override
    public void update(boolean verify) {
        /*if (verify) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }*/
    }

    //method for getting hashcode
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "SudokuElement{"
                +
                "sudokuFields="
                +
                sudokuFields
                +
                '}';
    }

    //method for comparing two objects
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuElement that = (SudokuElement) o;

        return new EqualsBuilder().append(sudokuFields, that.sudokuFields).isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
