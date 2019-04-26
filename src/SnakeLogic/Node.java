package SnakeLogic;

public class Node implements TreeItem<Node>{

   private TreeItem westNode;
    private TreeItem eastNode;
    private TreeItem northNode;
    private TreeItem southNode;
    private TreeItem parent;
    private MathVector pos;
    private int index;
    private int treeIndex;
    private boolean walkable;
    private boolean iAmWest;
    private boolean iAmNorth;
    private boolean iAmEast;
    private boolean iAmSouth;
    private boolean visited;
    private boolean prevVisited;


    private float strength;

    private final int width = 20;
    private final float height = 17.5f;


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

    public Node(float x, float y, boolean walkable, String dirFromOrigin){
        pos = new MathVector(x, y);
        this.walkable = walkable;
        assignDirFromOrigin(dirFromOrigin);
        this.index = Integer.MAX_VALUE;


    }

    public Node(float x, float y, int index){

        pos = new MathVector(x, y);
       // this.walkable = walkable;
       // assignDirFromOrigin(dirFromOrigin);
        this.index = index;
        this.strength = 2.9f;
        this.walkable = true;
        visited = false;
        this.treeIndex = Integer.MAX_VALUE;
        prevVisited = false;

    }

    public void assignDirFromOrigin(String dir){

        if(dir == "WEST"){
            setIAmWest(true);
        }
        if(dir == "NORTH"){
            setIAmNorth(true);
        }
        if(dir == "EAST"){
            setIAmEast(true);
        }
        if(dir == "SOUTH"){
            setIAmSouth(true);
        }

    }


    public MathVector repel(MovingObject o){


        MathVector dir;
        MathVector oPos = new MathVector(o.getX(), o.getY());
        dir = oPos.sub(this.pos);
        float d = (float)dir.mag();
        d = constrain(d, 1, 2);
        dir.normalize();
        float force =    strength / (d * d);
        dir.mult((int)force);
        return dir;

    }

    public float constrain(float x, float a, float b){

        if(x < a){
            return a;
        }
        if(b < x){
            return b;
        }else{
            return x;
        }
    }


    public int getWidth(){return width;}
    public float getHeight(){return height;}


public boolean getVisited(){return visited;}

    @Override
    public void setVisited(boolean visited) {

        this.visited = visited;
    }

    public void setPrevVisited(boolean visited){this.prevVisited = visited;}
    public boolean getPrevVisited(){return prevVisited;}

    public void setTreeIndex(int treeIndex){this.treeIndex = treeIndex;}
public int getTreeIndex(){return treeIndex;}
    public void setIAmWest(boolean amWest){iAmWest = amWest;}
    public void setIAmNorth(boolean amNorth){iAmNorth = amNorth;}
    public void setIAmEast(boolean amEast){iAmEast = amEast;}
    public void setIAmSouth(boolean amSouth){iAmSouth = amSouth;}
    public boolean getIAmWest(){return iAmWest;}
    public boolean getIAmNorth(){return iAmNorth;}
    public boolean getIAmEast(){return iAmEast;}
    public boolean getIAmSouth(){return iAmSouth;}





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

    public void setGridIndex(int index){this.index = index;}
    public int getGridIndex(){return index;}

    public boolean getWalkable(){return walkable;}
    public void setWalkable(boolean w){walkable = w;}
    public float getX(){return pos.x;}
    public float getY(){return pos.y;}
    public void setX(float x){this.pos.x = x;}
    public void setY(float y){this.pos.y = y;}

    public String toString(){

        StringBuilder sb = new StringBuilder();
        String dir = " ";

        if(this.getIAmWest()){
            dir = "WEST";
        }
        if(this.getIAmNorth()){
            dir = "NORTH";
        }
        if(this.getIAmEast()){
            dir = "EAST";
        }
        if(this.getIAmSouth()){
            dir = "SOUTH";
        }

        sb.append(" Direction = " + dir);
        sb.append(", Treeindex = " + treeIndex);
        sb.append(", Index nr = " + index);
        sb.append(", Pos = " + pos);
        sb.append(", parent = " + parent);
        sb.append(", is walkable = " + walkable);

        return sb.toString();

    }


}
