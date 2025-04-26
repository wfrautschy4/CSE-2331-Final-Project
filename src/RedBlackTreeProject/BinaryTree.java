package RedBlackTreeProject;
//Define a class for BinaryTree that recreates the Components Library
class BinaryTree<T> {

    BinaryTree<T> left;
    BinaryTree<T> right;
    T data;
    boolean color;  //Black = 0, Red = 1

    /**
     * Constructor for if it is a blank Tree
     */
    public BinaryTree(){
        this.createNewRep();
    }


    /**
     * Constructor to add root data to a blank Tree
     * 
     * @param data
     *      Data to add to the root Node
     */
    public BinaryTree(T data){
        this.createNewRep();
        this.data = data;
    }

    /**
     * Returns the root of the node without changing anythign
     * 
     * @return {T data of root}
     */
    public T root(){
        return data;
    }

    /**
     * Replace the root value and returns the old one.
     * 
     * @param x
     *      The data to add to the root
     * @return {#this.data}
     */
    public T replaceRoot(T x){
        T temp = this.data;
        this.data = x;
        return temp;
    }

    public BinaryTree<T> replaceRight(BinaryTree<T> right){

        // Save Data currently in Right Tree
        BinaryTree<T> temp = new BinaryTree<T>();
        if(this.right != null) temp.transferFrom(this.right);
        
        //Transfer Data into Right Node if its not null
        this.right = new BinaryTree<T>();
        if(right != null) this.right.transferFrom(right);
        
        return temp;
    }
    
    public BinaryTree<T> replaceLeft(BinaryTree<T> left){

        // Save Data in Left Tree
        BinaryTree<T> temp = new BinaryTree<T>();
        if(this.left != null) temp.transferFrom(this.left);

        //Transfer Data into Left Node if its not null
        this.left = new BinaryTree<T>();
        if(left != null) this.left.transferFrom(left);
        
        return temp;
    }

    /**
     * Assembles the tree with the given data. Replaces any old values
     * 
     * @param root
     *      Data to store in the root node
     * @param left
     *      BinaryTree to add to the left child
     * @param right
     *      BinaryTree to add to the right child
     */
    public void assemble(T root, BinaryTree<T> left, BinaryTree<T> right){
        // Initialize if null
        if (this.left == null) this.left = new BinaryTree<T>();
        if (this.right == null) this.left = new BinaryTree<T>();

        //Transfer Data if it is real
        if (left != null) this.left.transferFrom(left);
        if (right != null) this.right.transferFrom(right);

        this.data = root;
    }

    /**
     * Disassembles the tree and gives the data back to the user then clear the rest
     * 
     * @param left
     *      Blank Binary Tree to absorb the data from the other
     * @param right
     *      Blank Binary Tree to absorb the data from the other
     * @return
     */
    public T disassemble(BinaryTree<T> left, BinaryTree<T> right){
        //Initialize Children if not done
        if(this.left == null) this.left = new BinaryTree<T>();
        if(this.right == null) this.right = new BinaryTree<T>();
 
        //Transfer Children to Parameters
        left.transferFrom(this.left);
        right.transferFrom(this.right);

        //Alter Node Data
        T data = this.data;
        this.data = null;

        return data;
    }

    /**
     * Returns the given height of the tree
     * 
     * @return height(this)
     */
    public int height(){
        int leftHeight = 0;
        int rightHeight = 0;

        //Check the height of both children
        if (this.left != null){
            leftHeight += this.left.height() + 1;
        }
        if(this.right != null){
            rightHeight += this.right.height() + 1;
        }

        //Check the maximum of the two and return it
        return leftHeight >= rightHeight ? leftHeight : rightHeight;
    }

    /**
     * Returns the given size of the tree
     * 
     * @return size(tree)
     */
    public int size(){
        int size = 1;

        if(this.left != null){
            size += this.left.size();
        }
        if(this.right != null){
            size += this.right.size();
        }

        return size;
    }

    /**
     * Copies data from the source and adds to this Node. Then clears the source
     * 
     * @param source
     *      BinaryTree source to copy data from
     */
    public void transferFrom(BinaryTree<T> source){
        this.left = source.left;
        this.right = source.right;
        this.data = source.data;
        source.clear();
    }

    private void createNewRep(){
        this.left = null;
        this.right = null;
        this.data = null;
        this.color = true;
    }
    /**
     * Clears all local variables to the BinaryTree
     */
    public void clear(){
        this.createNewRep();
    }

    public BinaryTree<T> left(){
        return this.left;
    }

    public BinaryTree<T> right(){
        return this.right;
    }
}