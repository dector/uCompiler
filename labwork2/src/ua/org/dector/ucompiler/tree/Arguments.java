package ua.org.dector.ucompiler.tree;

/**
 * @author dector
 */
public enum Arguments implements NodeValue {
    A, B, N, NUMBER_0;

    @Override
    public String toString() {
        switch (this) {
            case A: return "a";
            case B: return "b";
            case N: return "n";
            case NUMBER_0: return "0";
            default: return "";
        }
    }
}
