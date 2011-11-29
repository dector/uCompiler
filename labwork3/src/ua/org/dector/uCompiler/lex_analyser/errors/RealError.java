package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class RealError extends LexicParseError {
    public RealError(String message, int position) {
        super(message, position);
        setName("RealError");
    }
}
