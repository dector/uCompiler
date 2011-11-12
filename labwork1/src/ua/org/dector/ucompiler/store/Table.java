package ua.org.dector.ucompiler.store;

import java.util.*;

/**
 * Main structure storage. Use TableWrapper
 *
 * DEV NOTES:
 * Some rules for working with this class:
 * 1) This is unsecured data structure interface with a LITTLE of automatic
 * 2) ALL data processing types can be performed ONLY if you know row's ID
 * 3) ALL algorythmic operations (sophisticated selection, searching etc.) are in the TableWrapper
 *
 * @author dector
 */
public class Table {
    // Set of rows
    private Map<Long, Map.Entry<String, Double>> rows;

    Table() {
        reinitRows();
    }

    /**
     * Insert new record into table
     *
     * @param id    ID value of this record
     * @param name  NAME value of this record
     * @param value VALUE value of this record
     * @return  <b>true</b> if record was inserted, else <b>false</b>
     */
    public boolean insertRow(long id, String name, double value) {
        boolean inserted = false;

        if (! rows.containsKey(id)) {
            Map.Entry<String, Double> entry = new HashMap.SimpleEntry<String, Double>(name, value);
            rows.put(id, entry);
            inserted = true;
        }

        return inserted;
    }

    /**
     * Remove record from table
     *
     * @param id    record ID to remove
     * @return  <b>true</b> if record was removed, else <b>false</b>
     */
    public boolean deleteRow(long id) {
        boolean deleted = false;

        if (rows.containsKey(id)) {
            rows.remove(id);
            deleted = true;
        }

        return deleted;
    }

    /**
     * Fully modify row
     *
     * @param id        row ID to modify
     * @param newId     new row ID to set
     * @param newName   new row NAME to set
     * @param newValue  new row VALUE to set
     * @return  <b>true</b> is row was modified, else <b>false</b>
     */
    public boolean updateRow(long id, long newId, String newName, double newValue) {
        boolean updated = false;

        if (rows.containsKey(id)) {
            if (id != newId) {
                Map.Entry<String, Double> newEntry = new HashMap.SimpleEntry<String, Double>(newName, newValue);
                rows.remove(id);
                rows.put(newId, newEntry);
            } else {
                Map.Entry<String, Double> entry = rows.get(id);

                if (! newName.equals(entry.getKey())) {
                    Map.Entry<String, Double> newEntry = new HashMap.SimpleEntry<String, Double>(newName, newValue);
                    rows.put(id, newEntry);
                } else if (newValue != entry.getValue()){
                    entry.setValue(newValue);
                }
            }

            updated = true;
        }

        return updated;
    }

    /**
     * Modify row data
     *
     * @param id        row ID to modify
     * @param newName   new row NAME to set
     * @param newValue  new row VALUE to set
     * @return  <b>true</b> is row was modified, else <b>false</b>
     */
    public boolean updateRow(long id, String newName, double newValue) {
        return updateRow(id, id, newName, newValue);
    }

    /**
     * Modify row name
     *
     * @param id        row ID to modify
     * @param newName   new row NAME to set
     * @return  <b>true</b> is row was modified, else <b>false</b>
     */
    public boolean updateRow(long id, String newName) {
        return updateRow(id, newName, (selectRow(id) != null)?selectRow(id).getValue():0d);
    }

    /**
     * Modify row value
     *
     * @param id        row ID to modify
     * @param newValue  new row VALUE to set
     * @return  <b>true</b> is row was modified, else <b>false</b>
     */
    public boolean updateRow(long id, double newValue) {
        return updateRow(id, (selectRow(id) != null)?selectRow(id).getKey():null, newValue);
    }

    /**
     * Returns pair of <NAME, VALUE> selected by ID
     *
     * @param id row ID to select
     * @return NAME and VALUE pair
     */
    public Map.Entry<String, Double> selectRow(long id) {
        return rows.get(id);
    }

    /**
     * Count number of rows in table
     *
     * @return number of rows
     */
    public long countRows() {
        return rows.size();
    }

    /**
     * Returns set of all ID's in table
     *
     * @return set of all ID values
     */
    public Set<Long> getRowIds() {
        return rows.keySet();
    }

    /**
     * Init table as structure (will delete all records)
     */
    void reinitRows() {
        rows = new HashMap<Long, Map.Entry<String, Double>>();
    }
}
