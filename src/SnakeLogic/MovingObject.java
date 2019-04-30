package SnakeLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 *  Abstract class MovingObject --> implements GameObject
 *
 */

public abstract class MovingObject implements GameObject{

    /**
     * Class Variables
     *
     */

    private MathVector pos;
    private MathVector vel;
    private MathVector accel;
    private MathVector up;
    private MathVector down;
    private MathVector right;
    private MathVector left;
    private float maxForce;
    private float maxSpeed;
    private String dir;
    private List<GraphItem> myPath;
    private GNode[][] visited;
    private String name;
    private int score;

    /**
     *
     * class constructor
     * @param x position X
     * @param y position Y
     */

    public MovingObject(int x, int y){

        pos = new MathVector(x, y);
        vel = new MathVector(0, 0);
        accel = new MathVector(0, 0);

        left = new MathVector(-1, 0);
        right = new MathVector(1, 0);
        up = new MathVector(0, -1);
        down = new MathVector(0, 1);

        dir = null;
        maxSpeed = 1;
        maxForce = 2;
        myPath = new ArrayList<>();
        visited = new GNode[30][20];
        score = 0;

    }


    /**
     *  Setter score
     * @param  score
     */

    public void setScore(int score){this.score = score;}

    /**
     * Getter score
     * @return int score
     */

    public int getScore(){return score;}

    /**
     * Setter for list of nodes in path
     * @param path
     */

    public void setMyPath(List<GraphItem> path){myPath = path;}

    /**
     * Getter for list of nodes in path
     * @return List
     */

    public List<GraphItem> getMyPath(){return  myPath;}

    /**
     * Stop movement
     * @param t
     */

public void stopMoving(String t){


        if (t == "up" || t == "right" || t == "left" || t== "down"){

            this.vel.mult(0);
            this.accel.mult(0);
        }
}

    /**
     * getter for velocity
     * @return velocity
     */

public MathVector getVel(){return vel;}

    /**
     * Getter for acceleration
     * @return acceleration
     */

public MathVector getAccel(){return accel;}

    /**
     * Setter for maximum speed
     * @param max
     */

   public void setMaxSpeed(float max){this.maxSpeed = max;}

    /**
     * getter for position X
     * @return position X
     */

    @Override
    public float getX() {return pos.x;}

    /**
     * getter for position Y
     * @return position Y
     */

    @Override
    public float getY() {return pos.y;}

    /**
     * Setter for position X
     * @param x
     */

    @Override
    public void setX(float x) {this.pos.x = x;}

    /**
     * Setter for position Y
     * @param y
     */

    @Override
    public void setY(float y) {this.pos.y = y; }


    /**
     * Getter for direction
     * @return direction
     */

    @Override
    public String getDir() {
        return dir;
    }

    /**
     * Setter for Direction
     * @param dir
     */

    public void setDir(String dir){this.dir = dir;}

    /**
     * Getter for full MathVector position
     * @return
     */

    public MathVector getPos(){return pos;}

    /**
     * Update method - updates velocity and position and sets acceleration to zero so it doesn't accumulate
     */

    @Override
    public void update() {

        vel.add(accel);
        vel.limit(maxSpeed);
        pos.add(vel);
        accel.mult(0);
    }

    /**
     * Apply force method - mainly for applying acceleration
     * @param force
     */

    @Override
    public void applyForce(MathVector force) {

        force.limit(maxForce);
        accel.add(force);
    }

    /**
     * To string method
     * @return
     */

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("x = " + pos.x);
        sb.append(" y = " + pos.y);
        sb.append(" vel x = " + vel.x);
        sb.append(" vel y = " + vel.y);
        sb.append(" accel x = " + accel.x);
        sb.append(" accel y = " + accel.y);

        return sb.toString();
    }


    public void stop(){
        vel.x = 0;
        vel.y = 0;
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

    public void applyRepeller(GNode node) {

        MathVector force = node.repel(this);
        this.applyForce(force);
    }

}
