package SnakeLogic;

public interface TreeItem<T>  {



    boolean getPrevVisited();
    void setPrevVisited(boolean visited);
    void setGridIndex(int index);
    int getGridIndex();
    boolean getVisited();
    void setVisited(boolean visited);
    void setTreeIndex(int index);
    int getTreeIndex();
    TreeItem getWest();
    TreeItem getNorth();
    TreeItem getEast();
    TreeItem getSouth();
    TreeItem getParent();
    void setParent(TreeItem parent);
    void setWest(TreeItem west);
    void setNorth(TreeItem north);
    void setEast(TreeItem east);
    void setSouth(TreeItem south);
    float getX();
    float getY();
    void setIAmWest(boolean amWest);
    void setIAmNorth(boolean amNorth);
    void setIAmEast(boolean amEast);
    void setIAmSouth(boolean amSouth);
    boolean getIAmWest();
    boolean getIAmNorth();
    boolean getIAmEast();
    boolean getIAmSouth();

}
