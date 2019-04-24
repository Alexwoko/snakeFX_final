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

    public void emptyTree(){


        if(rootNode != null){
            rootNode = null;
            numOfNodes = 0;
        }
    }

    public boolean add(T node) {

      //  if(node.getVisited()){
        if(node.getPrevVisited()){
           return false;
        }

    
        if(rootNode == null){
            //  node.setTreeIndex(numOfNodes);
            node.setTreeIndex(numOfNodes);
            numOfNodes += 1;
            node.setVisited(true);
            rootNode = node;
        }else{

            addChildNote(rootNode, node);
        }

        return true;

    }

    private void addChildNote(T rootNode, T node) {

        if(node.getIAmWest()){
            if(rootNode.getWest() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                node.setVisited(true);
                rootNode.setWest(node);
            }else if(rootNode.getWest() != null && node.getIAmWest()){
                addChildNote((T)rootNode.getWest(), node);
            }
        }

        if(node.getIAmNorth()){
            if(rootNode.getNorth() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                node.setVisited(true);
                rootNode.setNorth(node);
            }else if(rootNode.getNorth() != null && node.getIAmNorth()){
                addChildNote((T)rootNode.getNorth(), node);
            }
        }

        if(node.getIAmEast()){
            if(rootNode.getEast() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                node.setVisited(true);
                rootNode.setEast(node);
            }else if(rootNode.getEast() != null && node.getIAmEast()){
                addChildNote((T)rootNode.getEast(), node);
            }
        }

        if(node.getIAmSouth()){
            if(rootNode.getSouth() == null){
                node.setTreeIndex(numOfNodes);
                numOfNodes += 1;
                node.setVisited(true);
                rootNode.setSouth(node);
            }else if(rootNode.getSouth() != null && node.getIAmSouth()){
                addChildNote((T)rootNode.getSouth(), node);
            }
        }
    }

    public boolean containsValue(Node node){

        if(rootNode != null){
            if(rootNode.getGridIndex() == node.getGridIndex()){
                return true;
            } else{
                containsValue(rootNode, node);
            }
        }
        return false;
    }

    public boolean containsValue(T rootNode, Node node){

       if(rootNode.getWest() != null){
           if(node.getIAmWest() && node.getGridIndex() == rootNode.getGridIndex()){
               return true;
           } else if(node.getIAmWest()){
               containsValue(rootNode, node);

           }
       }

       if(rootNode.getNorth() != null){
           if(node.getIAmNorth() && node.getGridIndex() == rootNode.getGridIndex()){
               return true;
           } else if(node.getIAmNorth()){
               containsValue(rootNode, node);
           }
       }

       if(rootNode.getEast() != null){
        if(node.getIAmEast() && node.getGridIndex() == rootNode.getGridIndex()){
            return true;
        } else if(node.getIAmEast()){
            containsValue(rootNode, node);
        }

       }

       if(rootNode.getSouth() != null){
           if(node.getIAmSouth() && node.getGridIndex() == rootNode.getGridIndex()){
               return true;
           } else if(node.getIAmSouth()){
               containsValue(rootNode, node);
           }
       }




        return false;








        /*
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
        return false;
        */
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes in tree = " + numOfNodes);
        sb.append( "Node + " + nodes);


        return sb.toString();

    }
}


