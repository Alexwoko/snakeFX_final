package SnakeLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pathfinder {


    private Grid myGrid;
    private MovingObject seeker;
    private MovingObject target;
    private LinkedList<GraphItem> thePath;
    private String algorithm;

    public Pathfinder(MovingObject seeker, String algorithm, Grid grid){

        this.algorithm = algorithm;
        this.seeker = seeker;


    }


    public void findPath(MovingObject target, String algorithm){

        this.target = target;

        if(algorithm.equals("DEPTH FIRST SEARCH")){
            depthFirstSearch(seeker, this.target);

        }
    }

    public boolean isWalkable(Node n){

        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for(int j = 0; j < myGrid.getFrameHeight(); j++){

                if(n.getX() == i && n.getY() == j && myGrid.nodes[i][j].getWalkable()){
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

                    if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20 && !myGrid.nodes[(int)checkX][(int)checkY].getPrevVisited()) ) {

                        Node nTwo = myGrid.nodes[(int) checkX][(int) checkY];
                        //  nTwo.setVisited(true);
                        nTwo.setPrevVisited(true);

                        if (nTwo.getX() < n.getX()) {
                            //  nTwo.setIAmWest(true);
                            nTwo.assignDirFromOrigin("WEST");
                            neighbours.add(nTwo);
                        }
                        if (nTwo.getY() < n.getY()) {
                            //  nTwo.setIAmNorth(true);
                            nTwo.assignDirFromOrigin("NORTH");
                            neighbours.add(nTwo);
                        }
                        if (nTwo.getX() > n.getX()) {
                            //  nTwo.setIAmEast(true);
                            nTwo.assignDirFromOrigin("EAST");
                            neighbours.add(nTwo);
                        }
                        if (nTwo.getY() > n.getY()) {
                            //  nTwo.setIAmSouth(true);
                            nTwo.assignDirFromOrigin("SOUTH");
                            neighbours.add(nTwo);
                        }
                    }
                }
            }
        }
        return neighbours;
    }


    public void depthFirstSearch(MovingObject seeker, MovingObject target){







    }




}
