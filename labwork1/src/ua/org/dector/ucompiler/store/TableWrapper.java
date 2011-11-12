package ua.org.dector.ucompiler.store;

import java.util.*;

/**
 * Use TableWrapper to manage your own table structure: <br /><br />
 *
 * +---------+-----------+----------+<br />
 * |    ID   |    NAME   |  VALUE   |<br />
 * | (long)  | (String)  | (double) |<br />
 * +---------+-----------+----------+<br />
 * | Primary | Secondary |          |<br />
 * |   key   |   key     |          |<br />
 * +---------+-----------+----------+<br />
 * |   ...   |    ...    |    ...   |<br />
 *
 * @author dector
 */
public class TableWrapper {
    private Table table;

    /**
     * Create new instance
     */
    public TableWrapper() {
        table = new Table();
    }

    public boolean insert(long id, String name, double value) {
        return table.insertRow(id, name, value);
    }

    public boolean delete(long id) {
        return table.deleteRow(id);
    }

    public TableRow select(long id) {
        Map.Entry<String, Double> entry = table.selectRow(id);

        if (entry != null) {
            return TableRow.newInstance(id, entry);
        } else {
            return null;
        }
    }

    public List<TableRow> select(String name, boolean completeAssertion) {
        List<TableRow> rowList = new ArrayList<TableRow>();

        String seekName = new String(name);

        while (seekName.length() != 0 && rowList.size() == 0) {
            for (long id : getIds(seekName, completeAssertion)) {
                rowList.add(TableRow.newInstance(id, table.selectRow(id)));
            }

            seekName = seekName.substring(0, seekName.length()-1);
        }

        return rowList;
    }

    private Set<Long> getIds() {
        return table.getRowIds();
    }

    private List<Long> getIds(String name, boolean completeAssertion) {
        List<Long> idList = new ArrayList<Long>();

        String rowName;
        for (long id : getIds()) {
            rowName = table.selectRow(id).getKey();

            if (completeAssertion) {
                if (rowName.equals(name)) {
                    idList.add(id);
                }
            } else {
                if (rowName.contains(name)) {
                    idList.add(id);
                }
            }
        }

        return idList;
    }

    public boolean update(long id, long newId, String newName, double newValue) {
        return table.updateRow(id, newId, newName, newValue);
    }

    public boolean update(long id, String newName, double newValue) {
        return table.updateRow(id, newName, newValue);
    }

    public boolean update(long id, String newName) {
        return table.updateRow(id, newName);
    }

    public boolean update(long id, double newValue) {
        return table.updateRow(id, newValue);
    }

//    public TableRow select(long id) throws RowsNotFoundError {
//        return select(new DirectIdSelectionStrategy(), id);
//    }
//
//    public Set<TableRow> select(String name) throws RowsNotFoundError {
//        return select(new BinaryNameSelectionStrategy(), name);
//    }
//
//    private TableRow select(SelectionStrategy<Long> ss, long id) throws RowsNotFoundError {
//        Set<TableRow> rowSet = ss.getRows(table, id);
//
//        if (rowSet.isEmpty()) {
//            throw new RowsNotFoundError(id);
//        } else {
//            return rowSet.iterator().next();
//        }
//    }
//
//    private Set<TableRow> select(SelectionStrategy<String> ss, String name) throws RowsNotFoundError {
//        return ss.getRows(table, name);
//    }
//
//    public boolean insert(long id, String name, double value) {
//        return table.insertRow(id, name, value);
//    }
//
//    public boolean delete(long id) {
//        return table.deleteRow(id);
//    }
//
//    public long delete(String name) {
//
//    }
//
//    /**
//     * Get row by id
//     *
//     * @param id
//     * @return
//     */
//    public TableRow getRow(long id) {
//        return new DirectIdSelectionStrategy().getRows(table, id);
////        Map.Entry<String, Double> entry = table.selectRow(id);
////        return new TableRow(new HashMap.SimpleEntry(id, entry));
//    }
//
//    public TableRow getRowBin(long id) {
//        return new BinaryIdSelectionStrategy().getRows(table, id);
//    }
//
//    public TableRow getRow(String name) {
//        return new LinearNameSelectionStrategy().getRows(table, name);
////        TableRow foundRow = null;
////
////        Set<Long> rowId = table.getRowIds();
////        Iterator<Long> iterator = rowId.iterator();
////        boolean found = false;
////
////        String iterableName;
////        long currentRowId;
////        while (! found && iterator.hasNext()) {
////            currentRowId = iterator.next();
////            iterableName = table.selectRow(currentRowId).getKey();
////
////            if (iterableName.equals(name)) {
////                Map.Entry<String, Double> entry = table.selectRow(currentRowId);
////                foundRow = new TableRow(new HashMap.SimpleEntry(currentRowId, entry));
////                found = true;           // or may use: foundRow != null
////            }
////        }
////
////        return foundRow;
//    }
//
//    public TableRow getRowBin(String name) {
//        return new BinaryNameSelectionStrategy().getRows(table, name);
//    }
//
//    public List<TableRow> getRows() {
//        List<TableRow> rowsList = new ArrayList<TableRow>();
//
//        Map.Entry<String, Double> entry;
//        TableRow tableRow;
//        for (long rowId : table.getRowIds()) {
//            tableRow = getRow(rowId);
////            entry = table.selectRow(rowId);
////            tableRow = new TableRow(new HashMap.SimpleEntry(rowId, entry));
//            rowsList.add(tableRow);
//        }
//
//        return rowsList;
//    }
//
//    public void sort() {
//        sort(0, table.countRows()-1);
//    }
//
//    private void sort(long first, long last) {
//        long i = first;
//        long j = last;
//        long base = getRow((first + last)/2).getId();
//
//        do {
//            while (getRow(i).getId() < base) {
//                i++;
//            }
//            while (base < getRow(j).getId()) {
//                j--;
//            }
//
//            if (getRow(j).getId() <= getRow(i).getId()) {
//                swap(i, j);
//                i++;
//                j--;
//            }
//        } while (i <= j);
//
//        if (first < j) {
//            sort(first, j);
//        }
//        if (i < last) {
//            sort(i, last);
//        }
//
//    }
//
//    public void swap(long idOne, long idTwo) {
//        Map.Entry<String, Double> entry;
//
//        entry = table.selectRow(idOne);
//        String nameOne = entry.getKey();
//        double valueOne = entry.getValue();
//
//        entry = table.selectRow(idTwo);
//        String nameTwo = entry.getKey();
//        double valueTwo = entry.getValue();
//
//        table.updateRow(idOne, idTwo, nameTwo, valueTwo);
//        table.updateRow(idTwo, idOne, nameOne, valueOne);
//    }
//
//    public void clear() {
//        table.reinitRows();
//    }

//    public TableRow getRows(String name) {
//
//    }
}
