package RedBlackTreeProject;

class test {

    static void printInorder(BinaryTree<Integer> node) {
        if (node == null)
            return;

        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public static void main(String args[]){
        BinaryTree<Integer> bt = new BinaryTree<Integer>();
        BinaryTree<Integer> left = new BinaryTree<Integer>(3);
        BinaryTree<Integer> right = new BinaryTree<Integer>(2);

        BinaryTree<Integer> leftleft = new BinaryTree<Integer>(10);
        BinaryTree<Integer> leftright = new BinaryTree<Integer>(1);

        BinaryTree<Integer> rightleft = new BinaryTree<Integer>(7);
        BinaryTree<Integer> rightright = new BinaryTree<Integer>(12);

        left.assemble(3, leftleft, leftright);
        right.assemble(2, rightleft, rightright);
        bt.assemble(5, left, right);
        
        TreePrinter.printTree(bt);

        System.out.println("Rotating Right");
        RedBlackTree.rotateRight(bt.left());
        TreePrinter.printTree(bt);
        System.out.println("Height: "+ bt.height());
        System.out.println("Size: "+ bt.size());

        System.out.println("Rotating Left");
        RedBlackTree.rotateLeft(bt.left());
        TreePrinter.printTree(bt);
        System.out.println("Height: "+ bt.height());
        System.out.println("Size: "+ bt.size());

        
        
    }
}