package SnakeLogic;

/**
 * Created by Ebbe Vang on 23-01-2017.
 */
public interface GameObject {


    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    void setVelX(float x);
    void setVelY(float y);
    float getVelX();
    float getVelY();



    void update();
    String getDir();


    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    void setDir(String dir);
    void applyRepeller(Grid.Tile tile);
    void applyForce(MathVector force);
    String toString();

 //   String atWall(Grid.Tile w);



}
