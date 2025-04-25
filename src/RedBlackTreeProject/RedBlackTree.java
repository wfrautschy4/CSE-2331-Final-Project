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

    private static <T> T removeMin(BinaryTree<T> t){
        BinaryTree<T> left = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();
        T root = t.disassemble(left, right);
        T min = root; 

        if(left != null){
            min = removeMin(left);

            //Only Reassemble if its not a leaf node to remove it
            t.assemble(root, left, right);
        } 
        return min;
    }

    private static <T> T removeMax(BinaryTree<T> t){
        BinaryTree<T> left = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();
        T root = t.disassemble(left, right);
        T max = root; 

        if(right != null){
            max = removeMax(right);

            //Only Reassemble if its not a leaf node to remove it
            t.assemble(root, left, right);
        } 
        return max;
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

    private static <T> int getBalancedFactor(BinaryTree<T> root){
        int leftHeight = root.left() == null ? -1 : root.left().height();
        int rightHeight = root.right() == null ? -1 : root.right().height();
        return rightHeight - leftHeight;
    }

    public static <T> void balanceTree(BinaryTree<T> root){
        /*
         * Step 1: Recursively Climb down Tree
         * Step 2: Check Differences in Children's Heights
         *      abs(left.height() - right.height()) >= 2 is evident of a rotation
         * Step 3: Find Path of Steepest Descent
         * Step 4: Do according rotations
         * Step 5: Call balanceTree on children only if their height difference is > 1
         */
        
        //Check if it is a leaf node
        if(root.height() != 0){
            BinaryTree<T> left = new BinaryTree<T>(); //Make variables for easier addressing
            BinaryTree<T> right = new BinaryTree<T>();
            left = root.left();
            right = root.right();

            //Recursively Climb Down Tree
            if(left != null) balanceTree(left);
            if(right != null) balanceTree(right);

            //Check differences in Children's Heights
            int balancedFactor = getBalancedFactor(root);
            if(Math.abs(balancedFactor) < 2) return; // Tree is balanced

            // System.out.println("---------------------------------------");
            // Find Path of Steepest Descent (left.height != right.height)
            // Call Appropriate Rotations to Balance based on Path
            if(balancedFactor < 0){ //Left Heavy
                int leftBalancedFactor = getBalancedFactor(left);
                if (leftBalancedFactor <= 0){
                    // System.out.println("Right Rotation Performed on: "+ root.root());
                    rotateRight(root);  // (LL)
                } else {
                    // System.out.println("Left-Right Rotation Performed on: "+ left.root()+ " and "+ root.root());
                    rotateLeft(left);   // (LR)
                    rotateRight(root);
                }
            } else {
                int rightBalancedFactor = getBalancedFactor(right);
                if(rightBalancedFactor >= 0){
                    // System.out.println("Left Rotation Performed on: "+ root.root());
                    rotateLeft(root);   // (RR)
                } else {
                    // System.out.println("Right-Left Rotation Performed on: "+ right.root()+ " and "+ root.root());
                    rotateRight(right); // (RL)
                    rotateLeft(root);
                    
                }
            }

            // //Check if Children need to be Rebalanced
            // System.out.println("Root BalancedFactor: "+getBalancedFactor(root));
            // TreePrinter.printTree(root);
            
            // if(root.left() != null) {
            //     System.out.println("Left BalancedFactor: "+getBalancedFactor(root.left()));
            //     TreePrinter.printTree(root.left());
            // }
            // if(root.right() != null) {
            //     System.out.println("Right BalancedFactor: "+getBalancedFactor(root.right()));
            //     TreePrinter.printTree(root.right());
            
       
            if(root.left() != null && getBalancedFactor(root.left()) >= 2) balanceTree(root.left());
            if(root.right() != null && getBalancedFactor(root.right()) >= 2) balanceTree(root.right());
        }
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