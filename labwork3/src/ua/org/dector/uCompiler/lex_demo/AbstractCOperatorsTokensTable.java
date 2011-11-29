package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.Token;
import ua.org.dector.uCompiler.lex_analyser.TokenType;
import ua.org.dector.uCompiler.lex_analyser.TokensTable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCOperatorsTokensTable implements TokensTable {
    private static Map<String, Token> operatorsMap;

    static {
        operatorsMap = new LinkedHashMap<String, Token>();

        addToken(new Token(TokenType.PRE_DECREMENT, "--"));
        addToken(new Token(TokenType.MINUS, "-"));
        addToken(new Token(TokenType.EQUALS, "=="));
        addToken(new Token(TokenType.ASSIGN, "="));
    }

    public AbstractCOperatorsTokensTable() {}

    public boolean hasToken(String charGroup) {
        return operatorsMap.containsKey(charGroup);
    }

    public Token getToken(String charGroup) {
        return operatorsMap.get(charGroup);
    }

    private static void addToken(Token token) {
        operatorsMap.put(token.getValue(), token);
    }
}
