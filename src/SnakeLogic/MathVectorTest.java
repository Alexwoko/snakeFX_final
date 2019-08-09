package SnakeLogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathVectorTest {


    MathVector a;
    MathVector b;
    int denominator;
    int multValue;

    @Before
    public void setUp()throws Exception{

        a = new MathVector(10, 10);
        b = new MathVector(20, 20);
        denominator = 2;
        multValue = 2;

    }


    @Test
    public void testVectorAdd(){

        MathVector result = new MathVector(30, 30);
        a.add(b);
        assertEquals(a.x, result.x, 0);
        assertEquals(a.y, result.y, 0);

    }

    @Test
    public void testVectorSub(){

        MathVector result = new MathVector(10, 10);
        b.sub(a);
        assertEquals(a.x, result.x, 0);
        assertEquals(a.y, result.y, 0);

    }

    @Test
    public void divideVectorTest(){

        MathVector result = new MathVector(5, 5);
        a.div(denominator);
        assertEquals(a.x, result.x, 0);
        assertEquals(a.y, result.y, 0);

    }

    @Test
    public void multiplyVectorTest(){

    MathVector result = new MathVector(20, 20);
    a.mult(multValue);
    assertEquals(a.x, result.x, 0);
    assertEquals(a.y, result.y, 0);

    }


    @Test
    public void magnitudeVectorTest(){

        double magnitude = 10 * 1.4;
        assertEquals(a.mag(), magnitude, 0.2);
    }

    @Test
    public void normalizeVectorTest(){

        MathVector unitVector = new MathVector(1, 1);
        a.normalize();
        assertEquals(a.x, unitVector.x, 0.4);
        assertEquals(a.y, unitVector.y, 0.4);

    }

    @Test
    public void setMagnitudeTest(){

        MathVector result = new MathVector(10, 10);
        MathVector starVector = new MathVector(1, 1);
        starVector.setMag(10);
        assertEquals(starVector.x, result.x, 0);
        assertEquals(starVector.y, result.y, 0);

    }

    @Test
    public void limitVectorTest(){

        float max = 4;
        a.limit(max);

        assertTrue(a.x < max);
        assertTrue(a.y < max);


    }

}
