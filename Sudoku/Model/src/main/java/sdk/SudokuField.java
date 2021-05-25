package sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuField implements Observable, Serializable, Comparable<SudokuField>, Cloneable {
    private int value;
    public List<Observer> observers = new ArrayList<>();

    public SudokuField() {
        this.value = 0;
    }

    public int getFieldValue() {
        return this.value;
    }

    //notifyObservers
    public void setFieldValue(int value) {
        this.value = value;
    }

    public void addObservers(Observer... addedObservers) {
        this.observers.addAll(Arrays.asList(addedObservers));
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(observer.verify());
        }
    }

    @Override
    public String toString() {
        return "SudokuField{"
                +
                "value="
                +
                value
                +
                '}';
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int compareTo(SudokuField o) throws NullPointerException {
        return Integer.compare(this.value, o.getFieldValue());
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
