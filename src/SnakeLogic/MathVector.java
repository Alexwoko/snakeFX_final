package SnakeLogic;

public class MathVector {

    public int x, y;


    public MathVector(int x, int y){

        this.x = x;
        this.y = y;

    }

    public String toString(){

        return "Vector value = " + "X" + " " + x + "Y" + " " + y;

    }

   // public int getX(){return x;}
   // public int getY(){return y;}

    public MathVector add(MathVector in){


        this.x = this.x + in.x;
        this.y = this.y + in.y;
        MathVector result = new MathVector(this.x, this.y);
        return result;
    }

    public MathVector sub(MathVector in){


        this.x = this.x - in.x;
        this.y = this.y - in.y;
        MathVector result = new MathVector(this.x, this.y);

        return result;
    }

    /*
    public MathVector sub(MathVector one, MathVector two){

        if(one > two)
        one.x = one.x - two.x;
        one.y = one.y - two.y;




        MathVector result = new MathVector(one.x, one.y);
        return result;


    }
    */

    public void div(int in){

        this.x = this.x / in;
        this.y = this.y / in;


    }
        public void mult(int in){


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

    public void setMag(int len){

        MathVector myV = this;
        myV.normalize();
        myV.mult(len);

    }

    public void limit(int max){

        if(max < this.mag()){
            this.normalize();
            this.setMag(max);
        }
    }

    public void set(int x, int y){this.x = x; this.y = y;}
    public MathVector get(){return new MathVector(x, y);}





}
