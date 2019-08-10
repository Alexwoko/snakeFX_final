package SnakeLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GNodeTest {

   GNode n;
   GraphItem fromTest;




   @Before
    public void setUp(){

       n = new GNode(10, 10,0);
       fromTest = new GNode(9, 10, 1);

   }

    @Test
   public void checkCookieTest(){

       assertTrue(!n.getHasCookie());
       n.setHasCookie(true);
       assertTrue(n.getHasCookie());

    }

    @Test
    public void moveCostTest(){

       float testCost = 10;
       n.setMoveCost(testCost);
       assertEquals(n.getMoveCost(), testCost, 0);

    }

    @Test
    public void walkableTest(){

       n.setWalkable(true);
       assertTrue(n.getWalkable());
       n.setWalkable(false);
       assertTrue(!n.getWalkable());
    }

    @Test
    public void visitedTest(){

       n.setVisited(true);
       assertTrue(n.getVisited());
       n.setVisited(false);
       assertTrue(!n.getVisited());


    }

    @Test
    public void prevVisitedTest(){

       n.setPrevVisited(true);
       assertTrue(n.getPrevVisited());
       n.setPrevVisited(false);
       assertTrue(!n.getPrevVisited());
    }

    @Test
    public void prevVisitedTwoTest(){

        n.setPrevVisitedTwo(true);
        assertTrue(n.getPrevVisitedTwo());
        n.setPrevVisitedTwo(false);
        assertTrue(!n.getPrevVisitedTwo());
    }

    @Test
    public void prevVisitedThreeTest(){

        n.setPrevVisitedThree(true);
        assertTrue(n.getPrevVisitedThree());
        n.setPrevVisitedThree(false);
        assertTrue(!n.getPrevVisitedThree());
    }

    @Test
    public void getHeightTest(){

       float height = 17.5f;
       assertEquals(n.getHeight(), height, 0);

    }

    @Test
    public void getWidthTest(){

       int width = 20;
       assertEquals(n.getWidth(), width, 0);

    }

    @Test
    public void getXTest(){

       int actual = 10;
       assertEquals(n.getX(), actual, 0);

    }

    @Test
    public void getYTest(){

       int actual = 10;
       assertEquals(n.getY(), actual, 0);

    }

    @Test
    public void nodeFromTest(){

       assertNull(n.getFrom());
       n.setFrom(fromTest);
       assertEquals(n.getFrom(), fromTest);


    }
    
}
