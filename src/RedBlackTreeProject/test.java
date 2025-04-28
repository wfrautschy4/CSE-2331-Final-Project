package RedBlackTreeProject;

import java.util.ArrayList;

class test {



    public static void main(String args[]){
        testDelete();
        
        
    }


public static void testDelete(){
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    //Insert all the elements
    int numElem = 20;
    for (int i = 0; i < numElem; i++) {
        int randomNum = (int) Math.floor(Math.random() * numElem + 1);
        rbt.insert(randomNum);

    }
    //Print state of the world
    TreePrinter.printTree(rbt.getTree());
   

    ArrayList<Integer> list = rbt.getList();
    
    //Remove an element
    System.out.println("Removing: "+ list.get(15));
    rbt.delete(list.get(15));
    TreePrinter.printTree(rbt.getTree());

}



public static void timeInsert(){
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    int datapoints = 6;
        for(int j = 1; j < datapoints * Math.pow(10,datapoints); j=j*10){
            //Insert all the elements
            long start = System.nanoTime();
            for (int i = 0; i < j; i++) {
                int randomNum = (int) Math.floor(Math.random() * j + 1);
                rbt.insert(randomNum);
            }
            long end = System.nanoTime();
            System.out.println("Average time for ("+j+") insertions: " + (end - start) / 1000 + " ns");
            rbt.clear();
        }
}

public static void timeSearch(){
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    int datapoints = 8;
    for(int j = 1; j < datapoints * Math.pow(10,datapoints); j=j*10){
        //Insert all the elements
        for (int i = 0; i < j; i++) {
            int randomNum = (int) Math.floor(Math.random() * j + 1);
            rbt.insert(randomNum);
        }

        //Get list of all elements
        ArrayList<Integer> list = rbt.getList();

        //Search each element
        long start = System.nanoTime();
        for (int i = 0; i < j; i++){
            rbt.contains(list.get(i));
        }
        long end = System.nanoTime();
            System.out.println("Average time for ("+j+") searches: " + (end - start) / 1000 + " ns");
        rbt.clear();
    }
}
        
        
        



        

    
}