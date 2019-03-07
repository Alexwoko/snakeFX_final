package SnakeLogic;

public class RandomRambler implements GameObject {


   int x;
   int y;


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
    this.x = x;
    }

    @Override
    public void setY(int y) {
    this.y = y;

    }

    @Override
    public void update() {

        // CHECK DIR
        // CHECK FOR WALLS
        // CHECK FOR PLAYER

    }
}
