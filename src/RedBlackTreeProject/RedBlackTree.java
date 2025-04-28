package RedBlackTreeProject;

import java.util.ArrayList;

class RedBlackTree<T extends Comparable<T>> {
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

        BinaryTree<T> parent = root.parent;

        //Separate the right child from tree
        right.transferFrom(root.right());
        leftroot.transferFrom(root);
        

        //Shift Child Node from right node to left one
        leftroot.replaceRight(right.left());
        
        //Assemble Right with leftroot as left child
        right.replaceLeft(leftroot);
        
        //Transfer data to root
        root.transferFrom(right);
        root.parent = parent;
        
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
        BinaryTree<T> parent = root.parent;
        BinaryTree<T> rightroot = new BinaryTree<T>();
        BinaryTree<T> left = new BinaryTree<T>();

        //Separate the left child from tree
        left.transferFrom(root.left());
        rightroot.transferFrom(root);
        

        //Shift Child Node from left node to right one
        rightroot.replaceLeft(left.right());
        
        //Assemble left with rightroot as right child
        rightroot.parent = root.parent;
        left.replaceRight(rightroot);
        
        //Transfer data to root
        root.transferFrom(left);
        root.parent = parent;
        
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        //Init Vars
        BinaryTree<T> left = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();
        boolean inTree = false;

        //Recursively Look for X
        if (t.height() > 0) {
            T root = t.disassemble(left, right);

            //Find which child to travel down
            if (root.compareTo(x) > 0) {
                inTree = isInTree(left, x);
            } else if (root.compareTo(x) < 0) {
                inTree = isInTree(right, x);
            } else {
                inTree = true;
            }

            //Reassemble Tree
            t.assemble(root, left, right);
        }
        return inTree;
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeMin(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        //Init Vars
        BinaryTree<T> left = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();

        T root = t.disassemble(left, right);
        T smallest = root;

        if (left.size() > 0) {
            //Follow left node
            smallest = removeMin(left);

            t.assemble(root, left, right);
        } else {
            t.transferFrom(right);
        }

        return smallest;
    }

    private static <T> int getBalancedFactor(BinaryTree<T> root){
        int leftHeight = root.left() == null ? -1 : root.left().height();
        int rightHeight = root.right() == null ? -1 : root.right().height();
        return rightHeight - leftHeight;
    }

    /*
    * Step 1: Recursively Climb down Tree
    * Step 2: Check Differences in Children's Heights
    *      abs(left.height() - right.height()) >= 2 is evident of a rotation
    * Step 3: Find Path of Steepest Descent
    * Step 4: Do according rotations
    * Step 5: Call balanceTree on children only if their height difference is > 1
    */
    public static <T> void balanceNode(BinaryTree<T> root){
        //Check if it is a leaf node
        if(root.height() != 0){
            BinaryTree<T> left = new BinaryTree<T>(); //Make variables for easier addressing
            BinaryTree<T> right = new BinaryTree<T>();
            left = root.left();
            right = root.right();

            //Recursively Climb Down Tree
            // if(left != null) balanceTree(left);
            // if(right != null) balanceTree(right);

            //Check differences in Children's Heights
            int balancedFactor = getBalancedFactor(root);
            if(Math.abs(balancedFactor) < 2) return; // Tree is balanced

            // Find Path of Steepest Descent (left.height != right.height)
            // Call Appropriate Rotations to Balance based on Path
            if(balancedFactor < 0){ //Left Heavy
                int leftBalancedFactor = getBalancedFactor(left);
                if (leftBalancedFactor <= 0){
                    rotateRight(root);  // (LL)
                } else {
                    rotateLeft(left);   // (LR)
                    rotateRight(root);
                }
            } else {
                int rightBalancedFactor = getBalancedFactor(right);
                if(rightBalancedFactor >= 0){
                    rotateLeft(root);   // (RR)
                } else {
                    rotateRight(right); // (RL)
                    rotateLeft(root);
                    
                }
            }       
            if(root.left() != null && getBalancedFactor(root.left()) >= 2) balanceNode(root.left());
            if(root.right() != null && getBalancedFactor(root.right()) >= 2) balanceNode(root.right());
        }
    }

