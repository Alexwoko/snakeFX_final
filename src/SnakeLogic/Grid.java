package SnakeLogic;

import java.util.*;

public class Grid {


    public Tile[][] tiles;
    private int frameWidth, frameHeight;
    private int maxWallsInLine;
    private int maxAmount;
    private int numOFUnwalks;
    private List<Tile> unwalks = new ArrayList<>();

    private List<Tile> openList;
    private List<Tile> closedList;
 //   public Stack<Tile> thePath;
    private int pathCounter;
    public List<Tile> thePath;



    private String buildDir;



    public Grid(){

        numOFUnwalks = 0;
        this.maxAmount = 0;
        frameWidth = 30;
        frameHeight = 20;
        tiles = new Tile[frameWidth][frameHeight];
        maxWallsInLine = 4;
        pathCounter = 0;
        createGrid();
        createFrame();
        createMaze();
        System.out.println(numOFUnwalkN(tiles[7][0]));
        // System.out.println(hasNeighbour(tiles[frameWidth-1][5]));
        //  System.out.println(maxInLine(tiles[7][0]));


    }

    public int getPathSize(){

            return pathCounter;

       }
    public int getFrameWidth(){return  frameWidth;}
    public int getFrameHeight(){return frameHeight;}

public Tile[][] getTiles(){return tiles;}


    public void retracePath(Tile starNode, Tile endNode){

       // List<Tile> thePath = new ArrayList<>();
        Tile currentNode = endNode;
        thePath = new ArrayList<>();

        while(!currentNode.equals(starNode)){
          //  pathCounter += 1;
            thePath.add(currentNode);
            currentNode = currentNode.parent;

        }
        Collections.reverse(thePath);
       // this.thePath = thePath;


    }


    public void BFS(MathVector startPos, MathVector endPos){

        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        Tile origin = new Tile((int)startPos.x, (int)startPos.y);
        Tile target = new Tile((int)endPos.x, (int)endPos.y);
     //   Tile target;

        Tile currentTile;
        origin.setMoveCost(Float.MAX_VALUE);

        openList.add(origin);

        while(!openList.isEmpty()){

            currentTile = openList.get(0);

            openList.remove(currentTile);
            closedList.add(currentTile);

            if(currentTile.pos.x == target.pos.x && currentTile.pos.y == target.pos.y){


                target = currentTile;

                retracePath(origin, target);
                return;
            }

            for(Tile t : getNeighbours(currentTile)){

                float newMoveCost =  getDistance(currentTile, t);

                if(closedList.contains(t) || t.getUnwalkable()){
                    continue;
                }

                if(!openList.contains(t) || newMoveCost < currentTile.getMoveCost()){

                t.setMoveCost(newMoveCost);
                t.parent = currentTile;

                if(!openList.contains(t))
                    openList.add(t);
                }
            }
        }
    }



