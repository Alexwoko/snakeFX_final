package SnakeLogic;

import java.util.*;

public class Pathfinder {


    private Grid myGrid;
    private MovingObject seeker;
    private MovingObject target;
    private LinkedList<GraphItem> thePath;
    private String algorithm;
    private Graph<GraphItem> visited;

    public Pathfinder(Grid grid){


        myGrid = grid;


    }

    public String getAlgorithm(){return algorithm;}
    public void setAlgorithm(String algorithm){this.algorithm = algorithm;}

    public void findPath(MovingObject seeker, MovingObject target, String algorithm){

        this.target = target;
        this.seeker = seeker;

        if(algorithm.equals("DEPTH FIRST SEARCH")){
            depthFirstSearch(this.seeker, this.target);

        }
    }

    public boolean isWalkable(GNode n){

        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for(int j = 0; j < myGrid.getFrameHeight(); j++){

                if(n.getX() == i && n.getY() == j && myGrid.gNodes[i][j].getWalkable()){
                    return true;
                }
            }
        }
        return false;
    }

    public List<GNode> getNeighbours(GNode n){

        List<GNode> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){


                if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){
                    continue;
                }else{

                    float checkX = n.getX() + i;
                    float checkY = n.getY() + j;

                    if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20 && !myGrid.gNodes[(int)checkX][(int)checkY].getPrevVisited()) ) {

                        GNode nTwo = myGrid.gNodes[(int) checkX][(int) checkY];
                        //  nTwo.setVisited(true);
                        nTwo.setPrevVisited(true);
                        neighbours.add(nTwo);


                    }
                }
            }
        }
        return neighbours;
    }

    public void retracePath(GNode startNode, GNode targetNode){

        GraphItem currentNode = targetNode;

        while(currentNode != startNode){

            thePath.add(currentNode);
            currentNode = currentNode.getFrom();

        }

        Collections.reverse(thePath);
        this.seeker.setMyPath(thePath);
    }


    private void depthFirstSearch(MovingObject seeker, MovingObject target){


        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        GNode currentNode;

        Stack<GNode> stack = new Stack<>();
        visited = new Graph();

        stack.push(start);

        while (!stack.empty()){


            currentNode = stack.pop();
            visited.addNode(currentNode);

            if(currentNode.getGridIndex() == end.getGridIndex()){

                end = currentNode;



            }else{


                for(GNode n : getNeighbours(currentNode)){

                    if(visited.containsNode(n.getGridIndex()) || !isWalkable(n)){
                        continue;
                    }

                    if(!stack.contains(n)){

                        n.setFrom(currentNode);
                        stack.push(n);


                    }


                }



            }







        }







    }




}
