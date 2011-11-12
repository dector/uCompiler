package ua.org.dector.ucompiler.selectionStrategies;

import ua.org.dector.ucompiler.store.Table;
import ua.org.dector.ucompiler.store.TableRow;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dector
 */
public class DirectIdSelectionStrategy implements SelectionStrategy<Long> {
    public Set<TableRow> getRows(Table table, Long id) {
        Map.Entry<String, Double> entry = table.selectRow(id);
        Set<TableRow> resultSet = new HashSet<TableRow>();
        resultSet.add(TableRow.newInstance(id, entry));
        return resultSet;
    }
}
