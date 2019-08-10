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
     * @return hasCookie boolean
     */

    public boolean getHasCookie(){return hasCookie;}

    /**
     * Setter for moveCost
     * @param moveCost
     */

    public void setMoveCost(float moveCost) {this.moveCost = moveCost;}

    /**
     * Getter for moveCost
     * @return moveCost float
     */

    public float getMoveCost() {return moveCost;}

    /**
     * Getter for walkable
     * @return boolean
     */

    public boolean getWalkable(){return walkable;}

    /**
     * Setter for walkable
     * @param walkable boolean
     */

    public void setWalkable(boolean walkable){this.walkable = walkable;}

    /**
     * Setter for prevVisited
     * @param prevVisited boolean
     */

    public void setPrevVisited(boolean prevVisited){this.prevVisited = prevVisited;}

    /**
     * getter for prevVisited
     * @return boolean
     */

    public boolean getPrevVisited(){return prevVisited;}

    /**
     * Setter for prevVisitedTwo (to track second ghost)
     * @param prevVisitedTwo boolean
     */

    public void setPrevVisitedTwo(boolean prevVisitedTwo){this.prevVisitedTwo = prevVisitedTwo;}

    /**
     * Getter for prevVisitedTwo (to track second ghost)
     * @return boolean
     */

    public boolean getPrevVisitedTwo(){return prevVisitedTwo;}

    /**
     * Setter for prevVisitedThree (to track third ghost)
     * @param prevVisitedThree boolean
     */

    public void setPrevVisitedThree(boolean prevVisitedThree){this.prevVisitedThree = prevVisitedThree;}

    /**
     * Getter for prevVisitedThree (to track third ghost)
     * @return boolean
     */

    public boolean getPrevVisitedThree(){return prevVisitedThree;}


    /**
     * Getter for node width
     * @return int
     */

    public int getWidth() {return width;}

    /**
     * Getter for node height
     * @return float
     */

    public float getHeight() {return height;}

    /**
     * Getter for gridPos x
     * @return float
     */

    @Override
    public float getX() {return gridPos.x;}

    /**
     * Getter for gridPos y
     * @return float
     */

    @Override
    public float getY() {return gridPos.y;}

    /**
     * Getter for visited
     * @return boolean
     */

    @Override
    public boolean getVisited() { return visited;}

    /**
     * Setter for visited
     * @param visited boolean
     */

    @Override
    public void setVisited(boolean visited) {this.visited = visited;}

    /**
     * Getter for the attribute nodeFrom (can point back to a neighbour node)
     * @return GraphItem
     */

    @Override
    public GraphItem getFrom() {return nodeFrom;}

    /**
     * Setter for the attribute nodeFrom (can point back to a neighbour node)
     * @param from GraohNode
     */

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
