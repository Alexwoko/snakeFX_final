package SnakeLogic;

import java.util.ArrayList;

public class Tree<T> {


    private T rootNode;
    private T westNode;
    private T eastNode;
    private T northNode;
    private T southNode;
    private int numOfNodes;
    private ArrayList<T> nodes;


    public Tree() {


        numOfNodes = 0;
        nodes = new ArrayList<>();

    }


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
            numOfNodes += 1;
            rootNode = node;
            nodes.add(node);
        }else{
            addChildNote(rootNode, node);
        }

        return true;

    }


    public boolean addChildNote(T rootNode, T node) {


        if(westNode == null){
            westNode = node;
            numOfNodes += 1;
            return true;
        } else {
            addChildNote(westNode, node);
        }
        else if(northNode == null){
            northNode = node;
            numOfNodes += 1;
            return true;
        } else{
            addChildNote(northNode, node);
        }
        if(eastNode == null){
            eastNode = node;
            numOfNodes += 1;
            return true;
        }else{
            addChildNote(eastNode, node);
        }
        if(southNode == null){
            southNode = node;
            numOfNodes += 1;
            return true;
        } else{
            addChildNote(southNode, node);
        }

        return false;

        /*
        if(westNode == null){
            westNode = node;
            numOfNodes += 1;

        } else if(northNode == null){
            northNode = node;
            numOfNodes += 1;

        } else if(eastNode == null){
            eastNode = node;
            numOfNodes += 1;
        } else if(southNode == null){
            southNode = node;
            numOfNodes += 1;
        }

        if(westNode != null){
            addChildNote(westNode, node);
        } else if(northNode != null){
            addChildNote(northNode, node);
        } else if(eastNode != null){
            addChildNote(eastNode, node);
        } else if(southNode != null){
            addChildNote(southNode, node);
        }
*/


/*
        if(westNode == null){
           westNode = node;
           numOfNodes += 1;


        } else if (northNode == null){
            northNode = node;
            numOfNodes += 1;

        }else if(eastNode == null){
            eastNode = node;
            numOfNodes += 1;

        } else if(southNode == null){
            southNode = node;
            numOfNodes += 1;
        } else {
            add(addChildNote();

        }
*/

        /*
        if(westNode == null){
            westNode = node;
            numOfNodes += 1;
            nodes.add(node);
        } else {
            addChildNote(westNode, node);

        }

        if(northNode == null){
            northNode = node;
            numOfNodes += 1;
            nodes.add(node);
        } else{
            addChildNote(northNode, node);
        }

        if(eastNode == null){
            eastNode = node;
            numOfNodes += 1;
            nodes.add(node);
        } else{
            addChildNote(eastNode, node);
        }
        if(southNode == null){
            southNode = node;
            numOfNodes += 1;
            nodes.add(node);
        } else{
            addChildNote(southNode, node);
        }
*/
    }


    public boolean containsValue(T node){

        if(rootNode != null){
            if (rootNode == node){
                return true;
            }else{
                return containsValue(rootNode, node);
            }

        }
        return false;
    }

    public boolean containsValue(T parent, T node){



        if(westNode != null){
            if(westNode == node){
                return true;
            }

        }
return false;

        /*

      if(westNode != null){
          if(westNode == node){
              return true;
          }else{
              containsValue(westNode, node);
          }
      }
      if(northNode != null){
          if(northNode == node){
              return true;
          }else{
              containsValue(northNode, node);
          }
      }
      if(eastNode != null){
          if(eastNode == node){
              return true;
          }else{
              containsValue(eastNode, node);
          }
      }
      if(southNode != null){
          if(southNode == node){
              return true;
          }else{
              containsValue(southNode, node);
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

/*

    public class Node<T extends Comparable<T>> implements Comparable<Node> {

        MathVector pos;
        Node parent;
        Node leftChild;
        Node rightChild;
        Node upChild;
        Node downChild;
        int nodeIndex;
        T value;



        public Node getLeftChild(){return leftChild;}

        public Node getRightChild() {return rightChild;}
        public Node getParent(){return parent;}
        public int getIndex(){return nodeIndex;}
        public MathVector getPos(){return pos;}
        public void setPos(int x, int y){this.pos = new MathVector(x, y);}
        public T getValue(){return value;}


        public Node(T val){

            this.value = val;

        }

        public Node(T val, Node parent){

            this.value = val;
            this.parent = parent;

        }

        Node(float x, float y, T val){

            this.value = val;
            pos = new MathVector(x, y);

        }


        public String toString(){

            StringBuilder sb = new StringBuilder();
            sb.append("Index = " + nodeIndex);
            sb.append(", value = " + value);
            sb.append(", parent node = " + parent);


            return sb.toString();


        }

        @Override
        public int compareTo(Node o) {

            if(this.value.equals(o.value)){
                return 0;
            }else if((Integer)this.value > (Integer) o.value){
                return 1;
            } else if ((Integer)this.value < (Integer) o.value){
                return -1;
            }

            return 0;
        }
    }
*/


}
