package SnakeLogic;

import javafx.scene.paint.Color;

/**
 * Items in the maze
 */
public class Item {
    private Color Color;
   // private int x;
  //  private int y;
    MathVector pos;

    public Item(Color color, int x, int y) {
        Color = color;
     pos = new MathVector(x, y);
       // pos.x = x;
       // pos.y = y;
    }

    public Color getColor() {
        return Color;
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }

    public void setX(int x){pos.x = x;}
    public void setY(int y){pos.y = y;}
}
