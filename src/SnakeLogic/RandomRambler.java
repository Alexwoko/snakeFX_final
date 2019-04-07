package SnakeLogic;

import java.util.*;

public class RandomRambler extends MovingObject {


    public RandomRambler(int x, int y) {
        super(x, y);
        setMaxSpeed(0.5f);
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

    public void followPath(List<Grid.Tile> path){

        for(int i = 0; i < path.size(); i++ ){

            Grid.Tile t = path.get(i);
            this.setX(t.getX());
            this.setY(t.getY());
            break;
        }




    }

@Override
    public void update(){


   getVel().add(getAccel());
   getVel().limit(0.5f);
   getPos().add(getVel());
   getAccel().mult(0f);




}


}
