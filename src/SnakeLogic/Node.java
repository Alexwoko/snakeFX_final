package SnakeLogic;

public class Node implements TreeItem<Node>{

   private TreeItem westNode;
    private TreeItem eastNode;
    private TreeItem northNode;
    private TreeItem southNode;
    private TreeItem parent;
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

    public Node(float x, float y, boolean walkable){
        pos = new MathVector(x, y);
        this.walkable = walkable;

    }

    public void setWest(TreeItem westNode){this.westNode = westNode;}
    public void setNorth(TreeItem northNode){this.northNode = northNode;}
    public void setEast(TreeItem eastNode){this.eastNode = eastNode;}
    public void setSouth(TreeItem southNode){this.southNode = southNode;}
    public TreeItem getWest(){return westNode;}
    public TreeItem getNorth(){return northNode;}
    public TreeItem getEast(){return eastNode;}
    public TreeItem getSouth(){return southNode;}
    public void setParent(TreeItem parent){this.parent = parent;}
    public TreeItem getParent(){return parent;}

    public void setTreeIndex(int index){this.index = index;}
    public int getTreeIndex(){return index;}

    public boolean isWalkable(){return walkable;}


    public void setWalkable(boolean w){walkable = w;}
    public float getX(){return pos.x;}
    public float getY(){return pos.y;}
    public void setX(float x){this.pos.x = x;}
    public void setY(float y){this.pos.y = y;}

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Pos = " + pos);
        sb.append(", parent = " + parent);
        sb.append(", is walkable = " + walkable);

        return sb.toString();

    }


}
