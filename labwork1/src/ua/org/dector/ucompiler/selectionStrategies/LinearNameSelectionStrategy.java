package ua.org.dector.ucompiler.selectionStrategies;

import ua.org.dector.ucompiler.store.Table;
import ua.org.dector.ucompiler.store.TableRow;

import java.util.*;

/**
 * @author dector
 */
public class LinearNameSelectionStrategy implements SelectionStrategy<String> {
    public Set<TableRow> getRows(Table table, String name) {
        TableRow foundRow = null;

        Set<Long> rowId = table.getRowIds();
        Iterator<Long> iterator = rowId.iterator();
        boolean found = false;

        String iterableName;
        long currentRowId;
        while (! found && iterator.hasNext()) {
            currentRowId = iterator.next();
            iterableName = table.selectRow(currentRowId).getKey();

            if (iterableName.equals(name)) {
                Map.Entry<String, Double> entry = table.selectRow(currentRowId);
                foundRow = TableRow.newInstance(currentRowId, entry);
                found = true;           // or may use: foundRow != null
            }
        }

        Set<TableRow> resultSet = new HashSet<TableRow>();
        resultSet.add(foundRow);
        return resultSet;
    }
}
