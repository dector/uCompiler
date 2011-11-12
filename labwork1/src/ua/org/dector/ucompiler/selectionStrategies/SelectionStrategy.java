package ua.org.dector.ucompiler.selectionStrategies;

import ua.org.dector.ucompiler.store.TableRow;
import ua.org.dector.ucompiler.store.Table;

import java.util.Set;

/**
 * @author dector
 */
public interface SelectionStrategy<T> {
    public Set<TableRow> getRows(Table table, T key);
}
