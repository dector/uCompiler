package ua.org.dector.ucompiler.queries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dector
 */
public class Query {
    private QueryType type;
    private List<QueryFilter> filtersList;

    public Query() {
        filtersList = new ArrayList<QueryFilter>();
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public QueryType getType() {
        return type;
    }

    public boolean containsFilter(QueryFilter filter) {
        return filtersList.contains(filter);
    }

    public int countFilters() {
        return filtersList.size();
    }

    public boolean addFilter(QueryFilter filter) {
        boolean added = false;

        if (! containsFilter(filter)) {
            filtersList.add(filter);
            added = true;
        }

        return added;
    }

    public boolean addFilter(QueryFilterField field, QueryFilterCondition condition, QueryFilterValue value) {
        return addFilter(new QueryFilter(field, condition, value));
    }

    @Override
    public String toString() {
        return "Query{" +
                "type=" + type +
                ", filtersList=" + filtersList +
                '}';
        //todo: I'm feeling so empty. Implement me!
    }

//todo: I'm feeling so empty. Implement me!
}
