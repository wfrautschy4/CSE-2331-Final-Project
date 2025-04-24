//Define a class for BinaryTree that recreates the Components Library
class BinaryTree<T> {
    BinaryTree<T> left;
    BinaryTree<T> right;
    T data;
    boolean color;  //Black = 0, Red = 1

    public BinaryTree(T data){
        this.left = null;
        this.right = null;
        this.data = data;
        this.color = true; //Red by default
    }

    public T root(){
        return data;
    }

    public T replaceRoot(T x){
        T temp = this.data;
        this.data = x;
        return temp;
    }

    public void assemble(T root, BinaryTree<T> left, BinaryTree<T> right){
        this.data = root;
        this.left = left;
        this.right = right;
    }

    public T disassemble(BinaryTree<T> left, BinaryTree<T> right){
        left = this.left;
        right = this.right;
        T data = this.data;

        this.left = null;
        this.right = null;
        this.data = null;

        return data;
    }

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
}