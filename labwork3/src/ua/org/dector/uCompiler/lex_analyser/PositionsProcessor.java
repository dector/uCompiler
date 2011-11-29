package ua.org.dector.uCompiler.lex_analyser;

/**
 * @author dector (dector9@gmail.com)
 */
public interface PositionsProcessor {
    public void setIOExceptionPosition();
    public void setFinalCheckPosition();
    public void process(SymbolType in);
    public void setState(LexicAnalyserState state);
}
