package SnakeLogic;

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
            addChildNote(rootNode, node);
        }
        System.out.println("num of nodes in tree = " + getSize());
        return true;

    }



    public void addChildNote(T rootNode, T node) {


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

    public boolean containsValue(T node, int index){


        if(node.getWest() != null && node.getWest().getTreeIndex() == index){
            return true;
        } else if(node.getNorth() != null && node.getEast() != null && node.getSouth() != null){
            if(node.getNorth().getTreeIndex() != index && node.getEast().getTreeIndex() != index && node.getSouth().getTreeIndex() != index){
                containsValue((T)node.getWest(), index);
            }

        }


        if(node.getNorth() != null && node.getNorth().getTreeIndex() == index){
            return true;
        } else if(node.getWest() != null && node.getWest().getSouth() != null){
            if(node.getWest().getSouth().getTreeIndex() != index){
                containsValue((T)node.getNorth(), index);
            }
        }

        if(node.getEast() != null && node.getEast().getTreeIndex() ==index){
            return true;
        } else if(node.getNorth() != null && node.getNorth().getEast() != null){
            if(node.getNorth().getEast().getTreeIndex() != index){
                containsValue((T)node.getEast(), index);
            }
        }

        if(node.getSouth() != null && node.getSouth().getTreeIndex() == index){
            return true;
        } else if(node.getEast() != null && node.getEast().getSouth() != null){
            if(node.getEast().getSouth().getTreeIndex() != index){
                containsValue((T)node.getSouth(), index);
            }
        }

/*
        if(node.getNorth() != null && node.getNorth().getTreeIndex() == index){
            return true;
        } else if(node.getWest().getSouth() != null){
            if(node.getWest().getSouth().getTreeIndex() != index){
                containsValue((T)node.getNorth(), index);
            }
        }


        if(node.getEast() != null && node.getEast().getTreeIndex() == index){
            return true;
        } else if(node.getNorth().getEast() != null){
            if(node.getNorth().getEast().getTreeIndex() != index){
                containsValue((T)node.getEast(), index);
            }
        }

        if(node.getSouth() != null && node.getSouth().getTreeIndex() == index){
            return true;
        } else if(node.getEast().getSouth() != null){
            if(node.getEast().getSouth().getTreeIndex() != index){
                containsValue((T)node.getSouth(), index);
            }
        }

        */

        return false;

        /*
        if(node.getWest() != null && node.getWest().getTreeIndex() == index){
            return true;
        } else if(node.getNorth().getTreeIndex() != index && node.getEast().getTreeIndex() != index && node.getWest().getTreeIndex() != index){

            containsValue((T)node.getWest(), index);
        }
        if(node.getNorth() != null && node.getNorth().getTreeIndex() == index){
            return true;
        } else if(node.getWest().getSouth().getTreeIndex() != index){
            containsValue((T)node.getNorth(), index);
        }
        if(node.getEast() != null && node.getEast().getTreeIndex() == index){
            return true;
        } else if(node.getNorth().getTreeIndex() != index){
            containsValue((T)node.getEast(), index);
        }
        if(node.getSouth() != null && node.getSouth().getTreeIndex() == index){
            return true;
        }else if(node.getEast().getTreeIndex() != index){
            containsValue((T)node.getEast(), index);
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


