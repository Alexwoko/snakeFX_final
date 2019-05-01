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
    Pathfinder pathfinder;
    Grid myGrid;


    @Before
public void setUp() throws Exception{
        ran = new RandomRambler(13, 9, "One" );
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
    public void retracePath(){

        Assert.assertTrue(ran.getMyPath().isEmpty());
        myGrid.getPathfinder().findPath(ran, player, "DEPTH FIRST SEARCH");
        myGrid.getPathfinder().retracePath(myGrid.getTile(ran.getX(), ran.getY()), myGrid.getTile(player.getX(), player.getY()));
        Assert.assertTrue(!ran.getMyPath().isEmpty());


    }

    @Test
    public void testDepthFirst(){

        Assert.assertTrue(ran.getMyPath().isEmpty());

        myGrid.getPathfinder().findPath(ran, player, "DEPTH FIRST SEARCH");
Assert.assertTrue(!ran.getMyPath().isEmpty());

    }

    @Test
    public void testBreadthFirst(){

        Assert.assertTrue(ran.getMyPath().isEmpty());

        myGrid.getPathfinder().findPath(ran, player, "BREADTH FIRST SEARCH");
        Assert.assertTrue(!ran.getMyPath().isEmpty());
    }

    @Test
    public void bestFirstSearch(){

        Assert.assertTrue(ran.getMyPath().isEmpty());

        myGrid.getPathfinder().findPath(ran, player, "BEST FIRST SEARCH");
        Assert.assertTrue(!ran.getMyPath().isEmpty());

    }






}