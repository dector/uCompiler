package ua.org.dector.uCompiler.lex_demo;

import ua.org.dector.uCompiler.lex_analyser.*;
import ua.org.dector.uCompiler.lex_analyser.errors.*;

import static ua.org.dector.uCompiler.lex_demo.Position.*;

/**
 * @author dector (dector9@gmail.com)
 */
public class AbstractCPositionsProcessor implements PositionsProcessor {
    private LexicAnalyserState state;

    private TokensTable keywordsTable;
    private TokensTable operatorsTable;
    private TokensTable delimitersTable;

    public AbstractCPositionsProcessor(TablesHolder holder) {
        keywordsTable = holder.getKeywordsTable();
        operatorsTable = holder.getOperatorsTable();
        delimitersTable = holder.getDelimitersTable();
    }

    public void setState(LexicAnalyserState state) {
        this.state = state;
    }

    public void setIOExceptionPosition() {
        setPosition(IO_EXCEPTION);
    }

    public void setFinalCheckPosition() {
        setPosition(SUCCESS_CHECK);
    }

    public void process(SymbolType in) {
        switch (state.getCurrentPosition()) {
            case START: doStart(in); break;
            case ACCUMULATE_IDENTIFIER: accumulateIdentifier(in); break;
            case ACCUMULATE_DECIMAL_NUMBER: accumulateDecimal(in); break;
            case ACCUMULATE_REAL_NUMBER: accumulateReal(in); break;
            case ACCUMULATE_OPERATOR: accumulateOperator(in); break;
            case ACCUMULATE_STRING: accumulateString(in); break;

            case CHECK_OPERATOR: checkOperator(); break;
            case CHECK_KEYWORD: checkKeyword(); break;

            case NEW_IDENTIFIER_TOKEN: createIdentifier(); break;
            case NEW_KEYWORD_TOKEN: createKeyword(); break;
            case NEW_DECIMAL_NUMBER_TOKEN: createDecimal(); break;
            case NEW_REAL_NUMBER_TOKEN: createReal(); break;
            case NEW_DELIMITER_TOKEN: createDelimiter(); break;
            case NEW_OPERATOR_TOKEN: createOperator(); break;
            case NEW_STRING_TOKEN: createString(); break;

            case FINISHED: finished(); break;
            case SUCCESS_CHECK: checkSuccess(); break;
            case DETECT_STRING: detectString(in); break;

            case IO_EXCEPTION: ioException(); break;
            case IDENTIFIER_ERROR: identifierError(); break;
            case DECIMAL_NUMBER_ERROR: decimalError(); break;
            case REAL_NUMBER_ERROR: realError(); break;
            case OPERATOR_ERROR: operatorError(); break;

            case UNEXPECTED_END_OF_FILE_ERROR: undexpectedEndOfFile(); break;
            case UNDEFINED_INPUT_ERROR: undefinedInput(); break;
        }
    }

    private void setPosition(Position p) {
        state.setCurrentPosition(p);
    }

    private void processed() {
        state.setBufferProcessed(true);
        state.incrementPositionIndex();
    }

    // Start position

    private void doStart(SymbolType in) {
        state.setBufferProcessed(false);

        switch (in) {
            case IGNORE: processed(); break;
            case LETTER: setPosition(ACCUMULATE_IDENTIFIER); break;
            case DIGIT: setPosition(ACCUMULATE_DECIMAL_NUMBER); break;
            case OPERATOR: setPosition(ACCUMULATE_OPERATOR); break;
            case DELIMITER: setPosition(NEW_DELIMITER_TOKEN); break;
//            case STRING_TAG: setPosition(ACCUMULATE_STRING); break;
            case STRING_TAG: setPosition(DETECT_STRING); break;
            default: setPosition(UNDEFINED_INPUT_ERROR);
        }

//        processed();
    }

    // =================== ACCUMULATE POSITIONS ====================

    private void accumulateIdentifier(SymbolType in) {
        switch (in) {
            case LETTER:
            case DIGIT: {
                state.accumulate();
                processed();
            } break;
            case IGNORE:
            case DELIMITER:
            case OPERATOR: setPosition(CHECK_KEYWORD); break;
            case DECIMAL_DELIMITER: setPosition(IDENTIFIER_ERROR);
        }
    }

    private void accumulateDecimal(SymbolType in) {
        switch (in) {
            case DIGIT: {
                state.accumulate();
                processed();
            } break;

            case IGNORE:
            case DELIMITER:
            case OPERATOR: setPosition(NEW_DECIMAL_NUMBER_TOKEN); break;
            case DECIMAL_DELIMITER: setPosition(ACCUMULATE_REAL_NUMBER); break;

            case LETTER: setPosition(DECIMAL_NUMBER_ERROR); break;
        }
    }

