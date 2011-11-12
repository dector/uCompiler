package ua.org.dector.ucompiler.common;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dector
 */
public class IOUtilsTest extends TestCase {
    public void testLoadFile() throws Exception {
        String filename = "test_files/load_test.usc";
        List<String> queriesList = IOUtils.loadFile(filename);
        assertEquals(queriesList.get(0), ";uShell commands (USC)");
        assertEquals(queriesList.get(1), "");
        assertEquals(queriesList.get(2), "select by id = 0");
    }
}
