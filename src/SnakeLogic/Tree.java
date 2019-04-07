package SnakeLogic;

public class Tree<T extends Comparable<T>> {


    Node rootNode;
    //  Node leftNode;
    // Node rightNode
    int numOfNodes;
    T incrementer;
    T sum;


    public Tree(int numOfNodes, T incrementer) {

        this.incrementer = incrementer;
        this.numOfNodes = numOfNodes;
        // rootNode = new Node<>(0);
        // rootNode = new Node(Integer.MAX_VALUE);
    }

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

    public MathVector getChildNode(Node<T> rootNode, int index){

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


    public void emptyTree(){


        if(rootNode != null){
            rootNode = null;
            numOfNodes = 0;
        }


    }


    public boolean add(T val) {



        if(rootNode == null){

            numOfNodes += 1;
            rootNode = new Node(val);
            rootNode.nodeIndex = numOfNodes;
        }else{
            addChildNote(rootNode, val);
        }

        return true;

    }

    private void addChildNote(Node<T> parent, T val) {

        if(val.compareTo(parent.value) > 0){
            if(parent.leftChild == null){
                parent.leftChild = new Node(val);
                parent.leftChild.parent = parent;
                numOfNodes += 1;
               // incIndex((Float)numOfNodes, (Float)incrementer);
                parent.leftChild.nodeIndex = numOfNodes;
            }else{
                addChildNote(parent.leftChild, val);
            }
        } else {
            if(parent.rightChild == null){
                parent.rightChild = new Node(val);
                parent.rightChild.parent = parent;
               // incIndex((Float)numOfNodes, (Float)incrementer);
                numOfNodes += 1;
                parent.rightChild.nodeIndex = numOfNodes;
            }else{
                addChildNote(parent.rightChild, val);
            }

        }
    }


    public boolean containsValue(int index){

        if(rootNode != null){
            if (rootNode.nodeIndex == index){
                return true;
            }else{
               return containsValue(rootNode, index);
            }

        }


        return false;
    }

    public boolean containsValue(Node parent, int index){


        if(parent.nodeIndex < index && parent.leftChild != null){
            if(parent.leftChild.nodeIndex == index){
                return true;
            } else {
               return containsValue(parent.leftChild, index);
            }

        } else if(parent.nodeIndex < index && parent.rightChild != null){
            if(parent.rightChild.nodeIndex == index){
                return true;
            } else{
                return containsValue(parent.rightChild, index);
            }

        }


        return false;
    }



public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Number of nodes in tree = " + numOfNodes);

        return sb.toString();


}



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



}
