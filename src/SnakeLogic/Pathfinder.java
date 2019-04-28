package SnakeLogic;

import java.util.*;

public class Pathfinder {


    private Grid myGrid;
    private MovingObject seeker;
  //  private MovingObject target;
   // private LinkedList<GraphItem> thePath;
    private String algorithm;
    private Graph<GraphItem> visited;
    private Stack<GNode> openList;
    private ArrayList<GNode> closedList;
    private Deque<GNode> stack = new ArrayDeque<>();



    public Pathfinder(Grid grid){


        myGrid = grid;

        closedList = new ArrayList<>();
        openList = new Stack<>();
        visited = new Graph();

    }

    public String getAlgorithm(){return algorithm;}
    public void setAlgorithm(String algorithm){this.algorithm = algorithm;}

    public void findPath(MovingObject seeker, MovingObject target, String algorithm){

        MovingObject endTarget = target;


        this.seeker = seeker;

        if(algorithm.equals("DEPTH FIRST SEARCH")){
            depthFirstSearch(this.seeker, endTarget);
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
                 //   continue;
                }else{

                    float checkX = n.getX() + i;
                    float checkY = n.getY() + j;

                   // if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY <= 20 && !myGrid.gNodes[(int)checkX][(int)checkY].getPrevVisited()) ) {


                    if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY <= 20) ) {


                        GNode nTwo = myGrid.gNodes[(int) checkX][(int) checkY];
                        //  nTwo.setVisited(true);
                      //  nTwo.setPrevVisited(true);
                        neighbours.add(nTwo);


                    }
                }
            }
        }
        return neighbours;
    }

    public void retracePath(GNode startNode, GNode targetNode){

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

private void depthFirstSearch(MovingObject seeker, MovingObject target){


        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

    GNode currentNode = start;

      //  openList.add(start);
    //stack.push(currentNode);
    stack.addLast(currentNode);

       // while(!openList.empty()){
  //  while(!stack.isEmpty()){
    while (true){

            currentNode = stack.pop();
      //  currentNode = openList.remove(openList.size()-1);
        closedList.add(currentNode);
        currentNode.setVisited(true);


        if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){

            currentNode = end;
            retracePath(start, currentNode);
            myGrid.resetVisited();
          //  openList.clear();
            stack.clear();
            myGrid.setClosedList(closedList);
            emptyList(closedList);
           return;

        } else{

            for(GNode n : getNeighbours(currentNode)){
                if(closedList.contains(n) || !n.getWalkable() || n.getVisited()){
                 //   continue;
             //   }else if(!openList.contains(n)){
                }else if(!stack.contains(n)){

                    n.setFrom(currentNode);
                  //  openList.add(n);
                  //  stack.push(n);
                    stack.addLast(n);
                }
            }
        }
        }
}

public void emptyList(List<GNode> list){

        for(int i = list.size()-1; i >= 0; i--){
            list.remove(i);

        }
}


    /*
    private void depthFirstSearch(MovingObject seeker, MovingObject target){


        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        GNode currentNode;
        ArrayList<GNode> openList = new ArrayList<>();


        visited = new Graph();

        openList.add(start);

        while (!openList.isEmpty()){


            currentNode = openList.remove(0);
            visited.addNode(currentNode);


            if(currentNode.getGridIndex() == end.getGridIndex()){

                end = currentNode;
                retracePath(start, end);

                visited.emptyGraph();
                myGrid.resetVisited();

            }else{


                for(GNode n : getNeighbours(currentNode)){

                    if(visited.containsNode(n.getGridIndex()) || !isWalkable(n)){
                 //   if(visited.containsNode(n.getGridIndex()) || !n.getWalkable()){

                        continue;
                    }

                    if(!openList.contains(n)){

                        n.setFrom(currentNode);
                        openList.add(n);
                    }
                }
            }
        }

    }
    */

}
