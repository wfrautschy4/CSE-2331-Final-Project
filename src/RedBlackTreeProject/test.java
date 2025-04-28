package RedBlackTreeProject;

class test {



    public static void main(String args[]){
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        //Insert n elements into the tree and print each step
        int n = 10;
        rbt.insert(10);
        rbt.insert(100);
        rbt.insert(50);
        rbt.insert(20);
        rbt.insert(90);
        rbt.insert(150);
        rbt.insert(12);
        rbt.insert(15);
        /* for(int i = 1; i <= n; i++){
            int randomNum = (int) Math.floor(Math.random() * 99 + 1);
            rbt.insert(randomNum);
            
            //Print it
            rbt.getTree().verifyParentPointers();
            TreePrinter.printTree(rbt.getTree());
            System.out.println("----------------------------------");
            TreePrinter.printTree(rbt.getTree());
        } */
        System.out.println("----------------------------------");
        TreePrinter.printTree(rbt.getTree());


        rbt.delete(15);

        System.out.println("----------------------------------");
        TreePrinter.printTree(rbt.getTree());

    }
}