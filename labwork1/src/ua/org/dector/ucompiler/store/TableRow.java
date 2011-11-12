package ua.org.dector.ucompiler.store;

import java.util.HashMap;
import java.util.Map;

/**
 * Data structure for users which represents one table raw
 *
 * @author dector
 */
public class TableRow {
    private Map.Entry<Long, Map.Entry<String, Double>> row;

    /**
     * Create new instance
     *
     * @param row representation of row in such complicated structure
     */
    TableRow(Map.Entry<Long, Map.Entry<String, Double>> row) {
        this.row = row;
    }

    /**
     * Returns ID of this row record
     *
     * @return ID value
     */
    public long getId() {
        return row.getKey();
    }

    /**
     * Returns NAME of this row record
     *
     * @return NAME value
     */
    public String getName() {
        return row.getValue().getKey();
    }

    /**
     * Returns VALUE of this row record
     *
     * @return VALUE value
     */
    public Double getValue() {
        return row.getValue().getValue();
    }

    /**
     * Create new representation of row record
     *
     * @param id    record ID
     * @param entry pair of <NAME, VALUE>
     * @return new instance of row representation
     */
    public static TableRow newInstance(Long id, Map.Entry<String, Double> entry) {
        return new TableRow(new HashMap.SimpleEntry<Long, Map.Entry<String, Double>>(id, entry));
    }

    public static TableRow newInstance(Long id, String name, Double value) {
        return newInstance(id, new HashMap.SimpleEntry<String, Double>(name, value));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("TableRow[");
        sb.append(getId());
        sb.append("] = <");
        sb.append(getName());
        sb.append(", ");
        sb.append(getValue());
        sb.append(">");

        return sb.toString();
    }

//    public int compareTo(TableRow row) {
//        if (this.getId() > row.getId()) {
//            return 1;
//        } else if (this.getId() < row.getId()) {
//            return -1;
//        } else {
//            return 0;
//        }
//    }

    @Override
    public boolean equals(Object obj) {
        TableRow row = (TableRow)obj;

        if (getId() == row.getId()
                && getName().equals(row.getName())
                && getValue().equals(row.getValue())) {
            return true;
        } else {
            return false;
        }
    }
}
