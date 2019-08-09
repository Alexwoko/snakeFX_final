package SnakeLogic;

/**
 * GameObject interface
 *
 */

public interface GameObject {


    float getX();
    float getY();
    void setX(float x);
    void setY(float y);
    void update();
    String getDir();
    void moveLeft();
    void moveRight();
    void moveUp();
    void moveDown();
    void setDir(String dir);
 //   void applyRepeller(GNode node);
    void applyForce(MathVector force);
    String toString();

}
