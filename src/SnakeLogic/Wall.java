package SnakeLogic;

import java.util.Random;

public class Wall extends Item{


   private float width;
   private float height;
   String axis;
   private final float strength;
    private float finalWidth, finalHeight;


    public Wall(javafx.scene.paint.Color color, int x, int y) {

       // set color and position
        super(color, x, y);
        strength = 1f;

    }

    public void update(){

    this.setX(this.getX());
    this.setY(this.getY());
    this.width = getWidth();
    this.height = getHeight();


    }

    public float getFinalWidth(){return finalWidth;}
    public float getFinalHeight(){return finalHeight;}

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setX(float x){super.setX(x);}
    @Override
    public void setY(float y){super.setY(y);}
    @Override
    public float getX(){return super.getX();}
    @Override
    public  float getY(){return super.getY();}

    public void setAxis(String axis){this.axis = axis;}
    public String getAxis(){return axis;}


    public void setProportions(){

       finalWidth = ranNumInRange(20, 30);
       finalHeight = ranNumInRange(20, 30);

       // Horizontal
       if(finalWidth >= finalHeight){
           finalHeight = 1;
           finalWidth = 40;
           setAxis("HORIZONTAL");
           // Vertical
       } else if(finalHeight > finalWidth){
           setAxis("VERTICAL");
           finalWidth = 1;
           finalHeight = 17.85f * 2;
       }

      this.setWidth(finalWidth);
      this.setHeight(finalHeight);

    }


    public int ranNumInRange(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("Minimum must be smaller than maximum");
        }else{
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }


    }


    public MathVector repel(GameObject o){

        MathVector dir = new MathVector(0, 0);
        MathVector oPos = new MathVector(o.getX(), o.getY());
        dir = oPos.sub(this.pos);

        float d = (float)dir.mag();
        d = constrain(d, 0, 1);
        dir.normalize();
        float force = 1 * strength / (d * d);
        dir.mult(force);
        return dir;

    }

    public float constrain(float x, float a, float b){

        if(x < a){
            return a;
        }
        if(b < x){
            return b;
        }else{
            return x;
        }


    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("x = " + pos.x);
        sb.append(" y = " + pos.y);
        sb.append(" Orientation = " + axis);
        return sb.toString();


    }


}
