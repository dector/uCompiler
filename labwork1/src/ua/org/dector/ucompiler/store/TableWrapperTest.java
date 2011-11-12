package ua.org.dector.ucompiler.store;

import junit.framework.TestCase;

import java.util.*;

/**
 * @author dector
 */
public class TableWrapperTest extends TestCase {
    TableWrapper tw;

    public void setUp() throws Exception {
        tw = new TableWrapper();
        tw.insert(0, "User 1", 5.0);
        tw.insert(1, "User 2", 2.0);
        tw.insert(2, "Admin 1", 0);
        tw.insert(3, "Admin 2", 0);
        tw.insert(4, "Admin 3", 1);
    }

    public void testInsert() throws Exception {
        boolean inserted;
        inserted = tw.insert(5, "User 1", 200);
        assertTrue(inserted);

        inserted = tw.insert(0, "User 2", 200);
        assertFalse(inserted);
    }

    public void testDelete() throws Exception {
        boolean deleted;
        deleted = tw.delete(0);
        assertTrue(deleted);

        deleted = tw.delete(0);
        assertFalse(deleted);
    }

    public void testSelectById() throws Exception {
        TableRow testedRow = TableRow.newInstance(0L, "User 1", 5d);
        TableRow selectedRow = tw.select(0);
        assertEquals(testedRow, selectedRow);

        selectedRow = tw.select(100);
        assertNull(selectedRow);
    }

    public void testSelectByName() throws Exception {
        List<TableRow> adminRows = new ArrayList<TableRow>();
        adminRows.add(TableRow.newInstance(2L, "Admin 1", 0d));
        adminRows.add(TableRow.newInstance(3L, "Admin 2", 0d));
        adminRows.add(TableRow.newInstance(4L, "Admin 3", 1d));

        List<TableRow> rowList;
        rowList = tw.select("Admin", false);
        assertEquals(adminRows, rowList);
        rowList = tw.select("Admon", false);
        assertEquals(adminRows, rowList);
        rowList = tw.select("Some", false);
        assertEquals("[]", rowList.toString());

        assertTrue(true);
    }

    public void testUpdate() throws Exception {
        boolean updated;
        updated = tw.update(0, "User", 2d);
        assertTrue(updated);
        updated = tw.update(10, "User", 2d);
        assertFalse(updated);

        updated = tw.update(0, "User");
        assertTrue(updated);
        updated = tw.update(10, "User");
        assertFalse(updated);

        updated = tw.update(0, 2d);
        assertTrue(updated);
        updated = tw.update(10, 2d);
        assertFalse(updated);

        updated = tw.update(0, 101, "User", 2d);
        assertTrue(updated);
        updated = tw.update(0, 1, "User", 2d);
        assertFalse(updated);
    }

    public void tearDown() throws Exception {
        tw = null;
    }
}
