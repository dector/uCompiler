package ua.org.dector.uCompiler.lex_analyser;

import ua.org.dector.uCompiler.lex_analyser.errors.LexicParseError;
import ua.org.dector.uCompiler.lex_demo.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dector (dector9@gmail.com)
 */
public class LexicAnalyserState {
    private boolean finished;
    private boolean success;

    private int inputBuffer;
    private boolean bufferProcessed;
    private StringBuilder tokenAccumulator;

    private Position currentPosition;
    private LexicParseError error;

    private int positionIndex;
    private List<Token> result;

    public LexicAnalyserState() {
        finished = false;
        success = false;

        bufferProcessed = true;
        positionIndex = 0;

        currentPosition = Position.START;

        tokenAccumulator = new StringBuilder();

        result = new LinkedList<Token>();
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public LexicParseError getError() {
        return error;
    }

    public void setError(LexicParseError error) {
        this.error = error;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public List<Token> getResult() {
        return result;
    }

    public int getInputBuffer() {
        return inputBuffer;
    }

    public void setInputBuffer(int inputBuffer) {
        this.inputBuffer = inputBuffer;
        setBufferProcessed(false);
    }

    public boolean isBufferProcessed() {
        return bufferProcessed;
    }

    public void setBufferProcessed(boolean bufferProcessed) {
        this.bufferProcessed = bufferProcessed;
    }

    public StringBuilder getTokenAccumulator() {
        return tokenAccumulator;
    }

    public void accumulate() {
        tokenAccumulator.append((char) inputBuffer);
    }

    public String getAccumulator() {
        return tokenAccumulator.toString();
    }

    public String clearAccumulator() {
        String returnString = tokenAccumulator.toString();
        tokenAccumulator = new StringBuilder();

        return returnString;
    }

    public void setTokenAccumulator(StringBuilder tokenAccumulator) {
        this.tokenAccumulator = tokenAccumulator;
    }

    public void incrementPositionIndex() {
        positionIndex++;
    }

    public boolean addResult(Token t) {
        return result.add(t);
    }
}
