package SnakeLogic;


import SnakeGUI.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class BFS {

    private Grid myGrid;
    private MovingObject startNode;
    private MovingObject endNode;
    private Node currentNode;
    private Stack<Node> thePath;
    private ArrayList<Node> openList;
    // private ArrayList<Grid.Tile> closedList;
    private Tree<Node> closedList;


    public BFS(MovingObject startNode, MovingObject endNode, Grid grid){

        this.startNode = startNode;
        this.endNode = endNode;
        this.myGrid = grid;
        startBFS();

    }

 //   public void setMyGrid(Grid grid){ this.myGrid = grid;}


    public void retracePath(Node startNode, Node targetNode){

        Node currentNode = targetNode;

        while(currentNode != startNode){

            thePath.add(currentNode);
            currentNode = currentNode.getParent();

        }

        Collections.reverse(thePath);
        this.startNode.setMyPath(thePath);


    }

    public boolean isWalkable(Node n){

        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for(int j = 0; j < myGrid.getFrameHeight(); i++){

             //   if(myGrid.tiles[i][j].walkable && (n.getX() == i && n.getY() == j)){
                if(n.getX() == i && n.getY() == j && myGrid.tiles[i][j].walkable){
                    return true;

                }

            }

        }
        return false;
    }


    public void startBFS(){

        openList = new ArrayList<>();
        closedList = new Tree<>();



        Node origin = new Node(startNode.getX(), startNode.getY());
        Node target = new Node(endNode.getX(), endNode.getY());


        openList.add(origin);

        while(!openList.isEmpty()){


            currentNode = openList.get(0);
            openList.remove(currentNode);
            closedList.add(currentNode);

            if(currentNode.getX() == target.getX() && currentNode.getY() == target.getY()){

                target = currentNode;
                retracePath(origin, target);
                return;
            }


            for (Node n : myGrid.getNeighbours(currentNode)){

                if(closedList.containsValue(n) || !isWalkable(n)){
                    continue;

                }

                if(!openList.contains(n)){
                    n.setParent(currentNode);
                    openList.add(n);

                }
            }
        }
    }
}
