package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public class UndefinedInputError extends LexicParseError {
    public UndefinedInputError(String message, int position) {
        super(message, position);
        setName("UndefinedInputError");
    }
}
