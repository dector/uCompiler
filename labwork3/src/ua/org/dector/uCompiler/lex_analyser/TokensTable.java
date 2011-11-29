package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public interface TokensTable {
    public boolean hasToken(String charGroup);
    public Token getToken(String charGroup);
}
