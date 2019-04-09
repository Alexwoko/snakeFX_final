package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RandomRambler extends MovingObject {

    private double fieldWidth, fieldHeight;



    public RandomRambler(int x, int y) {
        super(x, y);
        setMaxSpeed(1f);
        fieldWidth = 20;
        fieldHeight = 17.85;


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




    public void followPath(){

        if(getMyPath() != null){

            for(int i = 0; i < getMyPath().size(); i++){

                Grid.Tile t = getMyPath().get(i); //    path.get(i);
                this.setX(t.getX());
                this.setY(t.getY());
                break;

            }


        }
    }

    public void displaySelf(GraphicsContext g, Color c){


        g.setFill(c);
        g.fillRoundRect(this.getX() * fieldWidth, this.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

    }

    public void displayPath(GraphicsContext g, Color c){

        if(this.getMyPath() != null) {
            for (Grid.Tile t : this.getMyPath()) {
                g.setFill(c);
                g.fillRoundRect(t.getX() * fieldWidth, t.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

            }
        }
    }

@Override
    public void update(){


   getVel().add(getAccel());
   getVel().limit(1f);
   getPos().add(getVel());
   getAccel().mult(0f);




}


}
