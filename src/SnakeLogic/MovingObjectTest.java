package SnakeLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MovingObjectTest {

    MovingObject mo1;
    MovingObject mo2;
    MovingObject mo3;


    @Before
    public void setUp(){

        mo1 = new RandomRambler(10, 10, "testerOne");


    }


    @Test
    public void setAndGetScore(){

        int testScore = 12;
        mo1.setScore(testScore);
        assertTrue(mo1.getScore() == testScore);

    }

    @Test
    public void updateTest(){

        MathVector startVel = new MathVector(0, 0);
        MathVector startAccel = new MathVector(0, 0);

        assertEquals(mo1.getVel(), startVel);
        assertEquals(mo1.getAccel(), startAccel);




    }
}
