package ua.org.dector.uCompiler.lex_analyser;

import sun.awt.Symbol;
import ua.org.dector.uCompiler.lex_analyser.errors.LexicParseError;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyException;
import java.util.List;

/**
 * @author dector (dector9@gmail.com)
 */
public class LexicAnalyser {
    private SymbolFactory symbolFactory;

    private LexicAnalyserState state;
    private PositionsProcessor pp;

    public LexicAnalyser(PositionsProcessor pp, SymbolFactory symbolFactory) {
        this.symbolFactory = symbolFactory;

        state = new LexicAnalyserState();

        this.pp = pp;
        pp.setState(state);
    }

    public void parse(InputStream in) {
        while (! state.isFinished()) {
            if (state.isBufferProcessed()) {
                try {
                    state.setInputBuffer(in.read());

                    if (state.getInputBuffer() == -1) {
                        pp.setFinalCheckPosition();
                    }

                } catch (IOException e) {
                    pp.setIOExceptionPosition();
                }
            }

            pp.process(symbolFactory.getType((char) state.getInputBuffer()));
        }
    }

    public boolean isSuccess() {
        return state.isSuccess();
    }

    public LexicParseError getError() {
        return state.getError();
    }

    public List<Token> getResult() {
        return state.getResult();
    }

    public SymbolFactory getSymbolFactory() {
        return symbolFactory;
    }
}