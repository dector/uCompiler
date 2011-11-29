package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class DecimalError extends LexicParseError {
    public DecimalError(String message, int position) {
        super(message, position);
        setName("DecimalError");
    }
}
