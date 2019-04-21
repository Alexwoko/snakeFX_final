package SnakeLogic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class BFS {

    private Grid myGrid;
    private MovingObject startNode;
    private MovingObject endNode;
   // private Node currentNode;
   // private Stack<Node> thePath;
    private ArrayList<TreeItem> thePath;



    public BFS(MovingObject startNode, MovingObject endNode, Grid grid){

        this.startNode = startNode;
        this.endNode = endNode;
        this.myGrid = grid;
        thePath = new ArrayList<>();
        startBFS();

    }

 //   public void setMyGrid(Grid grid){ this.myGrid = grid;}


    public void retracePath(Node startNode, Node targetNode){

        TreeItem currentNode = targetNode;

        while(currentNode != startNode){

            this.thePath.add(currentNode);
            currentNode = currentNode.getParent();

        }

        Collections.reverse(thePath);
        this.startNode.setMyPath(thePath);


    }

    public boolean isWalkable(Node n){

        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for(int j = 0; j < myGrid.getFrameHeight(); j++){

             //   if(myGrid.tiles[i][j].walkable && (n.getX() == i && n.getY() == j)){
                if(n.getX() == i && n.getY() == j && myGrid.nodes[i][j].getWalkable()){
                    return true;

                }

            }

        }
        return false;
    }


    public void startBFS(){


        ArrayList<Node> openList;
         Tree<Node> closedList;

        openList = new ArrayList<>();
        closedList = new Tree<>();
        Node currentNode;



      //  Node origin = new Node(startNode.getX(), startNode.getY(), true, "None");
       // Node target = new Node(endNode.getX(), endNode.getY(), true, "None");

        Node origin = myGrid.getTile(startNode.getX(),startNode.getY());
        Node target = myGrid.getTile(endNode.getX(), endNode.getY());


        openList.add(origin);

        while(!openList.isEmpty()){


            currentNode = openList.get(0);
            openList.remove(currentNode);
            closedList.add(currentNode);



            if(currentNode.getX() == target.getX() && currentNode.getY() == target.getY()){

               // myGrid.setPrevVisited(myGrid.nodes);

                target = currentNode;
                retracePath(origin, target);

                closedList.emptyTree();
                myGrid.resetVisited();
                return;
            }

            for (Node n : myGrid.getNeighbours(currentNode)){

           //     System.out.println(n.getTreeIndex() + " = node index");
                if(closedList.containsValue(n.getGridIndex()) || !isWalkable(n)){
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
