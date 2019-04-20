package SnakeLogic;

import java.awt.*;
import java.util.ArrayList;

public class Tree<T extends TreeItem> {


    private T rootNode;
    private int numOfNodes;
    private ArrayList<T> nodes;


    public Tree() {


        numOfNodes = 0;
        nodes = new ArrayList<>();

    }

    public int getSize(){return numOfNodes;}


    /*
    public MathVector get(int index){

        if(rootNode != null) {
            if (rootNode.nodeIndex == index) {
                return rootNode.pos;
            } else {
                getChildNode(rootNode, index);
            }
        }
        return null;
    }

    public MathVector getChildNode(T rootNode, int index){

        if(rootNode.leftChild != null) {
            if (rootNode.leftChild.nodeIndex == index) {
                return rootNode.leftChild.pos;
            } else{
                getChildNode(rootNode.leftChild, index);
            }
        }else if(rootNode.rightChild != null){
            if(rootNode.rightChild.nodeIndex == index){
                return rootNode.rightChild.pos;
            } else{
                getChildNode(rootNode.rightChild, index);
            }
        }


        return null;
    }
*/

    public void emptyTree(){


        if(rootNode != null){
            rootNode = null;
            numOfNodes = 0;
        }


    }


    public boolean add(T node) {

        if(rootNode == null){
            //  node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            rootNode = node;
        }else{
          //  System.out.println(numOfNodes);
            addChildNote(rootNode, node);
        }

        return true;

    }



    private void addChildNote(T rootNode, T node) {




        if(rootNode.getWest() == null){
            if(node.getIAmWest()){
                numOfNodes += 1;
                rootNode.setWest(node);
            } else{
                if(rootNode.getSouth() != null){
                    addChildNote((T)rootNode.getWest(), node);
                }
            }

        } else if(rootNode.getNorth() == null){
            if(node.getIAmNorth()){
                numOfNodes += 1;
                rootNode.setNorth(node);
            } else{
                if(rootNode.getWest() != null && node.getWest().getSouth() != null){
                    addChildNote((T)rootNode.getNorth(), node);
                }
            }
        } else if(rootNode.getEast() == null){
            if(node.getIAmEast()){
                numOfNodes += 1;
                rootNode.setEast(node);
            } else{
                if(rootNode.getNorth() != null && rootNode.getNorth().getEast() != null){
                    addChildNote((T)rootNode.getEast(), node);
                }

            }
        } else if(rootNode.getSouth() == null){
            if(node.getIAmSouth()){
                numOfNodes += 1;
                rootNode.setSouth(node);
            } else {
                if(rootNode.getEast() != null && rootNode.getEast().getSouth() != null){
                    addChildNote((T)rootNode.getSouth(), node);
                }
            }
        }



        /*
       if(rootNode.getWest() == null && node.getIAmWest()){
           rootNode.setWest(node);
       } else {
           if(rootNode.getSouth() != null){
               addChildNote((T)rootNode.getWest(), node);
           }

           if(rootNode.getNorth() == null && node.getIAmNorth()){
               rootNode.setNorth(node);
           } else {
               if(rootNode.getWest() != null && rootNode.getWest().getSouth() != null && rootNode.getSouth() != null){
                   addChildNote((T)rootNode.getNorth(), node);
               }

               if(rootNode.getEast() == null && node.getIAmEast()){
                   rootNode.setEast(node);
               } else {
                   if(rootNode.getNorth() != null && rootNode.getNorth().getEast() != null && rootNode.getSouth() != null){
                       addChildNote((T)rootNode.getEast(), node);
                   }
                   if(rootNode.getSouth() == null && node.getIAmSouth()){
                       rootNode.setSouth(node);
                   } else{
                       if(rootNode.getEast() != null && rootNode.getEast().getSouth() != null){
                           addChildNote((T)rootNode.getSouth(), node);
                       } else{
                           return;
                       }
                   }

               }

           }
       }
*/


        /*

        if(rootNode.getWest() == null && node.getIAmWest()){
            numOfNodes += 1;
            rootNode.setWest(node);
        } else if(rootNode.getSouth() != null && rootNode.getWest() != null){
            addChildNote((T)rootNode.getWest(), node);
        }

        if(rootNode.getNorth() == null && node.getIAmNorth()){
            numOfNodes += 1;
            rootNode.setNorth(node);
        } else if(rootNode.getWest() != null && rootNode.getWest().getSouth() != null && node.getIAmNorth() && rootNode.getNorth() != null){
            addChildNote((T)rootNode.getNorth(), node);
        }

        if(rootNode.getEast() == null && node.getIAmEast()){
            numOfNodes += 1;
            rootNode.setEast(node);
        } else if (rootNode.getNorth() != null && rootNode.getNorth().getEast() != null && node.getIAmEast() && rootNode.getEast() != null){
            addChildNote((T)rootNode.getEast(), node);
        }

        if(rootNode.getSouth() == null && node.getIAmSouth()){
            numOfNodes += 1;
            rootNode.setSouth(node);
        }else if(rootNode.getEast() != null && rootNode.getEast().getSouth() != null && node.getIAmSouth() && rootNode.getSouth() != null){
            addChildNote((T)rootNode.getSouth(), node);
        }


*/




/*
        if(rootNode.getWest() == null && node.getIAmWest()){
          //  node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            rootNode.setWest(node);

        } else if(rootNode.getSouth() != null && node.getIAmWest() || (node.getIAmWest() && rootNode.getWest() != null)){
            addChildNote((T)rootNode.getWest(), node);
        }

        if(rootNode.getNorth() == null && rootNode.getWest() != node && node.getIAmNorth()){

           // node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            rootNode.setNorth(node);

        }else if(rootNode.getWest() != null && rootNode.getWest().getSouth() != null && node.getIAmNorth()|| (node.getIAmNorth() && rootNode.getNorth() != null)){
            addChildNote((T)rootNode.getNorth(), node);
        }
        if(rootNode.getEast() == null && rootNode.getNorth() != node && rootNode.getWest() != node && node.getIAmEast()){


         //   node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            rootNode.setEast(node);

        } else if(rootNode.getNorth() != null && rootNode.getNorth().getEast() != null && node.getIAmEast() || (node.getIAmEast() && rootNode.getEast() != null)){
            addChildNote((T)rootNode.getEast(), node);
        }
        if(rootNode.getSouth() == null && rootNode.getEast() != node && rootNode.getNorth() != node && rootNode.getWest() != node && node.getIAmSouth()){

      //      node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            rootNode.setSouth(node);

        } else if(rootNode.getEast() != null && rootNode.getEast().getSouth() != null && node.getIAmSouth() || (node.getIAmSouth() && rootNode.getSouth() != null)){
            addChildNote((T)rootNode.getSouth(), node);
        }
*/


    }


