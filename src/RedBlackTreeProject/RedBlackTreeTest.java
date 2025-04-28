import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import RedBlackTreeProject.RedBlackTree;
import RedBlackTreeProject.BinaryTree;

public class RedBlackTreeTest {

    // ---------- INSERTION TESTS ---------- //

    @Test
    public void testInsertSingleValue() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        assertTrue(tree.contains(10));
    }

    @Test
    public void testInsertMultipleValues() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
    }

    @Test
    public void testInsertNegativeValues() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(-10);
        tree.insert(0);
        tree.insert(-20);
        assertTrue(tree.contains(-10));
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(-20));
    }

    // ---------- DELETION TESTS ---------- //

    @Test
    public void testDeleteLeafNode() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.delete(5);
        assertFalse(tree.contains(5));
        assertTrue(tree.contains(10));
    }

    @Test
    public
