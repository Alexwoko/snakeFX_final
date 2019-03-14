package SnakeLogic;

public class Player implements GameObject{


    String dir;

    private MathVector pos, vel, accel;
    private MathVector up, down, left, right;
    private int maxSpeed;
    private int maxForce;
    private boolean leftN, rightN, topN, downN;



    public Player(int x, int y){

        pos = new MathVector(x, y);
        vel = new MathVector(0, 0);
        accel = new MathVector(0, 0);


       left = new MathVector(-1, 0);
        right = new MathVector(1, 0);
        up = new MathVector(0, -1);
        down = new MathVector(0, 1);

        leftN = false;
        rightN = false;
        topN = false;
        downN = false;

        dir = null;
        maxSpeed = 1;
        maxForce = 1;

    }


    public boolean hasDownN(){
        return downN;
    }
    public boolean hasTopN(){
        return topN;
    }
    public boolean hasRightN(){
        return rightN;
    }
    public boolean hasLeftN(){
        return leftN;
    }
    public void setTopN(boolean topN){this.topN = topN;}
    public void setDownN(boolean downN){this.downN = downN;}
    public void setLeftN(boolean leftN){this.leftN = leftN;}
    public void setRightN(boolean rightN){this.rightN = rightN;}


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
    public void update() {

      //  System.out.println(getX() + getY());

pos.x = (int)pos.x;
pos.y = (int)pos.y;


        vel.add(accel);
        vel.limit(maxSpeed);
        pos.add(vel);
       accel.mult(0);
    }




    @Override
    public String getDir() {
        return dir;
    }

    public void setDir(String dir){this.dir = dir;}

    @Override
    public void applyRepeller(Wall wall) {

        MathVector force = wall.repel(this);
        this.applyForce(force);


    }


    @Override
    public void moveRight(){

   // if(!hasRightN())
        setDir("RIGHT");
        vel.y = 0;
    applyForce(right);

    }
@Override
    public void moveLeft(){

   // if(!hasLeftN())
        setDir("LEFT");
    vel.y = 0;
       applyForce(left);

    }
    @Override
    public void moveUp(){

       // if(!hasTopN())
            setDir("UP");
        vel.x = 0;


    applyForce(up);

    }
    @Override
    public void moveDown(){

     //   if(!hasDownN())
            setDir("DOWN");
        vel.x = 0;
   applyForce(down);

    }

    public void applyForce(MathVector force){

       force.limit(maxForce);
        this.accel.add(force);

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

public String atWall(Wall w){

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
                System.out.println("First part working");
                if(w.getAxis().equals("HORIZONTAL")) {
                    return "DOWN";
                }
            }






        return null;

}


}