    public boolean containsValue(int index){

        if(rootNode != null){
            if(rootNode.getTreeIndex() == index){
                return true;
            } else{
                containsValue(rootNode, index);
            }

        }
        return false;

    }

    public boolean containsValue(T rootNode, int index){



     /*

        if(rootNode.getWest() != null && rootNode.getWest().getTreeIndex() == index){
            return true;
        } else if(rootNode.getWest() != null){
            containsValue((T)rootNode.getWest(), index);
        }
        if(rootNode.getNorth() != null && rootNode.getNorth().getTreeIndex() == index){
            return true;
        } else if(rootNode.getNorth() != null){
            containsValue((T)rootNode.getNorth(), index);
        }

        if(rootNode.getEast() != null && rootNode.getEast().getTreeIndex() == index){
            return true;
        } else if(rootNode.getEast() != null){
            containsValue((T)rootNode.getEast(), index);
        }
        if(rootNode.getSouth() != null && rootNode.getSouth().getTreeIndex() == index){
            return true;
        } else if(rootNode.getSouth() != null){
            containsValue((T)rootNode.getSouth(), index);
        }

*/

        /*

            if (rootNode.getWest() != null && rootNode.getWest().getTreeIndex() == index) {
                return true;

            } else if (rootNode.getWest() != null && rootNode.getSouth() != null && rootNode.getSouth().getTreeIndex() != index) {
                containsValue((T) rootNode.getWest(), index);
            }

            if (rootNode.getNorth() != null && rootNode.getNorth().getTreeIndex() == index) {
                return true;
            } else if (rootNode.getNorth() != null && rootNode.getWest() != null && rootNode.getWest().getTreeIndex() != index && rootNode.getWest().getSouth() != null && rootNode.getWest().getSouth().getTreeIndex() != index) {
                containsValue((T) rootNode.getNorth(), index);
            }


            if (rootNode.getEast() != null && rootNode.getTreeIndex() == index) {
                return true;
            } else if (rootNode.getEast() != null && rootNode.getNorth() != null && rootNode.getNorth().getTreeIndex() != index && rootNode.getNorth().getEast() != null && rootNode.getNorth().getEast().getTreeIndex() != index) {
                containsValue((T) rootNode.getEast(), index);
            }

            if (rootNode.getSouth() != null && rootNode.getTreeIndex() == index) {
                return true;
            } else if (rootNode.getSouth() != null && rootNode.getEast() != null && rootNode.getEast().getTreeIndex() != index && rootNode.getEast().getSouth() != null && rootNode.getEast().getSouth().getTreeIndex() != index) {
                containsValue((T) rootNode.getSouth(), index);
            }

            */

        return false;



    }

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes in tree = " + numOfNodes);
        sb.append( "Node + " + nodes);


        return sb.toString();


    }
}


