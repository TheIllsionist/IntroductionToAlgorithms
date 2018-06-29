package Test.Chapter12_BinarySearchTree;

import Chapter12_BinarySearchTree.BinarySearchTree;

public class BinarySearchTreeTest {
    public static void main(String args[]) throws Exception{
        BinarySearchTree node7 = new BinarySearchTree(7);
        BinarySearchTree node1 = new BinarySearchTree(1);
        BinarySearchTree node2 = new BinarySearchTree(2);
        BinarySearchTree node3 = new BinarySearchTree(3);
        BinarySearchTree node4 = new BinarySearchTree(4);
        BinarySearchTree node5 = new BinarySearchTree(5);
        BinarySearchTree node6 = new BinarySearchTree(6);
        BinarySearchTree node8 = new BinarySearchTree(8);
        BinarySearchTree node9 = new BinarySearchTree(9);
        BinarySearchTree node10 = new BinarySearchTree(10);
        node2.setLeftChild(node1);
        node2.setRightChild(node3);

        node5.setRightChild(node6);

        node4.setLeftChild(node2);
        node4.setRightChild(node5);

        node9.setLeftChild(node8);

        node10.setLeftChild(node9);

        node7.setLeftChild(node4);
        node7.setRightChild(node10);

        node7.inOrderTreeWork();
        System.out.println();
        node7.preOrderTreeWork();
        System.out.println();
        node7.postOrderTreeWord();
    }

}
