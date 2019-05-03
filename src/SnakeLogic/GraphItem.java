package SnakeLogic;

public interface GraphItem<T> {



    boolean getVisited();

    void setVisited(boolean visited);
    GraphItem getFrom();
    void setFrom(GraphItem from);
    float getX();
    float getY();


}
