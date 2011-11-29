package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public String getStringValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "[Token " + getType() + " \"" + getValue() + "\"]";
    }

}
