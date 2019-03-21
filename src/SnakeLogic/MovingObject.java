package SnakeLogic;

public abstract class MovingObject implements GameObject{

    private MathVector pos, vel, accel;
    private MathVector up, down, right, left;
    private int maxForce, maxSpeed;
    private String dir;


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
        maxForce = 6;

    }

    @Override
    public int getX() {return pos.x;}
    @Override
    public int getY() {return pos.y;}
    @Override
    public void setX(int x) {this.pos.x = x;}
    @Override
    public void setY(int y) {
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


    @Override
    public void applyRepeller(Grid.Tile wall) {

        MathVector force = wall.repel(this);
        this.applyForce(force);
    }


    public String atWall(Grid.Tile w){


        if(this.pos.x >= w.getX() && this.pos.x <= w.pos.x + w.getWidth() && this.pos.y <= w.getHeight()){

                return "UP";

        }

        /*
        if((this.pos.y >= w.getY() && this.pos.y <= w.getY() + 1) && this.pos.x  == w.getX() - 1) {

                return "RIGHT";

        }

        if((this.pos.y >= w.getY() && this.pos.y <= w.getY() +1) && this.pos.x == w.getX()){

                return "LEFT";

        }

        if((this.pos.x >= w.getX() && this.pos.x <= w.getX() + 1) && this.pos.y  == w.getY() - 1){

                return "DOWN";

        }
*/





        return null;

    }




}
