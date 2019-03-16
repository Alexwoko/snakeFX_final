package SnakeLogic;

import java.util.*;

public class RandomRambler extends MovingObject {


    public RandomRambler(int x, int y) {
        super(x, y);
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



}
