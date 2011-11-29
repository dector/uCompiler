package ua.org.dector.ucompiler.tree;

import junit.framework.TestCase;

/**
 * @author dector
 */
public class TreeTest extends TestCase {
    public void testTree() throws Exception {
        Node operationNode;
        Node argument1Node;
        Node argument2Node;

        argument1Node = new Node(Arguments.A);
        argument2Node = new Node(Arguments.N);
        operationNode = new Node(Operations.ARRAY_ACCESS);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        argument2Node = operationNode;
        argument1Node = new Node(Arguments.B);
        operationNode = new Node(Operations.COMPARE_EQUALS);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        argument1Node = operationNode;
        argument2Node = new Node(Operations.RETURN);
        argument2Node.addChild(new Node(Arguments.N));
        operationNode = new Node(Operations.IF);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        argument2Node = operationNode;
        argument1Node = new Node(Operations.PRE_DECREMENT);
        argument1Node.addChild(new Node(Arguments.N));
        operationNode = new Node(Operations.CODE_BLOCK);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        argument1Node = operationNode;
        argument2Node = new Node(Arguments.N);
        operationNode = new Node(Operations.DO_WHILE);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        argument1Node = operationNode;
        argument2Node = new Node(Operations.RETURN);
        argument2Node.addChild(new Node(Arguments.NUMBER_0));
        operationNode = new Node(Operations.EMPTY);
        operationNode.addChild(argument1Node);
        operationNode.addChild(argument2Node);

        Tree tree = new Tree();
        tree.setRootNode(operationNode);

        System.out.println(tree);

        assertTrue(true);
    }
}
