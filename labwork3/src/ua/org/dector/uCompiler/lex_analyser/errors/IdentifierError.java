package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class IdentifierError extends LexicParseError {
    public IdentifierError(String message, int position) {
        super(message, position);
        setName("IdentifierError");
    }
}
