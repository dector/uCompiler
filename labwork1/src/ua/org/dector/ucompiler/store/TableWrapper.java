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

    /**
     * Insert new record to table
     *
     * @param id    record's ID value
     * @param name  NAME value
     * @param value VALUE value
     * @return  <b>true</b> if record was saved, <b>false</b> else
     */
    public boolean insert(long id, String name, double value) {
        return table.insertRow(id, name, value);
    }

    /**
     * Delete record by id from table
     *
     * @param id record's ID to delete
     * @return <b>true</b> if record was deleted, <b>false</b> else
     */
    public boolean delete(long id) {
        return table.deleteRow(id);
    }

    /**
     * Select record from table by id
     *
     * @param id record's ID to select
     * @return  existing table row, <b>null</b> if not exists
     */
    public TableRow select(long id) {
        Map.Entry<String, Double> entry = table.selectRow(id);

        if (entry != null) {
            return TableRow.newInstance(id, entry);
        } else {
            return null;
        }
    }

    /**
     * Select record from table by name
     *
     * @param name  record's NAME search value
     * @param completeAssertion set <b>true</b> to search for records, which contains full word
     * @return list of finded records
     */
    public List<TableRow> select(String name, boolean completeAssertion) {
        List<TableRow> rowList = new ArrayList<TableRow>();

        while (name.length() != 0 && rowList.size() == 0) {
            for (long id : getIds(name, completeAssertion)) {
                rowList.add(TableRow.newInstance(id, table.selectRow(id)));
            }

            name = name.substring(0, name.length()-1);
        }

        return rowList;
    }

    /**
     * Returns set of all ID values, which are stored in table
     *
     * @return all ID values set
     */
    private Set<Long> getIds() {
        return table.getRowIds();
    }

    /**
     * Returns all records ID values from table finded name
     *
     * @param name record's NAME search value
     * @param completeAssertion set <b>true</b> to search for records, which contains full word
     * @return ID values of finded records
     */
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

    /**
     * Update record's data
     *
     * @param id        record's ID
     * @param newId     new ID value for this record
     * @param newName   new NAME value for this record
     * @param newValue  new VALUE value for this record
     * @return <b>true</b> if update was saved, else <b>false</b>
     */
    public boolean update(long id, long newId, String newName, double newValue) {
        return table.updateRow(id, newId, newName, newValue);
    }

    /**
     * Update record's data
     *
     * @param id        record's ID
     * @param newName   new NAME value for this record
     * @param newValue  new VALUE value for this record
     * @return <b>true</b> if update was saved, else <b>false</b>
     */
    public boolean update(long id, String newName, double newValue) {
        return table.updateRow(id, newName, newValue);
    }

    /**
     * Update record's data
     *
     * @param id        record's ID
     * @param newName   new NAME value for this record
     * @return <b>true</b> if update was saved, else <b>false</b>
     */
    public boolean update(long id, String newName) {
        return table.updateRow(id, newName);
    }

    /**
     * Update record's data
     *
     * @param id        record's ID
     * @param newValue  new VALUE value for this record
     * @return <b>true</b> if update was saved, else <b>false</b>
     */
    public boolean update(long id, double newValue) {
        return table.updateRow(id, newValue);
    }

    /**
     * Clear all table records
     */
    public void clear() {
        table.reinitRows();
    }

//    /**
//     * Sort table by ID value
//     *
//     * @param asc <b>true</b> for ascend sorting, <b>false</b> for descend sorting
//     */
//    public void sort(boolean asc) {
//        if (asc) {
//            sortAsc();
//        } else {
//            sortDesc();
//        }
//    }
//
//    /**
//     * Sort table by ID value (ascending)
//     */
//    public void sortAsc() {
//        sortAsc(0, table.countRows()-1);
//    }
//
//    /**
//     * Sort table by ID value (descending)
//     */
//    public void sortDesc() {
//        sortDesc(0, table.countRows()-1);
//    }
//
//    /**
//     * Sort table by ID value (ascending)
//     *
//     * @param first first
//     * @param last
//     */
//    private void sortAsc(long first, long last) {
//        long i = first;
//        long j = last;
//        long base = (first + last)/2;
//
//        do {
//            while (i < base) {
//                i++;
//            }
//            while (base < j) {
//                j--;
//            }
//
//            if (j <= i) {
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
//    /**
//     * @param idOne
//     * @param idTwo
//     * @return
//     */
//    public boolean swap(long idOne, long idTwo) {
//        boolean success = false;
//
//        TableRow firstRow = table.se(idOne);
//        TableRow secondRow = select(idTwo);
//
//        success = update(idOne, idTwo, secondRow.getName(), secondRow.getValue());
//        success &= update(idTwo, idOne, firstRow.getName(), firstRow.getValue());
//
//        return success;
//    }



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
