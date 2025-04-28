package RedBlackTreeProject;

import java.util.ArrayList;

class test {



    public static void main(String args[]){
        timeDelete();
        
        
    }


public static void testDelete(){
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    //Insert all the elements
    int numElem = 20;
    for (int i = 0; i < numElem; i++) {
        int randomNum = (int) Math.floor(Math.random() * 99 + 1);
        rbt.insert(randomNum);

    }
    //Print state of the world
    TreePrinter.printTree(rbt.getTree());
   

    ArrayList<Integer> list = rbt.getList();
    
    //Remove an element
    for(int i = 0; i < rbt.size() * 2; i++){
        System.out.println("Removing: "+ list.get(i));
        rbt.delete(list.get(i));
        TreePrinter.printTree(rbt.getTree());
        System.out.println("------------------------");
    }
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
        
        
public static void timeDelete(){
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
    int datapoints = 7;
    for(int j = 1; j < datapoints * Math.pow(10,datapoints); j=j*10){
        //Insert all the elements
        for (int i = 0; i < j; i++) {
            int randomNum = (int) Math.floor(Math.random() * j + 1);
            rbt.insert(randomNum);
        }

        //Get list of all elements
        ArrayList<Integer> list = rbt.getList();

        //Delete each element
        long start = System.nanoTime();
        for (int i = 0; i < j; i++){
            rbt.delete(list.get(i));
        }
        long end = System.nanoTime();
            System.out.println("Average time for ("+j+") deletions: " + (end - start) / 1000 + " ns");
        rbt.clear();
    }
}
        



        

    
}