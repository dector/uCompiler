package ua.org.dector.ucompiler.store;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dector
 */
public class TableRowTest extends TestCase {
    TableRow tr;

    public void setUp() throws Exception {
        Map.Entry entry = new HashMap.SimpleEntry<String, Double>("User 1", 10d);
        Map.Entry higherEntry = new HashMap.SimpleEntry<Long, Map.Entry<String, Double>>(5L, entry);
        tr = new TableRow(higherEntry);
    }

    public void testGetId() throws Exception {
        assertEquals(tr.getId(), 5);
    }

    public void testGetName() throws Exception {
        assertEquals(tr.getName(), "User 1");
    }

    public void testGetValue() throws Exception {
        assertEquals(tr.getValue(), 10d);
    }

    public void testNewInstance() throws Exception {
        Map.Entry entry = new HashMap.SimpleEntry<String, Double>("User 1", 10d);
        TableRow testRow = TableRow.newInstance(5L, entry);

        assertEquals(testRow.toString(), tr.toString());
    }

    public void tearDown() throws Exception {

    }
}
