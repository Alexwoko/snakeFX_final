package SnakeLogic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathfinderTest {

    RandomRambler ran;
    Player player;
    String algorithmOne;
    String algorithmTwo;
    String algorithmThree;
    Grid myGrid;


    @Before
public void setUp() throws Exception{
        ran = new RandomRambler(6, 9, "One" );
        player = new Player(13, 14);
        algorithmOne = "DEPTH FIRST SEARCH";
        algorithmTwo = "BREADTH FIRST SEARCH";
        algorithmThree = "BEST FIRST SEARCH";
        myGrid = new Grid();
        myGrid.createGrid();
        myGrid.buildMaze();
        myGrid.createFrame();

    }



    @Test
    public void debthFirstTest(){

    myGrid.controlTheHunt(ran, player, algorithmOne);

    Assert.assertTrue(!ran.getMyPath().isEmpty());

        float posX = ran.getMyPath().get(ran.getMyPath().size() - 1).getX();
        float posY = ran.getMyPath().get(ran.getMyPath().size() - 1).getY();

        assertEquals((double) posX, (double) player.getX(), 0);
        assertEquals((double) posY, (double)player.getY(), 0);

    }

    @Test
    public void breadthFirstSearch(){

        myGrid.controlTheHunt(ran, player, algorithmTwo);

        Assert.assertTrue(!ran.getMyPath().isEmpty());

        float targetX = ran.getMyPath().get(ran.getMyPath().size()-1).getX();
        float targetY = ran.getMyPath().get(ran.getMyPath().size()-1).getY();

        assertEquals((double) targetX, (double)player.getX(), 0);
        assertEquals((double) targetY, (double)player.getY(), 0);

    }


    @Test
    public void bestFirstSearch(){

        myGrid.controlTheHunt(ran, player, algorithmThree);

        Assert.assertTrue(!ran.getMyPath().isEmpty());

        float targetX = ran.getMyPath().get(ran.getMyPath().size()-1).getX();
        float targetY = ran.getMyPath().get(ran.getMyPath().size()-1).getY();

        assertEquals((double) targetX, (double)player.getX(), 0);
        assertEquals((double) targetY, (double)player.getY(), 0);

    }




}