package SnakeLogic;

import java.util.Random;

public class RandomRambler implements GameObject {


   private MathVector pos, vel, accel;
   private MathVector up, down, right, left;
   private int maxForce, maxSpeed;
   private boolean rightN, leftN, topN, downN;

private String dir;

public RandomRambler(int x, int y){

    pos = new MathVector(x, y);
    vel = new MathVector(0, 0);
    accel = new MathVector(0, 0);

    up = new MathVector(0, -1);
    down = new MathVector(0, 1);
    left = new MathVector(-1, 0);
    right = new MathVector(1, 0);

    downN = false;
    topN = false;
    leftN = false;
    rightN = false;

    maxForce = 1;
    maxSpeed = 1;



}
@Override
public void moveRight(){
    setDir("RIGHT");
    vel.y = 0;
    applyForce(right);

}

@Override
public void moveLeft(){
    setDir("LEFT");
    vel.y = 0;
    applyForce(left);
}
@Override
public void moveUp(){
    setDir("UP");
    vel.x = 0;
    applyForce(up);

}
@Override
public void moveDown(){
    setDir("DOWN");
    vel.x = 0;
    applyForce(down);

}


    public void randomWalk(){

 Random ran = new Random();
 int result = ran.nextInt(3);

 switch(result){
  case 0:
   moveRight();
   break;
  case 1:
   moveLeft();
   break;
  case 2:
   moveUp();
   break;
  case 3:
   moveDown();

 }
}

    @Override
    public float getX() {return pos.x;}

    @Override
    public float getY() {return pos.y;}

   @Override
    public void setX(float x) {pos.x = x;}

    @Override
    public void setY(float y) {pos.y = y;}



    @Override
    public void update() {


     // RANDOM WALK
        randomWalk();
        vel.add(accel);
        vel.limit(maxSpeed);
        pos.add(vel);
        accel.mult(0);


     // CHECK FOR WALLS
        // CHECK FOR PLAYER


    }

    @Override
    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public void applyRepeller(Wall wall) {

    MathVector force = wall.repel(this);
    this.applyForce(force);

    }

    @Override
    public void applyForce(MathVector force) {

    force.limit(maxForce);
    accel.add(force);


    }

    @Override
    public void setDownN(boolean downN) {
this.downN = downN;
    }

    @Override
    public void setRightN(boolean rightN) {
this.rightN = rightN;
    }

    @Override
    public void setLeftN(boolean leftN) {
this.leftN = leftN;
    }

    @Override
    public void setTopN(boolean topN) {
this.topN = topN;
    }

    @Override
    public boolean hasRightN() {
        return rightN;
    }

    @Override
    public boolean hasLeftN() {
        return leftN;
    }

    @Override
    public boolean hasTopN() {
        return topN;
    }

    @Override
    public boolean hasDownN() {
        return downN;
    }

    @Override
    public boolean atWall(Wall w) {
        if(this.pos.x == w.getX() && this.pos.y == w.getY()) {
            return true;

        }else{
            return false;
        }
    }

    @Override
 public String getDir() {
  return dir;
 }




}
