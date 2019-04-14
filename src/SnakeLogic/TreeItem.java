package SnakeLogic;

public interface TreeItem<T>  {



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
    void setX(float x);
    void setY(float y);
    float getX();
    float getY();

}
