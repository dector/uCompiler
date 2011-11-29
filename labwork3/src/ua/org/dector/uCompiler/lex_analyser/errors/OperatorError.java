package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class OperatorError extends LexicParseError {
    public OperatorError(String message, int position) {
        super(message, position);
        setName("OperatorError");
    }
}
