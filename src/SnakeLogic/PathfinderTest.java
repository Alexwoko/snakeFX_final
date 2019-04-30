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


    @Before
public void setUp(){
        ran = new RandomRambler(13, 9, "One" );
        player = new Player(13, 14);
        algorithmOne = "DEPTH FIRST SEARCH";
        algorithmTwo = "BREADTH FIRST SEARCH";
        algorithmThree = "BEST FIRST SEARCH";

    }

    @Test
    public void testDepthFirst(){


pathfinder.findPath(ran, player, algorithmOne);
Assert.assertEquals(ran.getPos(), player.getPos(), player.getPos());



    }





}