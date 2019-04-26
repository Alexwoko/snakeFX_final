package SnakeLogic;




public class GNode implements GraphItem {


    private Edge[] edges;
    private GraphItem nodeFrom;
    private boolean visited;
    private int numOfEdges;
    private int gridIndex;
    private MathVector gridPos;
    private boolean walkable;
    private final int width = 20;
    private final float height = 17.5f;
    private boolean prevVisited;
    private final float  strength = 2.9f;

    public GNode(int gridIndex){
        edges = new Edge[4];
        nodeFrom = null;
        visited = false;
        numOfEdges = 0;
        this.gridIndex = gridIndex;
    }

    GNode(float x, float y, int gridIndex){

        edges = new Edge[4];
        nodeFrom = null;
        visited = false;
        numOfEdges = 0;
        this.gridIndex = gridIndex;
      gridPos = new MathVector(x, y);
      walkable = true;

    }


    GNode(int gridIndex, MathVector gridPos){

        this.gridIndex = gridIndex;
        this.gridPos = gridPos;

    }

    public void GNode(int gridIndex, boolean visited){
        this.gridIndex = gridIndex;
        this.visited = visited;
        edges = new Edge[4];
        nodeFrom = null;
        numOfEdges = 0;

    }

    public void GNode(int gridIndex, boolean visited, Edge[] edges){

        this.gridIndex = gridIndex;
        this.visited = visited;
        this.edges = edges;
        nodeFrom = null;
        numOfEdges = 0;

    }



public void addEdge(Edge e){

        for(int i = 0; i < edges.length; i++){

            if(i == numOfEdges){
                edges[i] = e;
            }
        }
        numOfEdges++;

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

    public MathVector repel(MovingObject o){


        MathVector dir;
        MathVector oPos = new MathVector(o.getX(), o.getY());
        dir = oPos.sub(this.gridPos);
        float d = (float)dir.mag();
        d = constrain(d, 1, 2);
        dir.normalize();
        float force =    strength / (d * d);
        dir.mult((int)force);
        return dir;

    }


public boolean getWalkable(){return walkable;}

public void setWalkable(boolean walkable){
        this.walkable = walkable;

}


public void setPrevVisited(boolean prevVisited){this.prevVisited = prevVisited;}
public boolean getPrevVisited(){return prevVisited;}

    public int getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void setX(int x) {gridPos.x = x;}

    @Override
    public void setY(int y) {gridPos.y = y;}

    @Override
    public float getX() {
        return gridPos.x;
    }

    @Override
    public float getY() {return gridPos.y;}

    @Override
    public void setEdges(Edge[] edges) {this.edges = edges;}

    @Override
    public Edge[] getEdges() {return edges; }

    @Override
    public boolean getVisited() { return false;}

    @Override
    public int getGridIndex() {return gridIndex;}

    @Override
    public void setVisited(boolean visited) {this.visited = visited;}

    @Override
    public GraphItem getFrom() {return nodeFrom;}


    @Override
    public void setFrom(GraphItem from) {this.nodeFrom = from;}

    @Override
    public int getNumOfEdges() {return numOfEdges;}

    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("Grid index = " + gridIndex);
        sb.append(", Num of Edges = " + numOfEdges);
        sb.append("Edges length = " + edges.length);
      //  sb.append(", Grid pos = " + gridPos);
        sb.append( ", parent = " + nodeFrom);
      //  sb.append(", Walkable = " + walkable);

return sb.toString();

    }

}
