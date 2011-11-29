package ua.org.dector.ucompiler.queries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dector
 */
public class Query {
    private QueryType type;
    private QueryFilterField field;
    private QueryFilterCondition condition;
    private String value;

    public Query(QueryType type, QueryFilterField field, QueryFilterCondition condition, String value) {
        this.type = type;
        this.field = field;
        this.condition = condition;
        this.value = value;
    }

    public Query(QueryType type, QueryFilterField field, QueryFilterCondition condition, long value) {
        this(type, field, condition, String.valueOf(value));
    }

    public Query(QueryType type, QueryFilterField field, QueryFilterCondition condition, double value) {
        this(type, field, condition, String.valueOf(value));
    }

    public QueryType getType() {
        return type;
    }

    public QueryFilterField getField() {
        return field;
    }

    public QueryFilterCondition getCondition() {
        return condition;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Query{ type=").append(type).append(", field=").append(field).append(", condition=").append(condition);
        sb.append(", value='").append(value).append("'}");

        return sb.toString();
    }
}
