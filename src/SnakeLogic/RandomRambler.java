package SnakeLogic;

import java.util.Random;

public class RandomRambler implements GameObject {


   private int x;
   private int y;
   private int velX;
   private int velY;


private String dir;
   private int preX;
   private int preY;


public RandomRambler(int x, int y){

 this.x = x;
 this.y = y;
 velX = 1;
 velY = 1;

}
@Override
public void moveRight(){
    setDir("RIGHT");
    x += 1;
}

@Override
public void moveLeft(){
    setDir("LEFT");
    x -= 1;}
@Override
public void moveUp(){
    setDir("UP");
    y -= 1;}
@Override
public void moveDown(){
    setDir("DOWN");
    y += 1;}


/*
    @Override
    public void changeDir() {
        if(getDir().equals("RIGHT")){
            velX *= -1;
        }
        if(getDir().equals("LEFT")){
            velX *= -1;
        }
        if(getDir().equals("UP")){
            velY *= -1;
        }
        if(getDir().equals("DOWN")){
            velY *= -1;
        }

    }
    */

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
    public int getX() {return x;}

    @Override
    public int getY() {return y;}

   @Override
    public void setX(int x) {this.x = x;}

    @Override
    public void setY(int y) {this.y = y;}

 public int getPreX() {return preX;}

 public void setPreX(int preX) {this.preX = preX;}

 public int getPreY() {return preY;}

 public void setPreY(int preY) {this.preY = preY;}

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
