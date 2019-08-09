package SnakeLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MovingObjectTest {

    MovingObject mo1;
    List<GraphItem> testPath;


    @Before
    public void setUp(){

        mo1 = new RandomRambler(10, 10, "testerOne");
        testPath = new ArrayList<>();
        GNode nONe = new GNode(1, 1, 0);
        GNode nTwo = new GNode(1, 2, 1);
        testPath.add(nONe);
        testPath.add(nTwo);

    }


    @Test
    public void setAndGetScore(){

        int testScore = 12;
        mo1.setScore(testScore);
        assertTrue(mo1.getScore() == testScore);

    }


    @Test
    public void moveStopAndUpdate(){

      mo1.moveLeft();

      assertEquals(mo1.getAccel().x, -1, 0);
      assertEquals(mo1.getVel().x, 0, 0);

      mo1.update();

      assertEquals(mo1.getAccel().x, 0, 0);
      assertEquals(mo1.getVel().x, -1, 0);

      mo1.stopMoving();

      assertEquals(mo1.getVel().x, 0, 0);

    }

    @Test
    public void getDirTest(){

        mo1.moveRight();
        assertEquals(mo1.getDir(), "RIGHT");

    }

    @Test
    public  void getPos(){


assertEquals(mo1.getPos().x, 10, 0);
assertEquals(mo1.getPos().y, 10, 0);

    }

    @Test
    public void applyForceTest(){

        mo1.stopMoving();
        assertEquals(mo1.getAccel().x, 0, 0);
        assertEquals(mo1.getAccel().y, 0, 0);

        MathVector testForce = new MathVector(1, 0);
        mo1.applyForce(testForce);

        assertEquals(mo1.getAccel().x, 1, 0);


    }

    @Test
    public void pathTest(){

        mo1.setMyPath(testPath);
        assertEquals(mo1.getMyPath(), testPath);

    }

    @Test
    public void moveUpTest(){

        mo1.moveUp();
        assertEquals(mo1.getAccel().y, -1, 0);

    }

    @Test
    public void moveDownTest(){

        mo1.moveDown();
        assertEquals(mo1.getAccel().y, 1, 0);

    }

    @Test
    public void moveRightTest(){

        mo1.moveRight();
        assertEquals(mo1.getAccel().x, 1, 0);

    }




}
