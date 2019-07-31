package SnakeLogic;

import java.util.*;

/**
 * Pathfinder class
 */

public class Pathfinder {

    /**
     * class variables
     */

    private Grid myGrid;
    private Stack<GNode> openList;
    private ArrayList<GNode> closedList;
    private Deque<GNode> stack = new ArrayDeque<>();
    private RandomRambler seeker;


    /**
     * class constructor
     * @param grid
     */

    public Pathfinder(Grid grid){

        myGrid = grid;
        closedList = new ArrayList<>();
        openList = new Stack<>();

    }


    /**
     *
     * Method that starts the pathfinding and controls the algorithm to be used
     * @param seeker
     * @param target
     * @param algorithm
     */

    public void findPath(RandomRambler seeker, MovingObject target, String algorithm){

        MovingObject endTarget = target;

        this.seeker = seeker;


        if(algorithm.equals("DEPTH FIRST SEARCH")){
            depthFirstSearch(this.seeker, endTarget);
        }

        if(algorithm.equals("BREADTH FIRST SEARCH")){
            breadthFirstSearch(this.seeker, target);
        }
        if(algorithm.equals("BEST FIRST SEARCH")){

            bestFirstSearch(this.seeker, endTarget);

        }


    }

    /**
     * A method that finds and returns the neigbhours of a specific ghost
     * @param n GNode
     * @param ram RandomRambler
     * @return List
     */

    private List<GNode> getNeighbours(GNode n, RandomRambler ram){

        List<GNode> neighbours = new ArrayList<>();


        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

                if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                }else{

                    float checkX = n.getX() + i;
                    float checkY = n.getY() + j;

                    if((checkX >= 0 && checkX < 30 && checkY >= 0 && checkY <= 20)){

                        GNode nTwo = myGrid.getGNodes()[(int) checkX][(int) checkY];

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

    /**
     * A method to go back through the path, insert the nodes into the path list and
     * finally reverse the list so we can do a "walking animation" travel.
     * @param startNode GNode
     * @param targetNode GNode
     */

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

    /**
     * Depth first search
     * @param seeker RandomRambler
     * @param target MovingObject
     */

    private void depthFirstSearch(RandomRambler seeker, MovingObject target){

int visitedNodes = 0;

        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        GNode currentNode = start;

        stack.addLast(currentNode);

        while (true){

            currentNode = stack.pop();
            closedList.add(currentNode);
            currentNode.setVisited(true);
            visitedNodes++;


            if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){

                System.out.println("Depth first search visited = " + visitedNodes);
                currentNode = end;
                retracePath(start, currentNode);
                myGrid.resetVisited();
                stack.clear();
                       closedList.clear();

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

    /**
     * Breadth first search
     * @param seeker RandomRambler
     * @param target MovingObject
     */

    public void breadthFirstSearch(RandomRambler seeker, MovingObject target){


        int visitedNodes = 0;
        LinkedList<GNode> nodesToVisit = new LinkedList<>();

        GNode end;
        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());



             end = myGrid.getTile(target.getX(), target.getY());



        GNode currentNode = start;

        nodesToVisit.add(currentNode);

        while (!nodesToVisit.isEmpty()){

            currentNode = nodesToVisit.remove(0);
            closedList.add(currentNode);
            visitedNodes++;

            if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){

                System.out.println("Breadth first search visited = " + visitedNodes);

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

    /**
     * Best first search
     * @param seeker RandomRambler
     * @param target MovingObject
     */

    private void bestFirstSearch(RandomRambler seeker, MovingObject target){

        int visitedNodes = 0;

        GNode start = myGrid.getTile(seeker.getX(), seeker.getY());
        GNode end = myGrid.getTile(target.getX(), target.getY());

        LinkedList<GNode> nodesToVisit = new LinkedList<>();

        nodesToVisit.add(start);

        GNode currentNode;

        while (!nodesToVisit.isEmpty()){


            currentNode = nodesToVisit.remove(0);
            closedList.add(currentNode);
            currentNode.setVisited(true);
            visitedNodes++;

            if(currentNode.getX() == end.getX() && currentNode.getY() == end.getY()){

                System.out.println("Best first search visited = " + visitedNodes);

                currentNode = end;

                retracePath(start, currentNode);
                myGrid.resetVisited();
                closedList.clear();
                nodesToVisit.clear();

            }else{

                for(GNode n : getNeighbours(currentNode, seeker)){

                    if(closedList.contains(n) || !n.getWalkable()){
                        continue;
                    }


                    float newMoveCost = getMoveCost(currentNode, n);


                    if(newMoveCost < currentNode.getMoveCost() || !openList.contains(n)){

                        n.setMoveCost(newMoveCost);
                        n.setFrom(currentNode);

                        if(!openList.contains(n)){
                            nodesToVisit.add(n);
                        }
                    }
                }
            }
        }
    }

    /**
     * A method to return a movement cost between two nodes - used for best first
     * @param nodeA GNode
     * @param nodeB GNode
     * @return float
     */

    private float getMoveCost(GNode nodeA, GNode nodeB){

        float dstX = nodeA.getX() - nodeB.getX();
        float dstY = nodeA.getY() - nodeB.getY();

        if(dstX > dstY){

            return 14 * dstY + 10 * (dstX - dstY);

        } else{

            return 14 * dstX + 10 * (dstY - dstX);

        }
    }

}
