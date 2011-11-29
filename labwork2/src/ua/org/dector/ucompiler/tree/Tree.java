package ua.org.dector.ucompiler.tree;

/**
 * @author dector
 */
public class Tree {
    private Node rootNode;

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public String toString() {
        return rootNode.toString();
    }
}
