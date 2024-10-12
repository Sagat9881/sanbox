package ru.apzakharov.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        GenerateParenthesis gP = new GenerateParenthesis();
        List<String> strings = gP.generateParenthesis(3);
        strings.forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        ParenthesisTree parenthesisTree = new ParenthesisTree(n);

        return parenthesisTree.leafToList();
    }

    public static boolean balanceParenthesis(char[] source, int treeDepth) {
        int counter = 0;
        for (char p : source) {
            if (p == '(') ++counter;
            if (p == ')') --counter;
            if (counter < 0) return false;
            treeDepth--;
        }

        return counter - treeDepth <= 0;
    }


    static class ParenthesisTree {

        final Node root;

        public ParenthesisTree(int n) {
            this.root = new Node(new char[]{'('});
            buildRecursive(root, n, 2);
        }


        public List<String> leafToList() {
            List<Node> leafs = new ArrayList<>();
            leafIntoListRecursive(leafs, root);
            return leafs.stream()
                    .map(Node::getValue)
                    .map(String::valueOf)
                    .toList();
        }

        private void leafIntoListRecursive(List<Node> leaf, Node node) {
            if (node == null) return;
            if ((node.left == null && node.right == null)) {
                leaf.add(node);
                return;
            }
            if (node.left != null) leafIntoListRecursive(leaf, node.left);
            if (node.right != null) leafIntoListRecursive(leaf, node.right);
        }


        private void buildRecursive(Node node, int n, int i) {
            if (i > n * 2) {
                return;
            }
            char[] leftArray = new char[i];
            char[] rightArray = new char[i];

            System.arraycopy(node.value, 0, leftArray, 0, node.value.length);
            System.arraycopy(node.value, 0, rightArray, 0, node.value.length);

            leftArray[i - 1] = '(';
            rightArray[i - 1] = ')';

            if (balanceParenthesis(rightArray, n * 2)) {
                Node right = new Node(rightArray);
                node.right = right;
                buildRecursive(right, n, i + 1);
            }
            if (balanceParenthesis(leftArray, n * 2)) {
                Node left = new Node(leftArray);
                node.left = left;
                buildRecursive(left, n, i + 1);
            }
        }


        private static class Node {
            Node left;
            Node right;

            public char[] getValue() {
                return value;
            }

            final char[] value;

            private Node(char[] value) {
                this.value = value;
            }
        }
    }


}
