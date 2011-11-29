package ua.org.dector.ucompiler.tree;

import static ua.org.dector.ucompiler.tree.TreeProperties.*;

/**
 * @author dector
 */
public enum Operations implements NodeValue {
    ARRAY_ACCESS, COMPARE_EQUALS, RETURN, PRE_DECREMENT, CODE_BLOCK, DO_WHILE, IF, EMPTY;

    @Override
    public String toString() {
        switch (this) {
            case ARRAY_ACCESS: return CHILD_VALUE + "[" + CHILD_VALUE + "]";
            case COMPARE_EQUALS: return CHILD_VALUE + " == " + CHILD_VALUE;
            case RETURN: return "return " + CHILD_VALUE + SEMICOLON;
            case PRE_DECREMENT: return "--" + CHILD_VALUE + SEMICOLON;
            case CODE_BLOCK: return "{\n" + CHILD_VALUE + "\n" + CHILD_VALUE + "\n}";
            case DO_WHILE: return "do\n" + CHILD_VALUE + "\nwhile (" + CHILD_VALUE + ")";
            case IF: return "if (" + CHILD_VALUE + ")\n" + CHILD_VALUE;
            case EMPTY: return CHILD_VALUE + ";\n" + CHILD_VALUE;
            default: return "";
        }
    }
}
