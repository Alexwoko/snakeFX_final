package SnakeLogic;

import java.util.*;

public class RandomRambler implements GameObject {


    private MathVector pos, vel, accel;
    private MathVector up, down, right, left;
    private int maxForce, maxSpeed;
    private String dir;

    public RandomRambler(int x, int y){

        pos = new MathVector(x, y);
        vel = new MathVector(0, 0);
        accel = new MathVector(0, 0);

        up = new MathVector(0, -1);
        down = new MathVector(0, 1);
        left = new MathVector(-1, 0);
        right = new MathVector(1, 0);

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
    public int getX() {return pos.x;}

    @Override
    public int getY() {return pos.y;}

    @Override
    public void setX(int x) {pos.x = x;}

    @Override
    public void setY(int y) {pos.y = y;}



    @Override
    public void update() {

        vel.add(accel);
        vel.limit(maxSpeed);
        pos.add(vel);
        accel.mult(0);

    }

    public MathVector getPos(){return pos;}

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
    public String atWall(Wall w) {

        if((this.pos.x >= w.getX() && this.pos.x <= w.getX() + 1) && this.pos.y == w.getY()){

            if(w.getAxis().equals("HORIZONTAL")) {
                return "UP";
            }
        }

        if((this.pos.y >= w.getY() && this.pos.y <= w.getY() + 1) && this.pos.x  == w.getX() - 1) {
            if (w.getAxis().equals("VERTICAL")) {
                return "RIGHT";

            }
        }

        if((this.pos.y >= w.getY() && this.pos.y <= w.getY() +1) && this.pos.x == w.getX()){


            if(w.getAxis().equals("VERTICAL")){
                return "LEFT";
            }

        }

        if((this.pos.x >= w.getX() && this.pos.x <= w.getX() + 1) && this.pos.y  == w.getY() - 1){
            if(w.getAxis().equals("HORIZONTAL")) {
                return "DOWN";
            }
        }

        return null;
    }

    @Override
    public String getDir() {

        return dir;
    }

public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("X = " + pos.x);
        sb.append(" Y = " + pos.y);

        return sb.toString();


}


}
