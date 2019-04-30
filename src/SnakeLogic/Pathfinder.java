package SnakeLogic;

import java.util.*;

public class Pathfinder {


    private Grid myGrid;
    private String algorithm;
    private Graph<GraphItem> visited;
    private Stack<GNode> openList;
    private ArrayList<GNode> closedList;
    private Deque<GNode> stack = new ArrayDeque<>();
    private RandomRambler seeker;



    public Pathfinder(Grid grid){


        myGrid = grid;

        closedList = new ArrayList<>();
        openList = new Stack<>();
        visited = new Graph();

    }

    public String getAlgorithm(){return algorithm;}
    public void setAlgorithm(String algorithm){this.algorithm = algorithm;}

    public void findPath(RandomRambler seeker, MovingObject target, String algorithm){

        MovingObject endTarget = target;

        this.seeker = seeker;


        if(algorithm.equals("DEPTH FIRST SEARCH")){
            depthFirstSearch(this.seeker, endTarget);
        }

        if(algorithm.equals("BREADTH FIRST SEARCH")){
            breadthFirstSearch(this.seeker, target);
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


    private List<GNode> getNeighbours(GNode n, RandomRambler ram){

        List<GNode> neighbours = new ArrayList<>();


        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

                if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                }else{

                    float checkX = n.getX() + i;
                    float checkY = n.getY() + j;

                    if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY <= 20)){

                        GNode nTwo = myGrid.gNodes[(int) checkX][(int) checkY];

                        if(ram.getName().equals("One")){
                            nTwo.setPrevVisited(true);
                        }
                        if(ram.getName().equals("Two")){
                            nTwo.setPrevVisitedTwo(true);
                        }
                        if(ram.getName().equals("Three")){
                            nTwo.setPrevVisitedThree(true);
                        }





                        neighbours.add(nTwo);
                    }
                }
            }
        }
        return neighbours;
    }


    private void retracePath(GNode startNode, GNode targetNode){

        LinkedList<GraphItem> thePath;

        thePath = new LinkedList<>();
        GraphItem currentNode = targetNode;

        while(currentNode != startNode){

            thePath.add(currentNode);
            currentNode = currentNode.getFrom();

        }

        Collections.reverse(thePath);
        this.seeker.setMyPath(thePath);


    }

private void depthFirstSearch(RandomRambler seeker, MovingObject target){



        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

    GNode currentNode = start;

    stack.addLast(currentNode);

    while (true){

           currentNode = stack.pop();
        closedList.add(currentNode);
        currentNode.setVisited(true);

        if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){

            currentNode = end;
            retracePath(start, currentNode);
            myGrid.resetVisited();
          //  openList.clear();
            stack.clear();
            emptyList(closedList);
           return;

        } else{

            for(GNode n : getNeighbours(currentNode, seeker)){
                if(closedList.contains(n) || !n.getWalkable() || n.getVisited()){

                }else if(!stack.contains(n)){

                    n.setFrom(currentNode);
                    stack.addLast(n);

                }
            }
        }
        }
}

public void breadthFirstSearch(RandomRambler seeker, MovingObject target){

        LinkedList<GNode> nodesToVisit = new LinkedList<>();
      //  ArrayList<GNode> closedList = new ArrayList<>();


        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        GNode currentNode = start;

        nodesToVisit.add(currentNode);

        while (!nodesToVisit.isEmpty()){

            currentNode = nodesToVisit.remove(0);
            closedList.add(currentNode);

            if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){


                currentNode = end;
                retracePath(start, currentNode);
                myGrid.resetVisited();
                closedList.clear();
                nodesToVisit.clear();

            }else{

                for(GNode n : getNeighbours(currentNode, seeker)){
                    if(closedList.contains(n) || !n.getWalkable() || n.getVisited()){

                    }else if (!nodesToVisit.contains(n)){
                        n.setFrom(currentNode);
                        nodesToVisit.add(n);
                    }
                }
            }
        }
}


private void bestFirstSearch(RandomRambler seeker, MovingObject target){


        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        LinkedList<GNode> nodesToVisit = new LinkedList<>();

        nodesToVisit.add(start);

        GNode currentNode;

        while (!nodesToVisit.isEmpty()){


           currentNode = nodesToVisit.remove(0);
           closedList.add(currentNode);
           currentNode.setVisited(true);

           if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){


               currentNode = end;

           retracePath(start, end);
           




           }


        }



}






private void emptyList(List<GNode> list){

        for(int i = list.size()-1; i >= 0; i--){
            list.remove(i);

        }
}

}
