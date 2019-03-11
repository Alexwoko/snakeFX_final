package SnakeLogic;

public class MathVector {

    public float x, y;


    public MathVector(float x, float y){

        this.x = x;
        this.y = y;

    }

   // public int getX(){return x;}
   // public int getY(){return y;}

    public MathVector add(MathVector in){

        MathVector result = new MathVector(0, 0);
        result.x = this.x + in.x;
        result.y = this.y + in.y;

        return result;
    }

    public MathVector sub(MathVector in){

        MathVector result = new MathVector(0, 0);
        result.x = this.x - in.x;
        result.y = this.y - in.y;

        return result;
    }

    public void div(float in){

        this.x = this.x / in;
        this.y = this.y / in;


    }
        public void mult(float in){


            this.x = this.x * in;
            this.y = this.y * in;

    }

    public double mag(){

        double result;

        result = Math.sqrt((this.x * this.x) + (this.y * this.y));
        return result;

    }

    public void normalize(){

        double m = (float)this.mag();
        if(m != 0){
            this.div((float)m);
        }


    }

    public void setMag(float len){

        if(this.x > len){
            this.x = len;
        }
        if(this.x < -len){
            this.x = -len;
        }
        if(this.y > len){
            this.y = len;
        }
        if (this.y < -len){
            this.y = -len;
        }


    }

    public void limit(float max){

        if(this.mag() > max){
            setMag(max);
        }
        if(this.mag() < -max){
            setMag(-max);
        }

    }

    public void set(float x, float y){this.x = x; this.y = y;}
    public MathVector get(){return new MathVector(x, y);}





}
