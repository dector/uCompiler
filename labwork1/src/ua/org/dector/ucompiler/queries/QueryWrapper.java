package ua.org.dector.ucompiler.queries;

import ua.org.dector.ucompiler.common.IOUtils;
import ua.org.dector.ucompiler.exceptions.IsNotAQueryException;
import ua.org.dector.ucompiler.exceptions.QueryParseException;
import ua.org.dector.ucompiler.shell.DataHolder;
import ua.org.dector.ucompiler.store.TableWrapper;

import java.util.List;

/**
 * @author dector
 */
public class QueryWrapper {
    public static String executeQuery(String queryString) {
        String resultString = null;
        boolean success = false;

        System.out.println("executeQuery() requested: \"" + queryString + "\"");

        Query query;
        try {
            query = QueryParser.parseQuery(queryString);
            System.out.println("Parsed as query: " + query);

            TableWrapper tw = DataHolder.getInstance().getTableWrapper();

            QueryType queryType = query.getType();

            switch (queryType) {
                case SELECT: {

                }; break;

                case DELETE: {
                    success = tw.delete(Long.parseLong(query.getValue()));
                    resultString = "SUCCESS :: Query " + queryString + " executed";
                }; break;

                case INSERT: {
                    resultString = "SUCCESS :: Query " + queryString + " executed";
                }; break;

                case UPDATE: {
                    resultString = "SUCCESS :: Query " + queryString + " executed";
                }; break;
            }
        } catch (QueryParseException e) {
            resultString = "FAIL :: Query " + queryString + " is incorrect";
        } catch (IsNotAQueryException e) {
            System.out.println(queryString + " is not query");
        }

        return resultString;
    }

    public static void executeQueries(List<String> queriesList) {
        System.out.println("Execute queries list requested: " + queriesList);

        String queryResult;
        for (String query : queriesList) {
            queryResult = executeQuery(query);
            if (queryResult != null) {
                System.out.println(queryResult);
            }
        }
    }

    public static void executeQueriesFromFile(String filename) {
        System.out.println("Execute queries file requested: " + filename);

        List<String> fileLines = IOUtils.loadFile(filename);
        executeQueries(fileLines);
    }
}
