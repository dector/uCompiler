package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public interface SymbolFactory {
    public SymbolType getType(char symbol);
}
