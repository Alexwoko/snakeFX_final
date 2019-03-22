package SnakeLogic;

import java.util.ArrayList;
import java.util.Stack;

public class BFS {


    private Grid.Tile startNode;
    private Grid.Tile endNode;
    private Grid.Tile currentTile;
    private Stack<Grid.Tile> thePath;
    private ArrayList<Grid.Tile> openList;
    private ArrayList<Grid.Tile> closedList;

    public BFS(Grid.Tile startNode, Grid.Tile endNode){

        this.startNode = startNode;
        this.endNode = endNode;
        startBFS();

    }

    public void startBFS(){

        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        Grid.Tile origin = this.startNode;
        Grid.Tile target = this.endNode;

        openList.add(origin);

        while(!openList.isEmpty()){


            currentTile = openList.get(0);
            openList.remove(currentTile);
            closedList.add(currentTile);

            if(currentTile.getX() == target.getX() && currentTile.getY() == target.getY()){

                target = currentTile;
                retracePath(origin, target);
                return;
            }

            For(Grid.Tile t : openList)

        }


    }
}
