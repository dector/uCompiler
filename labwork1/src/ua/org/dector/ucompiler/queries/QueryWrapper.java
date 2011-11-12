package ua.org.dector.ucompiler.queries;

import ua.org.dector.ucompiler.common.IOUtils;

import java.util.List;

/**
 * @author dector
 */
public class QueryWrapper {
    public static boolean executeQuery(String queryString) {
        boolean success = false;

        //todo: I'm debug information. Delete me completely!
        System.out.println("executeQuery() requested: \"" + queryString + "\"");

        Query query;
        if (isCorrectQuery(queryString)) {
            query = QueryParser.parseQuery(queryString);
            success = true;

            QueryType queryType = query.getType();

            switch (queryType) {
                case SELECT: {

                }; break;
            }

            //todo: I'm debug information. Delete me completely!
            System.out.println("Parsed as query: " + query);
        }


        return success;


//        QueryType queryType = query.getCommand();
//        switch (queryQueryType) {
//            case select: {
//
//            }; break;
//            case delete: {
//
//            }; break;
//            case insert: {
//
//            }; break;
//            case update: {
//
//            }; break;
//        }
    }

    public static void executeQueries(List<String> queriesList) {
        //todo: I'm debug information. Delete me completely!
        System.out.println("Execute queries list requested: " + queriesList);

        //todo: I'm feeling so empty. Implement me!
        for (String query : queriesList) {
            executeQuery(query);
        }
    }

    public static void executeQueriesFromFile(String filename) {
        //todo: I'm debug information. Delete me completely!
        System.out.println("Execute queries file requested: " + filename);

        //todo: I'm feeling so empty. Implement me!
        List<String> fileLines = IOUtils.loadFile(filename);
        executeQueries(fileLines);
    }

    private static boolean isCorrectQuery(String query) {
        boolean correct = false;

        if (isAQuery(query)) {
            correct = true;
        }

        return correct;
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
