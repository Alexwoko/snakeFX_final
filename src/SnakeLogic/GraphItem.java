package SnakeLogic;

public interface GraphItem<T> {


    void setEdges(Edge[] edges);
    Edge[] getEdges();
    boolean getVisited();
    int getGridIndex();
    void setVisited(boolean visited);
    GraphItem getFrom();
    void setFrom(GraphItem from);
    int getNumOfEdges();
    void addEdge(Edge e);
    void setX(int x);
    void setY(int y);
    int getX();
    int getY();


}
