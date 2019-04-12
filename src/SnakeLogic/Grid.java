package SnakeLogic;

import java.util.*;

public class Grid {


    public Tile[][] tiles;
    private int frameWidth, frameHeight;
    public Node[][] nodes;






    public Grid(){


        frameWidth = 30;
        frameHeight = 20;
        tiles = new Tile[frameWidth][frameHeight];
        nodes = new Node[frameWidth][frameHeight];
        createGrid();
        createFrame();
        buildMaze();



    }

    public int getFrameWidth(){return  frameWidth;}
    public int getFrameHeight(){return frameHeight;}

public Tile[][] getTiles(){return tiles;}

    public List<Node> getNeighbours(Node n){

        List<Node> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

                if(n.getX() == i && n.getY() == j || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                }else{

                    float checkX = n.getX() +i;
                    float checkY = n.getY() + j;

                    if(checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20){

                        neighbours.add(nodes[(int)checkX][(int)checkY]);

                    }
                }
            }
        }
        return neighbours;
    }



/*

    public void retracePath(Tile starNode, Tile endNode, MovingObject o){

        Tile currentNode = endNode;
        thePath = new ArrayList<>();

        while(!currentNode.equals(starNode)){
            //  pathCounter += 1;
            thePath.add(currentNode);
            currentNode = currentNode.getParent();

        }
        Collections.reverse(thePath);
        o.setMyPath(thePath);

    }



    public void BFS(MovingObject hunter, MovingObject prey){

        createGrid();
        createFrame();
        buildMaze();

       tree = new Tree<>(0, 1f);
      // Tree<Float> tree = hunter.getTree();

        openList = new ArrayList<>();

        Tile origin = getTile(hunter.getPos());
        Tile target = getTile(prey.getPos());

        hunter.setCurrentTile(origin);
        prey.setCurrentTile(target);

       // hunter.setCurrentTile(origin);

        origin.setMoveCost(Float.MAX_VALUE);
        Tile currentTile;

        openList.add(origin);

        while(!openList.isEmpty()){

            currentTile = openList.get(0);
            openList.remove(currentTile);
            tree.add(currentTile.getMoveCost());

            currentTile.setIndex(tree.numOfNodes);
            hunter.setTree(tree);


            if (currentTile.pos.x == target.pos.x && currentTile.pos.y == target.pos.y) {



         //      hunter.setCurrentTile(currentTile);

            //    target = convertTileToPos();

                retracePath(origin, target, hunter);

                hunter.getTree().emptyTree();
                tree.emptyTree();

                for(int i = openList.size(); i > 0; i--){

                    openList.remove(i-1);

                }


                return;
            }


            for(Tile t : getNeighbours(currentTile)){

                if(tree.containsValue(t.index) || t.getUnwalkable()){

                    continue;
                }

                if(!openList.contains(t) || t.getMoveCost() < currentTile.getMoveCost()) {

                    t.setMoveCost(getDistance(currentTile, t));
                    t.parent = currentTile;

                    if (!openList.contains(t))
                        openList.add(t);


                }
            }
        }
    }



    public List<Tile> getNeighbours(Tile t){

        List<Grid.Tile> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

               if(i == 0 && j == 0 || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

             //   if(i == 0 && j == 0){
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
    */


    private void buildMaze(){

        int[] firstLine = {14};
        buildWallHorizLine(firstLine, 1);
        int[] secondLine = {2, 3, 5, 6, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27};
        buildWallHorizLine(secondLine, 2);
        int[] fourthLine = {2, 3, 6, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 23, 26, 27};
        buildWallHorizLine(fourthLine, 4);
        int[] fifthLine = {6, 14, 23};
        buildWallHorizLine(fifthLine, 5);
        int[] sixthLine = {1, 2, 3, 6, 7, 8, 21, 22, 23, 26, 27, 28};
        buildWallHorizLine(sixthLine, 6);
        int[] seventhLine = {0, 1, 2, 3, 6, 11, 12, 13, 15, 16, 17, 23, 26, 27, 28, 29};
        buildWallHorizLine(seventhLine, 7);
        int[] eightsLine = {1, 2, 3, 6, 11, 17, 23, 26, 27, 28};
        buildWallHorizLine(eightsLine, 8);
        int[] ninthLine = {11, 17};
        buildWallHorizLine(ninthLine, 9);
        int[] tenthLine = {1, 2, 3, 6, 11, 12, 13, 14,  15, 16, 17, 23, 26, 27, 28};
        buildWallHorizLine(tenthLine, 10);
        int[] eleventhLine = {0, 1, 2, 3, 6, 23, 26, 27, 28, 29};
        buildWallHorizLine(eleventhLine, 11);
        int[] twelfthLine = {1, 2, 3, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28};
        buildWallHorizLine(twelfthLine, 12);
        int[] thirteenthLine = {6, 7, 8, 14, 20, 21, 22, 23};
        buildWallHorizLine(thirteenthLine, 13);
        int[] fourteenth = {2, 3, 4, 25, 26, 27};
        buildWallHorizLine(fourteenth, 14);
        int[] fifteenth = {4, 6, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 23, 25};
        buildWallHorizLine(fifteenth, 15);
        int[] sixteenth = {1, 2, 6, 14, 23, 27, 28};
        buildWallHorizLine(sixteenth, 16);
        int[] seventeenth = {4, 5, 6, 7, 8, 14, 20, 21, 22, 23, 24, 25};
        buildWallHorizLine(seventeenth, 17);



    }


    private void buildWallHorizLine(int fields[], int posY){


        for(int i = 0; i < frameWidth; i++){

            for (int j = 0; j < fields.length; j ++){

                if(fields[j] == i){
                    tiles[i][posY].setWalkable(false);

                }
            }
        }
    }


    public String scanForWalls(MovingObject o){

        int checkX;
        int checkY;

        Tile left, right, up, down;

        for(int i = -1; i <= 1; i+=1f){
            for(int j = -1; j <= 1; j+=1f){

                checkX = (int)o.getX() + i;
                checkY = (int)o.getY() + j;


                if(checkX >= 0 && checkX <= 29 && checkY >= 0 && checkY <= frameHeight) {

                    if (checkX == o.getX() + 1 && checkY == o.getY() && !tiles[checkX][checkY].getWalkable() && o.getDir() == "RIGHT") {
                        right = tiles[checkX][checkY];
                        o.applyRepeller(right);
                        return "right";
                    }
                    if (checkX == o.getX() -1 && checkY == o.getY() && !tiles[checkX][checkY].getWalkable() && o.getDir() == "LEFT") {
                        left = tiles[checkX][checkY];
                        o.applyRepeller(left);
                        return "left";

                    }


                    if (checkX == o.getX() && checkY == o.getY() -1 && !tiles[checkX][checkY].getWalkable()&& o.getDir() == "UP") {
                        up = tiles[checkX][checkY];
                        o.applyRepeller(up);
                        return "up";

                    }
                    if (checkX == o.getX() && checkY == o.getY() + 1 && !tiles[checkX][checkY].getWalkable() && o.getDir() == "DOWN") {
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

             if(o.getX() == i && o.getY() == j && tiles[i][j].getWalkable()){

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




    private void createFrame(){

        for(int i = 0; i < frameWidth; i+=1){

            tiles[i][0].setWalkable(false);
            tiles[i][frameHeight-1].setWalkable(false);

        }

        for (int i = 0; i < frameHeight; i+= 1){

            tiles[0][i].setWalkable(false);
            tiles[frameWidth-1][i].setWalkable(false);


        }
        tiles[0][9].setWalkable(true);
         tiles[frameWidth-1][9].setWalkable(true);
    }


    private Tile getTile(MathVector pos){

        for(int i = 0; i < frameWidth; i++){
            for (int j = 0; j < frameHeight; j++){

                if(pos.x == i && pos.y == j){
                    return tiles[i][j];
                }


            }
        }
return null;


    }

    public void createGrid(){


        for(int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){

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


    public MovingObject convertTileToPos(Tile t){

        RandomRambler rr = new RandomRambler((int)t.pos.x, (int)t.pos.y);

        return rr;

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
        Boolean walkable;
        private float strength;
        int index;


        public Tile(float x, float y){

            this.pos = new MathVector(x, y);
            this.width = 20;
            this.height = 17.5f;
            this.walkable = true;
          this.strength = 2.9f;

        }



        public float getWidth(){return width;}
        public float getHeight(){return height;}
        public float getX(){return pos.x;}
        public float getY(){return pos.y;}
        public MathVector getPos(){return pos;}
        public void setWalkable(boolean w){this.walkable = w;}
        public int getIndex(){return index;}

        public MathVector repel(MovingObject o){


            MathVector dir;
            MathVector oPos = new MathVector(o.getX(), o.getY());
            dir = oPos.sub(this.pos);
            float d = (float)dir.mag();
            d = constrain(d, 1, 2);
            dir.normalize();
            float force =    strength / (d * d);
            dir.mult((int)force);
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
        

        public Boolean getWalkable() {return walkable;}

        public String toString(){

            StringBuilder sb = new StringBuilder();

            sb.append(" x = " + this.pos.x + " y = " + this.pos.y);
            sb.append( ", walkable = " + this.walkable);

            return sb.toString();


        }



    }

}