    /**
     * Make Tree Abide by color rules for red/black tree
     * 
     * @param root
     *      Root of the given BinaryTree
     */
    public static <T> void reinstateColors(BinaryTree<T> root){

    }

    public static <T> BinaryTree<T> getUncle(BinaryTree<T> root){
        BinaryTree<T> uncle = new BinaryTree<T>();//True if uncle is on the right else left

        if(root.parent.parent.left == root.parent){
            uncle = root.parent.parent.right;
        }else if(root.parent.parent.right == root.parent){
            uncle = root.parent.parent.left;
        }

        return uncle;
    }

    /**
     * Checks the given tree to see if it is balanced and abides by the red/black rules when inserting.
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
    public static <T> void insertRedBlackValidity(BinaryTree<T> root){
        //If tree is just node, change root color to black and return
        if(root.color == true && (root.parent == null)){
            root.color = false;
            return;
        }
        if (!root.parent.color) {
            return; // parent is black, so no violation
        }
        BinaryTree<T> uncle = getUncle(root);
        BinaryTree<T> parent = root.parent;
        BinaryTree<T> grandparent = root.parent.parent;
        //If inserted and parent color is red then check the color of parent's sibling
        if(uncle != null && uncle.color){
            parent.color = false;
            uncle.color = false;
            grandparent.color = true;
            insertRedBlackValidity(grandparent);

        }else{
            if(parent.left == root && grandparent.left == parent){
                parent.color = false;
                grandparent.color = true; 
                rotateRight(grandparent);        
            }else if(parent.right == root && grandparent.left == parent) {
                root.color = false;
                grandparent.color = true; 
                rotateLeft(parent);
                rotateRight(grandparent);            
            }else if(parent.right == root && grandparent.right == parent){
                parent.color = false;
                grandparent.color = true; 
                rotateLeft(grandparent);
            }else if(parent.left == root && grandparent.right == parent){
                root.color = false;
                grandparent.color = true; 
                rotateRight(parent);
                rotateLeft(grandparent);
            }

                       
        }
        }
    
    /**
     * Checks the given tree to see if it is balanced and abides by the red/black rules when inserting.
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
    public static <T> void deleteRedBlackValidity(BinaryTree<T> root) {
        if (root == null) return;
    
        BinaryTree<T> parent = root.parent;
        while (root != null && parent != null && !root.color) {
            if (root == parent.left) {
                BinaryTree<T> sibling = parent.right;
    
                if (sibling == null) {
                    root = parent;
                    parent = root.parent;
                    continue;
                }
    
                if (sibling.color) {
                    sibling.color = false;
                    parent.color = true;
                    rotateLeft(parent);
                    sibling = parent.right;
                }
    
                if ((sibling.left == null || !sibling.left.color) &&
                    (sibling.right == null || !sibling.right.color)) {
                    sibling.color = true;
                    root = parent;
                    parent = root.parent;
                } else {
                    if (sibling.right == null || !sibling.right.color) {
                        if (sibling.left != null) sibling.left.color = false;
                        sibling.color = true;
                        rotateRight(sibling);
                        sibling = parent.right;
                    }
                    sibling.color = parent.color;
                    parent.color = false;
                    if (sibling.right != null) sibling.right.color = false;
                    rotateLeft(parent);
                    root = null; // Done
                }
            } else { // Symmetric
                BinaryTree<T> sibling = parent.left;
    
                if (sibling == null) {
                    root = parent;
                    parent = root.parent;
                    continue;
                }
    
                if (sibling.color) {
                    sibling.color = false;
                    parent.color = true;
                    rotateRight(parent);
                    sibling = parent.left;
                }
    
                if ((sibling.left == null || !sibling.left.color) &&
                    (sibling.right == null || !sibling.right.color)) {
                    sibling.color = true;
                    root = parent;
                    parent = root.parent;
                } else {
                    if (sibling.left == null || !sibling.left.color) {
                        if (sibling.right != null) sibling.right.color = false;
                        sibling.color = true;
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }
                    sibling.color = parent.color;
                    parent.color = false;
                    if (sibling.left != null) sibling.left.color = false;
                    rotateRight(parent);
                    root = null; // Done
                }
            }
        }
    
        if (root != null) {
            root.color = false; // Ensure root is black
        }
    }
    

     /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        BinaryTree<T> current = t;

        while (current.data != null) { 
            if (x.compareTo(current.data) <= 0) {
                if (current.left == null) {
                    current.left = new BinaryTree<T>();
                    current.left.parent = current; // maintain parent link
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new BinaryTree<T>();
                    current.right.parent = current; // maintain parent link
                }
                current = current.right;
            }
        }   
        current.color = true;
        current.data = x;
        current.left = null;
        current.right = null;

        insertRedBlackValidity(current);
    }

     /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    public static <T extends Comparable<T>> void removeFromTree(BinaryTree<T> t, T x) {
        BinaryTree<T> current = t;
        
        while (current != null && current.height() > 0) {
            T rootData = current.data;
            BinaryTree<T> left = current.left();
            BinaryTree<T> right = current.right();
            System.out.println("3");
            if (rootData.equals(x)) {
                System.out.println("4");
                if ((left == null || left.height() == 0) && (right == null || right.height() == 0)) {
                    System.out.println("Case 1");
                    if (!current.color) deleteRedBlackValidity(current);
                    current = null;
                    return;
                } else if (left == null || left.height() == 0) {
                    if (right != null) right.parent = current.parent;
                    current.transferFrom(right);
                    return;
                } else if (right == null || right.height() == 0) {
                    if (left != null) left.parent = current.parent;
                    current.transferFrom(left);
                    return;
                } else {
                    // Find successor
                    BinaryTree<T> successor = right;
                    while (successor.left() != null && successor.left().height() > 0)
                        successor = successor.left();
                    
                    boolean succWasBlack = !successor.color;
                    current.data = successor.data;
    
                    removeFromTree(right, successor.data); // delete successor in right subtree
    
                    if (succWasBlack) deleteRedBlackValidity(successor);
                    return;
                }
            } else if (x.compareTo(current.root()) < 0) {
                current = current.left();
            } else {
                current = current.right();
            }
            
        }
    }
    
    
        
    


    static <T> void traverseInOrder(BinaryTree<T> t, ArrayList<T> list){
        BinaryTree<T> left = new BinaryTree<T>();
        BinaryTree<T> right = new BinaryTree<T>();

        if (t.size() > 0){ //Not an empty node
            T root = t.disassemble(left, right);
            traverseInOrder(left, list);
            list.add(root);
            traverseInOrder(right, list);
        }
    }

     // ------------ Setup Functions ------------ //   

     private BinaryTree<T> t;
    
     /**
      *  Constructor for the class.
      */
     public RedBlackTree() {
         //Initialize a Blank Tree
         this.t = new BinaryTree<T>();
     }
 
    // ------------ Non-Static Functions ------------ //

    public void insert(T data){
        insertInTree(this.t, data);
    }

    public void delete(T data){
         removeFromTree(this.t, data);
    }

    public boolean contains(T data){
        return isInTree(this.t, data);
    }

    public ArrayList<T> getList(){
        //Print out data in sorted order
        ArrayList<T> list = new ArrayList<T>();
        traverseInOrder(this.t, list);
        return list;

    }

    public BinaryTree<T> getTree(){
        return this.t;
    }
    public int size(){
        return this.t.size();
    }
    public int height(){
        return this.t.height();
    }
    public void clear(){
        this.t.clear();
    }
}