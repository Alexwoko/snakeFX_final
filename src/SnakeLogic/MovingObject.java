package SnakeLogic;

import java.util.ArrayList;
import java.util.List;

public abstract class MovingObject implements GameObject{

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

   // Grid.Tile currentTile;
    Node currentNode;
    private Tree<TreeItem> closedList;
    private List<TreeItem> myPath;



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

       // closedList = new Tree<>(0, 1f);

    }


    public void setMyPath(List<TreeItem> path){myPath = path;}
    public List<TreeItem> getMyPath(){return  myPath;}
    public void setTree(Tree tree ){this.closedList = tree;}
    public Tree getTree(){return closedList;}

    public Node getCurrentTile(){return currentNode;}
    public void setCurrentTile(Node t){currentNode = t;}


public void stopMoving(String t){


        if (t == "up" || t == "right" || t == "left" || t== "down"){
           // System.out.println("UUUUUUUUUUPPPPPPPPPPPPPPPP!!!");
            this.vel.mult(0);
            setAccel(new MathVector(0, 0));
            this.accel.mult(0);
        }
}


public void setVel(MathVector newVal){vel = newVal;}
    public void setAccel(MathVector newVal){accel = newVal;}
public MathVector getVel(){return vel;}
public MathVector getAccel(){return accel;}

   @Override
   public float getVelX(){return vel.x;}
   @Override
   public float getVelY(){return vel.y;}
   @Override
   public void setVelX(float velX){this.vel.x = velX;}
   @Override
   public void setVelY(float velY){this.vel.y = velY;}

   public void setMaxSpeed(float max){this.maxSpeed = max;}

    @Override
    public float getX() {return pos.x;}
    @Override
    public float getY() {return pos.y;}
    @Override
    public void setX(float x) {this.pos.x = x;}
    @Override
    public void setY(float y) {
        this.pos.y = y;
    }
    @Override
    public String getDir() {
        return dir;
    }
    public void setDir(String dir){this.dir = dir;}
    public MathVector getPos(){return pos;}

    @Override
    public void update() {

        vel.add(accel);
        vel.limit(maxSpeed);
        pos.add(vel);
        accel.mult(0);
    }




    @Override
    public void applyForce(MathVector force) {

        force.limit(maxForce);
        accel.add(force);
    }

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


   // @Override
    public void applyRepeller(Node node) {

        MathVector force = node.repel(this);
        this.applyForce(force);
    }





}
