package ua.org.dector.ucompiler.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static ua.org.dector.ucompiler.tree.TreeProperties.*;

/**
 * @author dector
 */
public class Node {
    private Node parent;
    private List<Node> children;
    private NodeValue value;

    private Node() {
        children = new LinkedList<Node>();
    }

    public Node(NodeValue value) {
        this();
        setValue(value);
    }

    public void addChild(Node childNode) {
        children.add(childNode);
    }

    public Node getFirstChild() {
        if (! children.isEmpty()) {
            return children.get(0);
        } else {
            return null;
        }
    }

    public Node getLastChild() {
        if (! children.isEmpty()) {
            return children.get(children.size() - 1);
        } else {
            return null;
        }
    }

    public NodeValue getValue() {
        return value;
    }

    public void setValue(NodeValue value) {
        this.value = value;
    }

    public Iterator<Node> getChildrenIterator() {
        return children.iterator();
    }

    public String toString() {

        if (hasChildren()) {
            String newValue = value.toString();

            for (Node child : children) {
                newValue = newValue.replaceFirst(CHILD_VALUE, child.toString());
            }

            return newValue;
        } else {
            return value.toString();
        }
    }

    public boolean hasChildren() {
        return ! children.isEmpty();
    }
}
