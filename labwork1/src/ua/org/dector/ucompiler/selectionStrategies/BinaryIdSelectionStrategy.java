package ua.org.dector.ucompiler.selectionStrategies;

import ua.org.dector.ucompiler.store.Table;
import ua.org.dector.ucompiler.store.TableRow;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dector
 */
public class BinaryIdSelectionStrategy implements SelectionStrategy<Long> {
    public Set<TableRow> getRows(Table table, Long selectedId) {
        long leftBorder = 0;
        long rightBorder = table.countRows();
        long prevId = 0;
        long viewedId = 0;

        boolean found = false;

        do {
            prevId = viewedId;
            viewedId = (leftBorder + rightBorder) / 2;
            if (viewedId == selectedId) {
                found = true;
            } else {
                if (viewedId < selectedId) {
                    leftBorder = viewedId;
                } else {
                    rightBorder = viewedId;
                }
            }
        } while (! found || prevId == viewedId);

        Map.Entry<String, Double> entry = null;
        if (viewedId == selectedId) {
            entry = table.selectRow(viewedId);
        }

        Set<TableRow> resultSet = new HashSet<TableRow>();
        resultSet.add(TableRow.newInstance(viewedId, entry));
        return resultSet;
    }
}
