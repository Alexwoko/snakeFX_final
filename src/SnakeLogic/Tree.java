package SnakeLogic;

public class Tree<T extends Comparable<T>> {


    Node rootNode;
    //  Node leftNode;
    // Node rightNode
    int numOfNodes;


    public Tree() {

        numOfNodes = 0;
        // rootNode = new Node<>(0);
        // rootNode = new Node(Integer.MAX_VALUE);
    }

    public boolean add(T val) {

       if(rootNode == null){
           rootNode = new Node(val);
       }else{
           addChildNote(rootNode, val);
       }

        // recursiveAdd(val, this.rootNode);
        return true;

    }

    private void addChildNote(Node<T> parent, T val) {

        if(val.compareTo(parent.value) < 0){
            if(parent.leftChild == null){
                parent.leftChild = new Node(val);
            }else{
                addChildNote(parent.leftChild, val);
            }
        } else {
            if(parent.rightChild == null){
                parent.rightChild = new Node(val);
            }else{
                addChildNote(parent.rightChild, val);
            }

        }



    }

    public T containsValue(T value) {
        if (rootNode != null) {
            if (rootNode.value == value) return (T) rootNode.value;
            else return containsValue(rootNode, value);
        }
        return null;

    }


    public T containsValue(Node parent, T value) {


        if ((value.compareTo((T) parent.value) < 0) && (parent.leftChild != null)) {
            if (parent.leftChild.value.equals(value)) {
                return (T) parent.value;
            }else  { containsValue(parent.leftChild, value); }



        }else if((value.compareTo((T)parent.value) > 0) && (parent.rightChild != null)){
            if(value.equals(parent.rightChild.value)){
                return (T)parent.value;
            } else{
                 containsValue(parent.rightChild, value);
            }
        }



      return null;
    }






    private class Node<T extends Comparable<T>>{

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

        public Node(T val){

            this.value = val;

        }

        public Node(T val, Node parent){

            this.value = val;
            this.parent = parent;

        }


        public String toString(){

            StringBuilder sb = new StringBuilder();
            sb.append("Index = " + nodeIndex);
            sb.append(", value = " + value);
            sb.append(", parent node = " + parent);

            return sb.toString();


        }

    }


}
