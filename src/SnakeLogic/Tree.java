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

        if(node.getVisited()){
            return false;
        }

        if(rootNode == null){
            //  node.setTreeIndex(numOfNodes);
            node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            node.setVisited(true);
            rootNode = node;
        }else{
          //  System.out.println(numOfNodes);
            addChildNote(rootNode, node);
        }

        return true;

    }



    private void addChildNote(T rootNode, T node) {



        if(node.getIAmWest()){
            if(rootNode.getWest() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                rootNode.setWest(node);
            }else if(rootNode.getWest() != null && node.getIAmWest()){
                addChildNote((T)rootNode.getWest(), node);
            }
        }

        if(node.getIAmNorth()){
            if(rootNode.getNorth() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                rootNode.setNorth(node);
            }else if(rootNode.getNorth() != null && node.getIAmNorth()){
                addChildNote((T)rootNode.getNorth(), node);
            }
        }

        if(node.getIAmEast()){
            if(rootNode.getEast() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                rootNode.setEast(node);
            }else if(rootNode.getEast() != null && node.getIAmEast()){
                addChildNote((T)rootNode.getEast(), node);
            }
        }

        if(node.getIAmSouth()){
            if(rootNode.getSouth() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                rootNode.setSouth(node);
            }else if(rootNode.getSouth() != null && node.getIAmSouth()){
                addChildNote((T)rootNode.getSouth(), node);
            }
        }


    }


    public boolean containsValue(int index){

        if(rootNode != null){
            if(rootNode.getGridIndex() == index){
                return true;
            } else{
                containsValue(rootNode, index);
            }

        }
        return false;

    }

    public boolean containsValue(T rootNode, int index){


        if(rootNode.getWest() != null){
            if(rootNode.getWest().getGridIndex() == index){
                return  true;
            }else{
                containsValue((T)rootNode.getWest(), index);
            }
        } else if(rootNode.getNorth() != null){
            if(rootNode.getNorth().getGridIndex() == index){
                return true;
            } else{
                containsValue((T)rootNode.getNorth(), index);
            }

        } else if(rootNode.getEast() != null){
            if(rootNode.getEast().getGridIndex() == index){
                return true;
            } else{
                containsValue((T)rootNode.getEast(), index);
            }
        } else if(rootNode.getSouth() != null){
            if(rootNode.getSouth().getGridIndex() == index){
                containsValue((T)rootNode.getSouth(), index);
            }
        }



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


