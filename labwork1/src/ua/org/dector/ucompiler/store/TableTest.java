package ua.org.dector.ucompiler.store;

import junit.framework.TestCase;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dector
 */
public class TableTest extends TestCase {
    Table table;

    public void setUp() throws Exception {
        table = new Table();
        table.insertRow(0, "User 1", 5.0);
        table.insertRow(1, "User 2", 2.0);
        table.insertRow(2, "Admin 1", 0);
        table.insertRow(3, "Admin 2", 0);
        table.insertRow(4, "Admin 3", 1);
    }

    public void testInsertRows() throws Exception {
        boolean inserted;
        inserted = table.insertRow(10, "User 1", 5.0);
        assertTrue(inserted);
        inserted = table.insertRow(11, "User 2", 2.0);
        assertTrue(inserted);
        inserted = table.insertRow(12, "Admin 1", 0);
        assertTrue(inserted);
        inserted = table.insertRow(1, "Admin 2", 0);
        assertFalse(inserted);
        inserted = table.insertRow(13, "Admin 2", 0);
        assertTrue(inserted);
        inserted = table.insertRow(14, "Admin 3", 1);
        assertTrue(inserted);
    }

    public void testDeleteRow() throws Exception {
        boolean deleted;
        deleted = table.deleteRow(100);
        assertFalse(deleted);
        deleted = table.deleteRow(3);
        assertTrue(deleted);
    }

    public void testUpdateRow() throws Exception {
        boolean updated;
        updated = table.updateRow(2, 2, "Admin_U 1", 10);
        assertTrue(updated);
        updated = table.updateRow(1, "User_U 2", 2.5);
        assertTrue(updated);
        updated = table.updateRow(0, "User_U 1");
        assertTrue(updated);
        updated = table.updateRow(0, 12.2);
        assertTrue(updated);

        updated = table.updateRow(101, 0d);
        assertFalse(updated);
    }

    public void testSelectRow() throws Exception {
        Map.Entry<String, Double> entry = table.selectRow(4);
        assertEquals(entry, new HashMap.SimpleEntry("Admin 3", 1d));
    }

    public void testCountRows() throws Exception {
        long tableSize = table.countRows();
        assertEquals(tableSize, 5);
    }

    public void tearDown() throws Exception {
        table = null;
    }
}
