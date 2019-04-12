package SnakeLogic;

public class Node {

    Node westNode;
    Node eastNode;
    Node northNode;
    Node southNode;
    Node parent;
    MathVector pos;
    int index;
    boolean walkable;


    public Node(int index){
        this.index = index;

    }

    public Node(int index, Node parent){

        this.parent = parent;
        this.index = index;

    }

    public int getIndex(){return index;}
    public Node getParent(){return parent;}
    public boolean isWalkable(){return walkable;}
    public void setWalkable(boolean w){walkable = w;}

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Pos = " + pos);
        sb.append(", parent = " + parent);
        sb.append(", is walkable = " + walkable);

        return sb.toString();

    }


}