    private void accumulateReal(SymbolType in) {
        switch (in) {
            case DECIMAL_DELIMITER:
            case DIGIT: {
                state.accumulate();
                processed();
            } break;

            case IGNORE:
            case DELIMITER:
            case OPERATOR: setPosition(NEW_REAL_NUMBER_TOKEN); break;
            default: setPosition(REAL_NUMBER_ERROR); break;
        }
    }

    private void accumulateOperator(SymbolType in) {
        switch (in) {
            case OPERATOR: {
                state.accumulate();
                processed();
            } break;
            default: setPosition(CHECK_OPERATOR);
        }
    }

    private void accumulateString(SymbolType in) {
        switch (in) {
            case STRING_TAG: setPosition(NEW_STRING_TOKEN); break;
            default: state.accumulate();
        }

        processed();
    }

    private void detectString(SymbolType in) {
        setPosition(ACCUMULATE_STRING);

        processed();
    }

    private void checkKeyword() {
        String s = state.getAccumulator();

        if (keywordsTable.hasToken(s)) {
            setPosition(NEW_KEYWORD_TOKEN);
        } else {
            setPosition(NEW_IDENTIFIER_TOKEN);
        }
    }

    private void checkOperator() {
        String currentOperator = state.getAccumulator();
        if (operatorsTable.hasToken(currentOperator)) {
            setPosition(NEW_OPERATOR_TOKEN);
        } else {
            setPosition(OPERATOR_ERROR);
        }

//        processed();
    }

    // ============================== CREATE POSITIONS ==============================

    private void createIdentifier() {
        String identifier = state.clearAccumulator();

        Token t = new Token(TokenType.IDENTIFIER, identifier);
        state.addResult(t);

        setPosition(START);
    }

    private void createKeyword() {
        String keyword = state.clearAccumulator();

        Token t = keywordsTable.getToken(keyword);
        state.addResult(t);

        setPosition(START);
    }

    private void createDecimal() {
        String number = String.valueOf(state.clearAccumulator());

        state.addResult(new Token(TokenType.DECIMAL_NUMBER, number));

        setPosition(START);
//        processed();
    }

    private void createReal() {
        String number = String.valueOf(state.clearAccumulator());

        state.addResult(new Token(TokenType.REAL_NUMBER, number));

        setPosition(START);
//        processed();
    }

    private void createOperator() {
        Token t = operatorsTable.getToken(state.clearAccumulator());
        state.addResult(t);

        setPosition(START);
//        processed();
    }

    private void createDelimiter() {
        state.accumulate();
        Token t = delimitersTable.getToken(state.clearAccumulator());
        state.addResult(t);

        setPosition(START);
        processed();
    }

    private void createString() {
        String string = state.clearAccumulator();
        state.addResult(new Token(TokenType.STRING, string));

        setPosition(START);
//        processed();
    }

    private void checkSuccess() {
        if (state.clearAccumulator().isEmpty()) {
            state.setSuccess(true);
            setPosition(FINISHED);
        } else {
            setPosition(UNEXPECTED_END_OF_FILE_ERROR);
        }
    }

    private void finished() {
        state.setFinished(true);
    }

    // ============================== ERROR POSITIONS ===============================

    private void identifierError() {
        LexicParseError error = new IdentifierError("Wrong identifier", state.getPositionIndex());
        state.setError(error);

        setPosition(FINISHED);
    }

    private void operatorError() {
        LexicParseError error = new OperatorError("Operator not found", state.getPositionIndex());
        state.setError(error);

        setPosition(FINISHED);
    }

    private void decimalError() {
        LexicParseError error = new DecimalError("Wrong decimal number", state.getPositionIndex());
        state.setError(error);

        setPosition(FINISHED);
    }

    private void realError() {
        LexicParseError error = new RealError("Wrong real number", state.getPositionIndex());
        state.setError(error);

        setPosition(FINISHED);
    }

    private void undefinedInput() {
        LexicParseError error = new UndefinedInputError("Undefined symbol input", state.getPositionIndex());
        state.setError(error);

        setPosition(FINISHED);
    }

    private void ioException() {
        LexicParseError error = new UndefinedInputError("IOException", 0);
        state.setError(error);

        setPosition(FINISHED);
    }

    private void undexpectedEndOfFile() {
        LexicParseError error = new UndexpectedEndOfFileError("Undexpected End Of File", state.getPositionIndex());

        state.setError(error);

        setPosition(FINISHED);
    }
}
