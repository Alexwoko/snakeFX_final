package SnakeLogic;




public class GNode implements GraphItem {


    private Edge[] edges;
    private GraphItem nodeFrom;
    private boolean visited;
    private int numOfEdges;
    private int gridIndex;
    private MathVector gridPos;

    public GNode(int gridIndex){
        edges = new Edge[4];
        nodeFrom = null;
        visited = false;
        numOfEdges = 0;
        this.gridIndex = gridIndex;
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

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

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
}
