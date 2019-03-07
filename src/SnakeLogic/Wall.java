package SnakeLogic;

import javafx.scene.paint.Color;

import java.util.Random;

public class Wall extends Item{


   private int width;
   private int height;


    public Wall(javafx.scene.paint.Color color, int x, int y) {

       // set color and position
        super(color, x, y);
        // set proportions
       //  setProportions();
    }

    public void update(){

    this.setX(this.getX());
    this.setY(this.getY());
    this.width = getWidth();
    this.height = getHeight();


    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setX(int x){super.setX(x);}
    @Override
    public void setY(int y){super.setY(y);}
    @Override
    public int getX(){return super.getX();}
    @Override
    public  int getY(){return super.getY();}


    public void setProportions(){

       int finalWidth = ranNumInRange(20, 30);
       int finalHeight = ranNumInRange(20, 30);

       // Horizontal
       if(finalWidth >= finalHeight){
           finalHeight = 1;
           // Vertical
       } else if(finalHeight > finalWidth){
           finalWidth = 1;
       }

      this.setWidth(finalWidth);
      this.setHeight(finalHeight);

    }

public void connectWall(Wall wall){

        Random ran = new Random();
        float result = ran.nextInt(1);


        //Horizontal
    if(wall.getWidth()  >= wall.getHeight() ){
        if(result >= 0.5) {
            this.setX((wall.getX() + wall.getWidth()));
            this.setY(wall.getY());
        }else if(result < 0.5){
            this.setX((wall.getX() + wall.getWidth()));
           // this.setX(wall.getX());
            this.setY(-wall.getY());
        }

//VERTICAL
    } else if(wall.getHeight() > wall.getWidth() ){
        if(result >= 0.5) {
            this.setY((wall.getY() + wall.getHeight()));
            this.setX(wall.getX());
        }else if(result < 0.5){
            this.setY((wall.getY() + wall.getHeight()));
            this.setX(-wall.getX());
        }
    }
    System.out.println("Connecting " + this.getX() + " to " + wall.getX());
    System.out.println("Connecting " + this.getY() + " to " + wall.getY());
}

    public int ranNumInRange(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("Minimum must be smaller than maximum");
        }else{
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }


    }


}
