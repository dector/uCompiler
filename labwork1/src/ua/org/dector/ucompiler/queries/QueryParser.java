package ua.org.dector.ucompiler.queries;

/**
 * @author dector
 */
public class QueryParser {
    public static Query parseQuery(String queryString) {
        Query query = new Query();

        //todo: This is partial case. Make it more formal and universal.
        // select by id = 0
        query.setType(QueryType.SELECT);
        query.addFilter(QueryFilterField.ID, QueryFilterCondition.EQUALS, new QueryFilterValue<Long>(0L));

        //todo: I'm feeling so empty. Implement me!

        return query;
    }
}
