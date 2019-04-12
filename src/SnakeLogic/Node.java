package SnakeLogic;

public class Node {

   private Node westNode;
    private Node eastNode;
    private Node northNode;
    private Node southNode;
    private Node parent;
    private MathVector pos;
    private int index;
    private boolean walkable;


    public Node(int index){
        this.index = index;

    }

    public Node(int index, Node parent){

        this.parent = parent;
        this.index = index;

    }

    public Node(float x, float y){

        pos = new MathVector(x, y);

    }

    public int getIndex(){return index;}
    public Node getParent(){return parent;}
    public void setParent(Node parent){this.parent = parent;}
    public boolean isWalkable(){return walkable;}


    public void setWalkable(boolean w){walkable = w;}
    public float getX(){return pos.x;}
    public float getY(){return pos.y;}

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Pos = " + pos);
        sb.append(", parent = " + parent);
        sb.append(", is walkable = " + walkable);

        return sb.toString();

    }


}
