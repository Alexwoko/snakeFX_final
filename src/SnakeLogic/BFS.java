
/*
package SnakeLogic;

import SnakeGUI.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BFS {

    private Grid.Tile[][] tiles;
    private MovingObject startNode;
    private MovingObject endNode;
    private Grid.Tile currentTile;
    private Stack<Grid.Tile> thePath;
    private ArrayList<Grid.Tile> openList;
    private ArrayList<Grid.Tile> closedList;


    public BFS(MovingObject startNode, MovingObject endNode){

        this.startNode = startNode;
        this.endNode = endNode;
        startBFS();


    }

    public List<Grid.Tile> getNeighbours(Grid.Tile t){

        List<Grid.Tile> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

                if(t.pos.x == i && t.pos.y == j || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                }else{

                    int checkX = t.pos.x +i;
                    int checkY = t.pos.y + j;

                    if(checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20){
                        neighbours.add([checkX][checkY]);

                    }
                }
            }
        }
        return neighbours;
    }

    public void startBFS(){

        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        Grid.Tile origin = this.startNode.getPos();
      //  Grid.Tile target = this.endNode;


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

            For (Grid.Tile t : getNeighbours(currentTile)){


            }

        }


    }
}
*/