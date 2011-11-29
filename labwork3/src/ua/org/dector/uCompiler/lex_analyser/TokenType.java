package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public enum TokenType {
    IDENTIFIER,
    DECIMAL_NUMBER, REAL_NUMBER,
    STRING,

    // Keywords
    DO, IF, RETURN, WHILE,

    // Delimiters
    LEFT_BRACE, RIGHT_BRACE,
    LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
    LEFT_BRAKET, RIGHT_BRAKET,
    SEMICOLON,

    // Operators
    MINUS,
    PRE_DECREMENT,
    ASSIGN,
    EQUALS
}
