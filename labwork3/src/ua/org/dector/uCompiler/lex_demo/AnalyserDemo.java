package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.*;
import ua.org.dector.uCompiler.lex_analyser.errors.LexicParseError;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author dector (dector9@gmail.com)
 */
public class AnalyserDemo {
    public static void main(String[] args) {
        String expression =
                "do" +
                "{" +
                    "--n;" +
                    "if(b==a[n])" +
                        "return n;" +
                "}" +
                "while (n);" +
                "return 0;";

        InputStream in = new BufferedInputStream(new ByteArrayInputStream(expression.getBytes()));

        SymbolFactory symbolFactory = new AbstractCSymbolFactory();
        TokensTable keywordsTable = new AbstractCKeywordsTokensTable();
        TokensTable operatorsTable = new AbstractCOperatorsTokensTable();
        TokensTable delimitersTable = new AbstractCDelimitersTokensTable();

        TablesHolder holder = new AbstractCTablesHolder(keywordsTable, operatorsTable, delimitersTable);
        PositionsProcessor pp = new AbstractCPositionsProcessor(holder);

        LexicAnalyser la = new LexicAnalyser(pp, symbolFactory);

        la.parse(in);

        if (la.isSuccess()) {
            // Print all tokens
            List<Token> tokens = la.getResult();

            for (Token token : tokens) {
                System.out.println(token);
            }
        } else {
            LexicParseError error = la.getError();

            System.out.println(error.getName() + ": " +
                    error.getMessage() + " at " + error.getPosition());
        }

    }
}