    public List<Tile> getNeighbours(Tile t){

        List<Grid.Tile> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

             //  if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                if(i == 0 && j == 0){
                    continue;

                }else{

                    float checkX = t.pos.x +i;
                    float checkY = t.pos.y + j;

                    if(checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20){
                        neighbours.add(tiles[(int)checkX][(int)checkY]);

                    }
                }
            }
        }
        return neighbours;
    }




    private void createMaze(){

        tiles[5][1].setUnwalkable(true);
        tiles[5][2].setUnwalkable(true);

        buildWall(tiles[5][2]);


    }

    public String scanForWalls(MovingObject o){

        int checkX;
        int checkY;

        Tile left, right, up, down;

        for(int i = -1; i <= 1; i+=1f){
            for(int j = -1; j <= 1; j+=1f){

                checkX = (int)o.getX() + i;
                checkY = (int)o.getY() + j;


                if(checkX >= 0 && checkX <= frameWidth && checkY >= 0 && checkY <= frameHeight) {

                    if (checkX == o.getX() + 1 && checkY == o.getY() && tiles[checkX][checkY].getUnwalkable() && o.getDir() == "RIGHT") {
                        right = tiles[checkX][checkY];
                        o.applyRepeller(right);
                        return "right";
                    }
                    if (checkX == o.getX() -1 && checkY == o.getY() && tiles[checkX][checkY].getUnwalkable() && o.getDir() == "LEFT") {
                        left = tiles[checkX][checkY];
                        o.applyRepeller(left);
                        return "left";

                    }


                    if (checkX == o.getX() && checkY == o.getY() -1 && tiles[checkX][checkY].getUnwalkable()&& o.getDir() == "UP") {
                        up = tiles[checkX][checkY];
                        o.applyRepeller(up);
                        return "up";

                    }
                    if (checkX == o.getX() && checkY == o.getY() + 1 && tiles[checkX][checkY].getUnwalkable()&& o.getDir() == "DOWN") {
                        down = tiles[checkX][checkY];
                        o.applyRepeller(down);
                        return "down";

                    }

                }
            }
        }
return null;

    }

    public void playerScanner(MovingObject o){

     for(int i = 0; i < frameWidth; i+=1f){
         for(int j = 0; j <  frameHeight; j+=1f){

             if(o.getX() == i && o.getY() == j && !tiles[i][j].getUnwalkable()){

                 o.stopMoving(scanForWalls(o));

             }
         }
     }
    }

    public float ranNumInRange(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("Minimum must be smaller than maximum");
        }else{
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
    }

    private void buildWall(Tile tile){

        float ranX = ranNumInRange(-1, 1);
        float ranY = ranNumInRange(-1, 1);

        ranX = (int)tile.pos.x + ranX;
        ranY = (int)tile.pos.y + ranY;


        if(ranX == tile.pos.x && ranY == tile.pos.y){
            buildWall(tile);
        }


        if(numOFUnwalks == 130) {
            return;
        }

        if (ranX >= 0 && ranX <= frameWidth - 1 && ranY >= 0 && ranY <= frameHeight - 1) {

            if (numOFUnwalkN(tile) <= 2 && numOFUnwalkN(tiles[(int)ranX][(int)ranY]) <= 1 && !unwalks.contains(tiles[(int)ranX][(int)ranY])) {

                unwalks.add(tiles[(int)ranX][(int)ranY]);
                numOFUnwalks += 1;
                tiles[(int)ranX][(int)ranY].setUnwalkable(true);
                buildWall(tiles[(int)ranX][(int)ranY]);

            } else {
                buildWall(tiles[(int)ranX][(int)ranY]);
            }
        }


    }

    private int numOFUnwalkN(Tile tile){

        int checkX;
        int checkY;

        int myCount = 0;


        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){

                if( i == 0 && j == 0){
                    continue;

                } else{

                    checkX = (int)tile.pos.x + i;
                    checkY = (int)tile.pos.y + j;

                    if(checkX >= 0 && checkX <= frameWidth-1 && checkY >= 0 && checkY <= frameHeight-1){
                        if(tiles[checkX][checkY].getUnwalkable())
                            myCount += 1;


                    }
                }
            }
        }
        return myCount;
    }


    private void createFrame(){

        for(int i = 0; i < frameWidth; i+=1f){


            tiles[i][0].setUnwalkable(true);
            tiles[i][frameHeight-1].setUnwalkable(true);
            unwalks.add(tiles[i][0]);
            unwalks.add(tiles[i][frameHeight -1]);


        }

        for (int i = 0; i < frameHeight; i+= 1f){

            tiles[0][i].setUnwalkable(true);
            tiles[frameWidth-1][i].setUnwalkable(true);
            unwalks.add(tiles[0][i]);
            unwalks.add(tiles[frameWidth - 1][i]);

        }


    }


    public void createGrid(){


        for(int i = 0; i < frameWidth; i+=1f){
            for(int j = 0; j < frameHeight; j+=1f){

                tiles[i][j] = new Tile(i, j);

            }

        }
    }


    private float getDistance(Tile tileA, Tile tileB){
// Math abs så vi sikrer at tallet er positivt.
    float dstX = Math.abs(tileA.pos.x - tileB.pos.x);
    float dstY = Math.abs(tileA.pos.y - tileB.pos.y);

    if(dstX > dstY){
        return 14 * dstY + 10 * (dstX - dstY);
    }
return 14 * dstX + 10 * (dstY - dstX);


    }



    /**
     * PRIVATE CLASS -----------------------------------------
     *
     *
     */



    public class Tile{

        MathVector pos;
        int width;
        float height;
        Boolean unwalkable, aWall;
        int nCounter;
        private float strength;
        private Tile parent;
        private float moveCost;

        public Tile(int x, int y){

            pos = new MathVector(x, y);
            width = 20;
            height = 17.5f;
            unwalkable = false;
          strength = 2.9f;
          moveCost = 0;


        }

        public void setMoveCost(float cost){moveCost = cost;}
        public float getMoveCost(){return moveCost;}
        public void setPos(MathVector pos){this.pos = pos;}
        public int getNCounter(){return nCounter;}
        public void setNCounter(int n){nCounter = n;}
        public float getWidth(){return width;}
        public float getHeight(){return height;}
        public float getX(){return pos.x;}
        public float getY(){return pos.y;}
        public MathVector getPos(){return pos;}
        public void setUnwalkable(boolean unW){this.unwalkable = unW;}

        public MathVector repel(MovingObject o){

            //  MathVector dir = new MathVector(0, 0);
            MathVector dir;
            MathVector oPos = new MathVector(o.getX(), o.getY());
            dir = oPos.sub(this.pos);


            float d = (float)dir.mag();
            d = constrain(d, 1, 2);
            dir.normalize();
            float force =    strength / (d * d);
            dir.mult((int)force);
           System.out.println(dir);
            return dir;

        }

        public float constrain(float x, float a, float b){

            if(x < a){
                return a;
            }
            if(b < x){
                return b;
            }else{
                return x;
            }
        }
        

        public Boolean getUnwalkable() {return unwalkable;}

        public String toString(){

            StringBuilder sb = new StringBuilder();

            sb.append(" x = " + this.pos.x + " y = " + this.pos.y);
            sb.append( " unwalkable = " + this.unwalkable);

            return sb.toString();


        }
    }

}
