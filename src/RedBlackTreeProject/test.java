package RedBlackTreeProject;

class test {

    static void printInorder(BinaryTree<Integer> node) {
        if (node == null)
            return;

        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    static void BSTinsert(BinaryTree<Integer> t, int x){
        if(t.root() == null){
            t.replaceRoot(x);
            return;
        }

        int cmp = x - t.root();
        if (cmp < 0) {
            if (t.left() == null) {
                t.replaceLeft(new BinaryTree<Integer>(x));
            } else {
                BSTinsert(t.left(), x);
            }
        } else if (cmp > 0) {
            if (t.right() == null) {
                t.replaceRight(new BinaryTree<Integer>(x));
            } else {
                BSTinsert(t.right(), x);
            }
        }
        
    }    

    static BinaryTree<Integer> generateRandomTree(int size){
        BinaryTree<Integer> root = new BinaryTree<Integer>();

        for (int i = 0; i < size; i++){
            int randomNum = (int) Math.floor(Math.random() * 99 + 1);
            BSTinsert(root, randomNum);
        }
        return root;
    }

    public static void main(String args[]){
        // BinaryTree<Integer> bt = new BinaryTree<Integer>();
        // BinaryTree<Integer> left = new BinaryTree<Integer>(3);
        // BinaryTree<Integer> right = new BinaryTree<Integer>(2);

        // BinaryTree<Integer> leftleft = new BinaryTree<Integer>(10);
        // BinaryTree<Integer> leftright = new BinaryTree<Integer>(1);

        // BinaryTree<Integer> rightleft = new BinaryTree<Integer>(7);
        // BinaryTree<Integer> rightright = new BinaryTree<Integer>(12);

        // left.assemble(3, leftleft, leftright);
        // right.assemble(2, rightleft, rightright);
        // bt.assemble(5, left, right);

        BinaryTree<Integer> bt = generateRandomTree(15);
        
        TreePrinter.printTree(bt);
        System.out.println("Height: "+ bt.height());
        System.out.println("Size: "+ bt.size());

        System.out.println("Rebalancing");
        RedBlackTree.balanceTree(bt);
        TreePrinter.printTree(bt);
        System.out.println("Height: "+ bt.height());
        System.out.println("Size: "+ bt.size());

        // System.out.println("Rotating Right");
        // RedBlackTree.rotateRight(bt);
        // TreePrinter.printTree(bt);
        // System.out.println("Height: "+ bt.height());
        // System.out.println("Size: "+ bt.size());

        // System.out.println("Rotating Right");
        // RedBlackTree.rotateRight(bt);
        // TreePrinter.printTree(bt);
        // System.out.println("Height: "+ bt.height());
        // System.out.println("Size: "+ bt.size());

        

        // System.out.println("Rotating Left");
        // RedBlackTree.rotateLeft(bt.left());
        // TreePrinter.printTree(bt);
        // System.out.println("Height: "+ bt.height());
        // System.out.println("Size: "+ bt.size());

        
        
    }
}