package SnakeLogic;

/**
 * Created by Ebbe Vang on 23-01-2017.
 */
public interface GameObject {


    int getX();
    int getY();
    void setX(int x);
    void setY(int y);


    void update();
    String getDir();

    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    void setDir(String dir);
    void applyRepeller(Wall wall);
    void applyForce(MathVector force);
    String toString();
    void setDownN(boolean downN);
    void setRightN(boolean rightN);
    void setLeftN(boolean leftN);
    void setTopN(boolean topN);
    boolean hasRightN();
    boolean hasLeftN();
    boolean hasTopN();
    boolean hasDownN();
    String atWall(Wall w);



}
