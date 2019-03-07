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
         setProportions();
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


    public void setProportions(){

       int finalWidth = ranNumInRange(2, 25);
       int finalHeight = ranNumInRange(2, 25);

       // Horizontal
       if(finalWidth > finalHeight){
           finalHeight = 2;
           // Vertical
       } else if(finalHeight > finalWidth){
           finalWidth = 2;
       }

       width = finalWidth;
       height = finalHeight;

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
