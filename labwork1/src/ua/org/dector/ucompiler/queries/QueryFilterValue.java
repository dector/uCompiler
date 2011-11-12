package ua.org.dector.ucompiler.queries;

/**
 * @author dector
 */
public class QueryFilterValue<T> {
    private T value;

    public QueryFilterValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
