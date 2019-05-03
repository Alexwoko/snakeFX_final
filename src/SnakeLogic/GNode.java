package SnakeLogic;


/**
 * Class GNode(GraphNode) --> implements GraphItem interface
 */

public class GNode implements GraphItem {

    /**
     * Class variables
     */

    private GraphItem nodeFrom;
    private boolean visited;
    private MathVector gridPos;
    private boolean walkable;
    private final int width = 20;
    private final float height = 17.5f;
    private boolean prevVisited;
    private boolean prevVisitedTwo;
    private boolean prevVisitedThree;
    private float moveCost;
    private boolean hasCookie;

    /**
     * class constructor
     * @param x float
     * @param y float
     * @param gridIndex int
     */

    GNode(float x, float y, int gridIndex){

        nodeFrom = null;
        visited = false;
        gridPos = new MathVector(x, y);
        walkable = true;
        moveCost = Float.MAX_VALUE;

    }

    /**
     * Settter for hasCookie
     * @param hasCookie boolean
     */
    public void setHasCookie(boolean hasCookie){this.hasCookie = hasCookie;}

    /**
     * Getter for hasCookie
     * @return boolean
     */

    public boolean getHasCookie(){return hasCookie;}

    public void setMoveCost(float moveCost) {this.moveCost = moveCost;}

    public float getMoveCost() {return moveCost;}


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

        final float  strength = 1.01f;


        MathVector dir;
        MathVector oPos = new MathVector(o.getX(), o.getY());
        dir = oPos.sub(this.gridPos);
        float d = (float)dir.mag();  // Distance?
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
    public void setPrevVisitedTwo(boolean prevVisitedTwo){this.prevVisitedTwo = prevVisitedTwo;}
    public boolean getPrevVisitedTwo(){return prevVisitedTwo;}
    public void setPrevVisitedThree(boolean prevVisitedThree){this.prevVisitedThree = prevVisitedThree;}
    public boolean getPrevVisitedThree(){return prevVisitedThree;}


    public int getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public float getX() {
        return gridPos.x;
    }

    @Override
    public float getY() {return gridPos.y;}

    @Override
    public boolean getVisited() { return visited;}

    @Override
    public void setVisited(boolean visited) {this.visited = visited;}

    @Override
    public GraphItem getFrom() {return nodeFrom;}

    @Override
    public void setFrom(GraphItem from) {this.nodeFrom = from;}


    public String toString(){

        StringBuilder sb = new StringBuilder();

        // sb.append("Grid index = " + gridIndex);
        // sb.append(", Num of Edges = " + numOfEdges);
        //  sb.append("Edges length = " + edges.length);
        sb.append(", has cookie = " + hasCookie);
        sb.append(", been visited = " + visited);
        sb.append(", Grid pos = " + gridPos);
        sb.append( ", parent = " + nodeFrom);
        //  sb.append(", Walkable = " + walkable);

        return sb.toString();

    }

}
