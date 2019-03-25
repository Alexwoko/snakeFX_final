package SnakeLogic;

public class Player extends MovingObject {


    public Player(int x, int y) {
        super(x, y);
    }


    @Override
    public void update() {

        super.getVel().add(super.getAccel());
        super.getVel().limit(2);
        super.getPos().add(super.getVel());
        super.getAccel().mult(0);
    }
}
