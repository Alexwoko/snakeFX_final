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
  //  void changeDir();
    void setDir(String dir);




}
