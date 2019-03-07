package SnakeLogic;

import javafx.scene.paint.Color;

/**
 * Items in the maze
 */
public class Item {
    private Color Color;
    private int x;
    private int y;

    public Item(Color color, int x, int y) {
        Color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return Color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
}
