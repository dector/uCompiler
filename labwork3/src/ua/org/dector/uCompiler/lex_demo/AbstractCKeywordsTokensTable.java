package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.Token;
import ua.org.dector.uCompiler.lex_analyser.TokenType;
import ua.org.dector.uCompiler.lex_analyser.TokensTable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCKeywordsTokensTable implements TokensTable {
    private static Map<String, Token> keywordsMap;

    static {
        keywordsMap = new LinkedHashMap<String, Token>();

        addToken(new Token(TokenType.IF, "if"));
        addToken(new Token(TokenType.DO, "do"));
        addToken(new Token(TokenType.RETURN, "return"));
        addToken(new Token(TokenType.WHILE, "while"));
    }

    public AbstractCKeywordsTokensTable() {}

    public boolean hasToken(String charGroup) {
        return keywordsMap.containsKey(charGroup);
    }

    public Token getToken(String charGroup) {
        return keywordsMap.get(charGroup);
    }

    private static void addToken(Token token) {
        keywordsMap.put(token.getValue(), token);
    }
}
