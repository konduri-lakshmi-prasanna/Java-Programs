/**
 * Program Title: Red-Black Tree (Insertion)
 * Author: v-technoid
 * Date: 2025-10-13
 *
 * Description: A self-balancing binary search tree implementation that supports O(log n) insertion 
 * while maintaining balance using color-flipping and rotations.
 *
 * Language: Java
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(n)
 */

import java.util.Scanner;

/**
 * Red-Black Tree Properties:
 * 1. Every node is either RED or BLACK.
 * 2. The root node is always BLACK.
 * 3. There are no two adjacent RED nodes (a RED node cannot have a RED parent or a RED child).
 * 4. Every path from a node to any of its null descendants has the same number of BLACK nodes.
 * 5. All null leaves are considered BLACK.
 */

// Enum to represent the two possible colors for a node
enum Color {
    RED, BLACK
}

// Represents a single node in the Red-Black Tree
class Node {
    int data;
    Node left, right, parent;
    Color color;

    Node(int data) {
        this.data = data;
        this.color = Color.RED; // New nodes are always RED initially
    }
}

public class RedBlackTree {
    private Node root;

    /**
     * Public method to insert a new value into the tree
     * Time Complexity: O(log n)
     */
    public void insert(int data) {
        Node newNode = new Node(data);
        root = bstInsert(root, newNode);
        fixViolation(newNode);
    }

    // A standard recursive BST insert that also sets the parent pointer
    private Node bstInsert(Node root, Node node) {
        if (root == null) return node;

        if (node.data < root.data) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    // Fixes the tree to restore Red-Black properties after insertion
    private void fixViolation(Node node) {
        Node parent = null;
        Node grandParent = null;

        while (node != root && node.color == Color.RED && node.parent.color == Color.RED) {
            parent = node.parent;
            grandParent = parent.parent;

            // Case A: Parent is the LEFT child of the grandparent
            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                if (uncle != null && uncle.color == Color.RED) {
                    grandParent.color = Color.RED;
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node = grandParent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandParent);
                    Color temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    break;
                }
            } else { // Case B: Parent is the RIGHT child (mirror of Case A)
                Node uncle = grandParent.left;
                if (uncle != null && uncle.color == Color.RED) {
                    grandParent.color = Color.RED;
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandParent);
                    Color temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    break;
                }
            }
        }
        if (root != null) {
            root.color = Color.BLACK;
        }
    }

    /**
     * Helper method to perform a left rotation
     * Time Complexity: O(1)
     */
    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            this.root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    /**
     * Helper method to perform a right rotation
     * Time Complexity: O(1)
     */
    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            this.root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        
        leftChild.right = node;
        node.parent = leftChild;
    }

    // Utility method to print the tree in-order (sorted)
    public void inOrderTraversal() {
        inOrderHelper(this.root);
        System.out.println();
    }

    private void inOrderHelper(Node node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.data + "(" + node.color + ") ");
            inOrderHelper(node.right);
        }
    }

    /**
     * The main method handles user input and runs the program
     */
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numbers to insert into the Red-Black Tree.");
        System.out.println("Type 'done' when you are finished.");

        while (true) {
            System.out.print("Enter a number: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int number = Integer.parseInt(input);
                rbt.insert(number);
                System.out.print("Current tree (in-order): ");
                rbt.inOrderTraversal();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'done'.");
            }
        }

        System.out.println("\nFinal Red-Black Tree (in-order traversal):");
        rbt.inOrderTraversal();
        
        scanner.close();
    }
}
