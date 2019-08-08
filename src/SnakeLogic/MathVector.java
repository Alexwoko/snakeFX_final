package SnakeLogic;

/**
 * MathVector class
 */

public class MathVector {

    public float x, y;


    /**
     * Constructor
     * @param x pos
     * @param y pos
     */

    public MathVector(float x, float y){

        this.x = x;
        this.y = y;

    }

    public String toString(){

        return "Vector value = " + "X" + " " + x + "Y" + " " + y;

    }

    /**
     * Add two vectors
     * @param in MathVector
     * @return result MathVector
     */


    public MathVector add(MathVector in){

        this.x = this.x + in.x;
        this.y = this.y + in.y;
        return new MathVector(this.x, this.y);
    }

    /**
     * Subtract two vectors
     * @param in MathVector
     * @return result MathVector
     */

    public MathVector sub(MathVector in){

        this.x = this.x - in.x;
        this.y = this.y - in.y;
        return new MathVector(this.x, this.y);
    }

    /**
     * Divide a vector by a single denominator
     * @param in int
     */

    public void div(int in){

        this.x = this.x / in;
        this.y = this.y / in;
    }

    /**
     * Multiply a Vector with a single float value
     * @param in
     */

    public void mult(float in){

        this.x = this.x * in;
        this.y = this.y * in;
    }

    /**
     * Get the magnitude/length of a vector. (We're calculating the hypotenuse on a triangle where x and y makes up the sides. Since A^2 + B^2 = C^2
     * then we get C = √A^2 + B^2
     * @return result double
     */

    public double mag(){

        return Math.sqrt((this.x * this.x) + (this.y * this.y));
       // return result;
    }

    /**
     * Normalizing "creates" a unit-vector with a value of one, keeping the direction of the original vector.
     *
     */

    public void normalize(){

        double m = (int)this.mag();
        if(m != 0){
            this.div((int)m);
        }
    }

    /**
     * set/change the magnitude of a Vector
     * @param magnitude
     */

    public void setMag(float magnitude){

        MathVector myV = this;
        myV.normalize();
        myV.mult(magnitude);
    }

    /**
     * Limit a vector to a max value
     * @param max
     */

    public void limit(float max){

        if(max < this.mag()){
            this.normalize();
            this.setMag(max);
        }
    }
}
