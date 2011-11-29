package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.Token;
import ua.org.dector.uCompiler.lex_analyser.TokenType;
import ua.org.dector.uCompiler.lex_analyser.TokensTable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCDelimitersTokensTable implements TokensTable {
    private static Map<String, Token> delimitersMap;

    static {
        delimitersMap = new LinkedHashMap<String, Token>();

        addToken(new Token(TokenType.LEFT_BRACE, "{"));
        addToken(new Token(TokenType.RIGHT_BRACE, "}"));
        addToken(new Token(TokenType.LEFT_PARENTHESIS, "("));
        addToken(new Token(TokenType.RIGHT_PARENTHESIS, ")"));
        addToken(new Token(TokenType.LEFT_BRAKET, "["));
        addToken(new Token(TokenType.RIGHT_BRAKET, "]"));
        addToken(new Token(TokenType.SEMICOLON, ";"));
    }

    public AbstractCDelimitersTokensTable() {}

    public boolean hasToken(String charGroup) {
        return delimitersMap.containsKey(charGroup);
    }

    public Token getToken(String charGroup) {
        return delimitersMap.get(charGroup);
    }

    private static void addToken(Token token) {
        delimitersMap.put(token.getValue(), token);
    }
}
