package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public interface TablesHolder {
    public TokensTable getKeywordsTable();
    public TokensTable getOperatorsTable();
    public TokensTable getDelimitersTable();
}
