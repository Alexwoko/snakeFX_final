package SnakeLogic;

import java.util.Random;

public class RandomRambler implements GameObject {


   private MathVector pos;
   private int velX;
   private int velY;


private String dir;
 //  private int preX;
  // private int preY;


public RandomRambler(int x, int y){

    pos.x = x;
    pos.y = y;

 velX = 1;
 velY = 1;

}
@Override
public void moveRight(){
    setDir("RIGHT");
   // x += 1;
}

@Override
public void moveLeft(){
    setDir("LEFT");
  //  x -= 1;
}
@Override
public void moveUp(){
    setDir("UP");
   // y -= 1;
}
@Override
public void moveDown(){
    setDir("DOWN");
    //y += 1;
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
    public void setX(int x) {pos.x = x;}

    @Override
    public void setY(int y) {pos.y = y;}

    /*
 public int getPreX() {return preX;}

 public void setPreX(int preX) {this.preX = preX;}

 public int getPreY() {return preY;}

 public void setPreY(int preY) {this.preY = preY;}
 */

    @Override
    public void update() {


     // RANDOM WALK
        randomWalk();

     // CHECK FOR WALLS
        // CHECK FOR PLAYER


    }

    @Override
    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
 public String getDir() {
  return dir;
 }




}
