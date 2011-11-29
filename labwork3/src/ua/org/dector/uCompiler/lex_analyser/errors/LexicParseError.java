package ua.org.dector.uCompiler.lex_analyser.errors;

/**
 * @author dector (dector9@gmail.com)
 */
public abstract class LexicParseError {
    private String name;
    private String message;
    private int position;

    public LexicParseError(String message, int position) {
        this.message = message;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getPosition() {
        return position;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
