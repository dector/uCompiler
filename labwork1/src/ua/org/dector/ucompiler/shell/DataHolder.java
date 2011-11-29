package ua.org.dector.ucompiler.shell;

import ua.org.dector.ucompiler.store.TableWrapper;

/**
 * @author dector
 */
public class DataHolder {
    private TableWrapper tw;

    private static DataHolder instance;

    private DataHolder() {
        tw = new TableWrapper();
    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }

        return instance;
    }

    public TableWrapper getTableWrapper() {
        return tw;
    }
}
