package RedBlackTreeProject;

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
    public static <T> void rotateLeft(BinaryTree<T> root){
        if(root.height() == 0 || root.right == null) System.out.println("ERROR: Invalid tree rotating left");

        //Initialize Temporary Objects to store and manipulate
        BinaryTree<T> leftroot = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();

        //Separate the right child from tree
        right.transferFrom(root.right());
        leftroot.transferFrom(root);

        //Shift Child Node from right node to left one
        leftroot.replaceRight(right.left());
        
        
        //Assemble Right with leftroot as left child
        right.replaceLeft(leftroot);
        
        //Transfer data to root
        root.transferFrom(right);
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
    public static <T> void rotateRight(BinaryTree<T> root){
        if(root.height() == 0 || root.left == null) System.out.println("ERROR: Invalid tree rotating right");

        //Initialize Temporary Objects to store and manipulate
        BinaryTree<T> rightroot = new BinaryTree<T>();
        BinaryTree<T> left = new BinaryTree<T>();

        //Separate the left child from tree
        left.transferFrom(root.left());
        rightroot.transferFrom(root);

        //Shift Child Node from left node to right one
        rightroot.replaceLeft(left.right());
        
        
        //Assemble left with rightroot as right child
        left.replaceRight(rightroot);
        
        //Transfer data to root
        root.transferFrom(left);
    }

    /**
     * Make Tree Abide by color rules for red/black tree
     * 
     * @param root
     *      Root of the given BinaryTree
     */
    public static <T> void reinstateColors(BinaryTree<T> root){

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
    public static <T> boolean checkValidity(BinaryTree<T> root){


        return false;
    }



    // ------------ Non-Static Functions ------------ //

    public void insert(T data){

    }

    public void delete(T data){

    }

    public void search(T data){

    }

    public void traverse(){

    }
}