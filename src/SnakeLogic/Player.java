package SnakeLogic;

public class Player implements GameObject{

    private int x; // Create a vector class
    private int y;
    private int preX;
    private int preY;
    String dir;
    private int velX;
    private int velY;
    private MathVector pos;


    private boolean moving;



    public Player(int x, int y){

        pos = new MathVector(x, y);
        this.x = x;
        this.y = y;
       // prevPos = new Player(0, 0);
        dir = null;
        velX = 0;
        velY = 0;
        moving = false;

    }



    public int getPreX() {return preX;}
    public void setPreX(int preX) {this.preX = preX;}
    public int getPreY() {return preY;}
    public void setPreY(int preY) {this.preY = preY;}
    @Override
    public int getX() {return x;}
    @Override
    public int getY() {return y; }
    @Override
    public void setX(int x) {this.x = x;}
    @Override
    public void setY(int y) {
    this.y = y;
    }

    @Override
    public void update() {

        /*
        if(moving) {
            x = x + velX;
            y = y + velY;
        }
        */
       // checkDir();

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    @Override
    public String getDir() {
        return dir;
    }

    public void setDir(String dir){this.dir = dir;}


@Override
    public void moveRight(){
    setDir("RIGHT");
    x += 1;

        velY = 0;
    }
@Override
    public void moveLeft(){
    setDir("LEFT");
       x -= 1;
        velY = 0;
    }
    @Override
    public void moveUp(){
    setDir("UP");
           y -= 1;
        velX = 0;
    }
    @Override
    public void moveDown(){
    setDir("DOWN");
           y += 1;
            velX = 0;
    }

}
