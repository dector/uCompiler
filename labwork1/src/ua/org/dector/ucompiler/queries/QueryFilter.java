package ua.org.dector.ucompiler.queries;

//todo: I'm looking like some shirt. REFACTOR ME!!! O_o

/**
 * @author dector
 */
public class QueryFilter {
    private QueryFilterField field;
    private QueryFilterCondition condition;
    private String value;

    public QueryFilter(QueryFilterField field, QueryFilterCondition condition, String value) {
        this.field = field;
        this.condition = condition;
    }
}
