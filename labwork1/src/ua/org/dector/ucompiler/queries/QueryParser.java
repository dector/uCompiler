package ua.org.dector.ucompiler.queries;

import ua.org.dector.ucompiler.exceptions.IsNotAQueryException;
import ua.org.dector.ucompiler.exceptions.QueryParseException;

/**
 * @author dector
 */
public class QueryParser {
    public static Query parseQuery(String queryString) throws QueryParseException, IsNotAQueryException {

        if (! isAQuery(queryString)) {
            throw new IsNotAQueryException(queryString);
        }

        // todo: FIXIT
        Query query = new Query(QueryType.SELECT, QueryFilterField.ID, QueryFilterCondition.EQUALS, 0);

        return query;
    }

    private static boolean isAQuery(String query) {
        boolean correct = false;

        query = query.trim();

        if (! (query.indexOf(QuerySyntax.COMMENT_CHAR) == 0    // Just comment line?
                || query.isEmpty())) {                          // Just empty line?
            correct = true;
        }

        return correct;
    }
}
