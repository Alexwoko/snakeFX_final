package SnakeLogic;

public class Player extends MovingObject {

    private boolean isInvincible;


    public Player(int x, int y) {
        super(x, y);
    }

    public void setInvincible(boolean isInvincible){this.isInvincible = isInvincible;}
    public boolean getInvincible(){return isInvincible;}


}
