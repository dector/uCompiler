package ua.org.dector.ucompiler.selectionStrategies;

import ua.org.dector.ucompiler.store.Table;
import ua.org.dector.ucompiler.store.TableRow;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dector
 */
public class BinaryNameSelectionStrategy implements SelectionStrategy<String> {
    public Set<TableRow> getRows(Table table, String selectedName) {
        long leftBorder = 0;
        long rightBorder = table.countRows();
        long prevId = 0;
        long viewedId = 0;

        boolean found = false;

        String viewedName;
        int comparationResult;
        do {
            prevId = viewedId;
            viewedId = (leftBorder + rightBorder) / 2;
            viewedName = table.selectRow(viewedId).getKey();

            comparationResult = viewedName.compareTo(selectedName);

            if (comparationResult == 0) {
                found = true;
            } else {
                if (comparationResult == -1) {
                    leftBorder = viewedId;
                } else {
                    rightBorder = viewedId;
                }
            }
        } while (! found || prevId == viewedId);

        Map.Entry<String, Double> entry = null;
        if (viewedName.compareTo(selectedName) == 0) {
            entry = table.selectRow(viewedId);
        }

        Set<TableRow> resultSet = new HashSet<TableRow>();
        resultSet.add(TableRow.newInstance(viewedId, entry));
        return resultSet;
    }
}
