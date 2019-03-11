package SnakeLogic;

public class Player implements GameObject{


    String dir;

    private MathVector pos;
    private MathVector vel;
    private boolean moving;
    private float maxSpeed;



    public Player(int x, int y){

        pos = new MathVector(x, y);
        vel = new MathVector(0, 0);
        dir = null;
        moving = false;
        maxSpeed = 1;

    }




    @Override
    public float getX() {return pos.x;}
    @Override
    public float getY() {return pos.y; }
    @Override
    public void setX(int x) {this.pos.x = x;}
    @Override
    public void setY(int y) {
    this.pos.y = y;
    }
    public void setVelX(float in){vel.x = in;}
    public void setVelY(float in){vel.y = in;}
   public MathVector getVel(){return vel;}


    @Override
    public void update() {

        pos.add(vel);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    @Override
    public String getDir() {
        return dir;
    }

    public void setDir(String dir){this.dir = dir;}


@Override
    public void moveRight(){
    setDir("RIGHT");
   // vel.x = 1;
    pos.x += vel.x;
    }
@Override
    public void moveLeft(){
    setDir("LEFT");
        pos.x -= vel.x;

    }
    @Override
    public void moveUp(){
    setDir("UP");

    pos.y -= vel.y;

    }
    @Override
    public void moveDown(){
    setDir("DOWN");
        //   y += 1;
          //  velX = 0;
       // vel.y = 1;
        pos.y += vel.y;
      //  vel.x = 0;
    }

}
