package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.SymbolFactory;
import ua.org.dector.uCompiler.lex_analyser.SymbolType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCSymbolFactory implements SymbolFactory {
    static Map<Character, SymbolType> charCache;

    static {
        charCache = new LinkedHashMap<Character, SymbolType>();

        charCache.put('A', SymbolType.LETTER);
        charCache.put('B', SymbolType.LETTER);
        charCache.put('C', SymbolType.LETTER);
        charCache.put('D', SymbolType.LETTER);
        charCache.put('E', SymbolType.LETTER);
        charCache.put('F', SymbolType.LETTER);
        charCache.put('G', SymbolType.LETTER);
        charCache.put('H', SymbolType.LETTER);
        charCache.put('I', SymbolType.LETTER);
        charCache.put('J', SymbolType.LETTER);
        charCache.put('K', SymbolType.LETTER);
        charCache.put('L', SymbolType.LETTER);
        charCache.put('M', SymbolType.LETTER);
        charCache.put('N', SymbolType.LETTER);
        charCache.put('O', SymbolType.LETTER);
        charCache.put('P', SymbolType.LETTER);
        charCache.put('Q', SymbolType.LETTER);
        charCache.put('R', SymbolType.LETTER);
        charCache.put('S', SymbolType.LETTER);
        charCache.put('T', SymbolType.LETTER);
        charCache.put('U', SymbolType.LETTER);
        charCache.put('V', SymbolType.LETTER);
        charCache.put('W', SymbolType.LETTER);
        charCache.put('X', SymbolType.LETTER);
        charCache.put('Y', SymbolType.LETTER);
        charCache.put('Z', SymbolType.LETTER);

        charCache.put('a', SymbolType.LETTER);
        charCache.put('b', SymbolType.LETTER);
        charCache.put('c', SymbolType.LETTER);
        charCache.put('d', SymbolType.LETTER);
        charCache.put('e', SymbolType.LETTER);
        charCache.put('f', SymbolType.LETTER);
        charCache.put('g', SymbolType.LETTER);
        charCache.put('h', SymbolType.LETTER);
        charCache.put('i', SymbolType.LETTER);
        charCache.put('j', SymbolType.LETTER);
        charCache.put('k', SymbolType.LETTER);
        charCache.put('l', SymbolType.LETTER);
        charCache.put('m', SymbolType.LETTER);
        charCache.put('n', SymbolType.LETTER);
        charCache.put('o', SymbolType.LETTER);
        charCache.put('p', SymbolType.LETTER);
        charCache.put('q', SymbolType.LETTER);
        charCache.put('r', SymbolType.LETTER);
        charCache.put('s', SymbolType.LETTER);
        charCache.put('t', SymbolType.LETTER);
        charCache.put('u', SymbolType.LETTER);
        charCache.put('v', SymbolType.LETTER);
        charCache.put('w', SymbolType.LETTER);
        charCache.put('x', SymbolType.LETTER);
        charCache.put('y', SymbolType.LETTER);
        charCache.put('z', SymbolType.LETTER);

        charCache.put('0', SymbolType.DIGIT);
        charCache.put('1', SymbolType.DIGIT);
        charCache.put('2', SymbolType.DIGIT);
        charCache.put('3', SymbolType.DIGIT);
        charCache.put('4', SymbolType.DIGIT);
        charCache.put('5', SymbolType.DIGIT);
        charCache.put('6', SymbolType.DIGIT);
        charCache.put('7', SymbolType.DIGIT);
        charCache.put('8', SymbolType.DIGIT);
        charCache.put('9', SymbolType.DIGIT);

        charCache.put('{', SymbolType.DELIMITER);
        charCache.put('}', SymbolType.DELIMITER);
        charCache.put('(', SymbolType.DELIMITER);
        charCache.put(')', SymbolType.DELIMITER);
        charCache.put('[', SymbolType.DELIMITER);
        charCache.put(']', SymbolType.DELIMITER);
        charCache.put(';', SymbolType.DELIMITER);

        charCache.put('-', SymbolType.OPERATOR);
        charCache.put('=', SymbolType.OPERATOR);

        charCache.put('.', SymbolType.DECIMAL_DELIMITER);
        charCache.put('\'', SymbolType.STRING_TAG);

        charCache.put('\'', SymbolType.STRING_TAG);
        charCache.put('"', SymbolType.STRING_TAG);

        charCache.put(' ', SymbolType.IGNORE);
        charCache.put('\n', SymbolType.IGNORE);
        charCache.put('\t', SymbolType.IGNORE);
    }

    public AbstractCSymbolFactory() {}

    public SymbolType getType(char symbol) {
        SymbolType type = null;

        if (charCache.containsKey(symbol)) {
            type = charCache.get(symbol);
        }

        return type;
    }
}
