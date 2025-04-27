package RedBlackTreeProject;
import java.util.*;

public class TreePrinter {

    // ANSI escape codes for coloring
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    // Public method to call
    public static <T extends Comparable<T>> void printTree(BinaryTree<T> root) {
        System.out.println("\n"); //Add Padding
        printTree(root, "", true);
        System.out.println("\n"); //Add Padding
    }

    // Recursive helper
    private static <T extends Comparable<T>> void printTree(BinaryTree<T> node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }
        // Print right child first (so it visually goes upward)
        if (node.right != null) {
            printTree(node.right, prefix + (isTail ? "│   " : "    "), false);
        }

        // Print current node
        System.out.print(prefix + (isTail ? "└── " : "┌── "));
        if (node.color) { // assuming true = RED, false = BLACK
            System.out.println(RED + node.data + RESET);
        } else {
            System.out.println(node.data);
        }

        // Print left child
        if (node.left != null) {
            printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }
}


// public class TreePrinter {
//     public static <T> void printTree(BinaryTree<T> root) {
//         int maxLevel = maxDepth(root);
//         printNodeInternal(Collections.singletonList(root), 1, maxLevel);
//     }

//     private static <T> void printNodeInternal(List<BinaryTree<T>> nodes, int level, int maxLevel) {
//         if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

//         int floor = maxLevel - level;
//         int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
//         int firstSpaces = (int) Math.pow(2, floor) - 1;
//         int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

//         printWhitespaces(firstSpaces);

//         List<BinaryTree<T>> newNodes = new ArrayList<>();
//         for (BinaryTree<T> node : nodes) {
//             if (node != null) {
//                 System.out.print(node.root());
//                 newNodes.add(node.left);
//                 newNodes.add(node.right);
//             } else {
//                 System.out.print(" ");
//                 newNodes.add(null);
//                 newNodes.add(null);
//             }

//             printWhitespaces(betweenSpaces);
//         }
//         System.out.println();

//         for (int i = 1; i <= edgeLines; i++) {
//             for (int j = 0; j < nodes.size(); j++) {
//                 printWhitespaces(firstSpaces - i);
//                 if (nodes.get(j) == null) {
//                     printWhitespaces(edgeLines + edgeLines + i + 1);
//                     continue;
//                 }

//                 if (nodes.get(j).left != null) System.out.print("/");
//                 else printWhitespaces(1);

//                 printWhitespaces(i + i - 1);

//                 if (nodes.get(j).right != null) System.out.print("\\");
//                 else printWhitespaces(1);

//                 printWhitespaces(edgeLines + edgeLines - i);
//             }
//             System.out.println();
//         }

//         printNodeInternal(newNodes, level + 1, maxLevel);
//     }

//     private static void printWhitespaces(int count) {
//         for (int i = 0; i < count; i++) System.out.print(" ");
//     }

//     private static <T> int maxDepth(BinaryTree<T> node) {
//         if (node == null) return 0;
//         return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
//     }

//     private static <T> boolean isAllElementsNull(List<BinaryTree<T>> list) {
//         for (BinaryTree<T> node : list) {
//             if (node != null) return false;
//         }
//         return true;
//     }
// }
