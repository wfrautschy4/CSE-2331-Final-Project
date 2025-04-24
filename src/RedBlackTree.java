import BinaryTree.java;

class RedBlackTree<T> {

    


    // ------------ Setup Functions ------------ //   

    private BinaryTree<T> t;
    
    /**
     *  Constructor for the class.
     */
    public RedBlackTree() {
        //Initialize a Blank Tree
        this.t = new BinaryTree<T>();
    }



    // ---------------- Static Functions ------------ //

    /**
     * Rotates the given tree and nodes left.
     * 
     * @param root
     *      The root of the BinaryTree
     * 
     * @requires {root.height > 0 && root.right != NULL}
     * @ensures {root.left = #root && 
     *           root = #root.right && 
     *           root.left.right = #root.right.left }
     */
    private static void rotateLeft(BinaryTree root){

    }


     /**
     * Rotates the given tree and nodes left.
     * 
     * @param root
     *      The root of the BinaryTree.
     * 
     * @requires {root.height > 0 && root.left != NULL}
     * @ensures {root.right = #root && 
     *           root = #root.left && 
     *           root.left.right = #root.right.left }
     */
    private static void rotateRight(BinaryTree root){
        
    }

    /**
     * Make Tree Abide by color rules for red/black tree
     * 
     * @param root
     *      Root of the given BinaryTree
     */
    static void reinstateColors(BinaryTree root){

    }

    /**
     * Checks the given tree to see if it is balanced and abides by the red/black rules.
     * 
     * . Every node is either red or black;
     * . The root is black;
     * . Every leaf is NIL and is black;
     * . If a node is red, then both its children are black;
     * . All simple paths from the root to any leaf contain the same number of black nodes.
     *   (Note: Every node in a binary tree is either a leaf or has BOTH a left AND right child.)
     * 
     * @param root
     *      The root of the BinaryTree
     * @return {Yes if the given BinaryTree is a valid red/black tree}
     */
    static boolean checkValidity(BinaryTree root){

    }



    // ------------ Non-Static Functions ------------ //

    public void insert(Object data){

    }

    public void delete(Object data){

    }

    public void search(Object data){

    }

    public void traverse(){

    }
}