package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.TablesHolder;
import ua.org.dector.uCompiler.lex_analyser.TokensTable;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCTablesHolder implements TablesHolder {
    TokensTable keywordsTable;
    TokensTable operatorsTable;
    TokensTable delimitersTable;

    public AbstractCTablesHolder(TokensTable keywordsTable, TokensTable operatorsTable, TokensTable delimitersTable) {
        this.keywordsTable = keywordsTable;
        this.operatorsTable = operatorsTable;
        this.delimitersTable = delimitersTable;
    }

    public TokensTable getKeywordsTable() {
        return keywordsTable;
    }

    public TokensTable getOperatorsTable() {
        return operatorsTable;
    }

    public TokensTable getDelimitersTable() {
        return delimitersTable;
    }
}
