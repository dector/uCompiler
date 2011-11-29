package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class UndexpectedEndOfFileError extends LexicParseError {
    public UndexpectedEndOfFileError(String message, int position) {
        super(message, position);
        setName("UndexpectedEndOfFileError");
    }
}
