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
        maxForce = 2;

    }


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

/*
    public Grid.Tile atWall(Grid.Tile[][] ts, Grid grid){

        int checkX;
        int checkY;

        for(int i = -1; i < 1; i++){
            for(int j = -1; j < 1; j++){

             if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == -1|| i == -1 && j == 1 || i==1 && j == 1){
                 continue;
             }else{

                 checkX = this.pos.x + i;
                 checkY = this.pos.y + j;

             if(checkX >= 0 && checkX <= grid.getFrameWidth() && checkY >= 0 && checkY <= grid.getFrameHeight()){
                 if(ts[checkX][checkY].getUnwalkable()){
                     if(checkY < this.pos.y){

                         return ts[checkX][checkY];
                     }
                 }


             }


            }


        }


    }

    /*
    public Boolean atWall(Grid.Tile w){


        if(this.pos.x == w.getX() && this.pos.y >= w.pos.y + w.getHeight()){

                return true;

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






        return null;

    }
*/



}
