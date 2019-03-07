package SnakeLogic;

public class Player implements GameObject{

    private int x;
    private int y;



    private int preX;

    private int preY;

    Player prevPos;

    public Player(int x, int y){

        this.x = x;
        this.y = y;
       // prevPos = new Player(0, 0);

    }

    public Player getPrevPos(){return prevPos;}
    public void setPrevPos(int x, int y){
       // prevPos.x = x;
       // prevPos.y = y;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
    this.x = x;
    }

    @Override
    public void setY(int y) {
    this.y = y;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    @Override
    public void update() {

        checkDir();
      //  prevPos.x = x;
      //  prevPos.y = y;

    }

    public String checkDir(){

        if(getX() > getPreX()){
            return "LEFT";
        }
        if(getX() < getPreX()){
            return "RIGHT";
        }
        if(getY() > getPreY()){
            return "DOWN";
        }
        if(getY() < getPreY()){
            return "UP";
        }
        return null;

    }

    public void moveRight(){
        x += 1;
    }

    public void moveLeft(){
        x -= 1;
    }
    public void moveUp(){
        y -= 1;
    }
    public void moveDown(){
        y += 1;
    }




}
