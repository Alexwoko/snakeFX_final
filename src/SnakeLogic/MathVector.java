package SnakeLogic;

public class MathVector {

    public float x, y;


    public MathVector(float x, float y){

        this.x = x;
        this.y = y;

    }

    public String toString(){

        return "Vector value = " + "X" + " " + x + "Y" + " " + y;

    }

    public void setPos(MathVector pos){
        this.x = pos.x;
       this.y = pos.y;

    }

    public MathVector getPos(){


       return new MathVector(this.x, this.y);


    }


    public MathVector add(MathVector in){


        this.x = this.x + in.x;
        this.y = this.y + in.y;
        return new MathVector(this.x, this.y);

    }

    public MathVector sub(MathVector in){


        this.x = this.x - in.x;
        this.y = this.y - in.y;

       return new MathVector(this.x, this.y);

    }


    public void div(int in){

        this.x = this.x / in;
        this.y = this.y / in;


    }
    public void mult(float in){


        this.x = this.x * in;
        this.y = this.y * in;

    }

    public double mag(){

        double result = Math.sqrt((this.x * this.x) + (this.y * this.y));
        return result;

    }

    public void normalize(){

        double m = (int)this.mag();
        if(m != 0){
            this.div((int)m);
        }
    }

    public void setMag(float magnitude){

        MathVector myV = this;
        myV.normalize();
        myV.mult(magnitude);
    }

    public void limit(float max){

        if(max < this.mag()){
            this.normalize();
            this.setMag(max);
        }
    }
}
