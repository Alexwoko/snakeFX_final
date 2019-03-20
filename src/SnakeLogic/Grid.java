package SnakeLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {


    public Tile[][] tiles;
    private int frameWidth, frameHeight;
    private int maxWallsInLine;
    private int maxAmount;
    private int numOFUnwalks;
    private List<Tile> unwalks = new ArrayList<>();


    private String buildDir;



    public Grid(){

        numOFUnwalks = 0;
        this.maxAmount = 0;
        frameWidth = 30;
        frameHeight = 20;
        tiles = new Tile[frameWidth][frameHeight];
        maxWallsInLine = 4;
        createGrid();
        createFrame();
        createMaze();
        System.out.println(numOFUnwalkN(tiles[7][0]));
        // System.out.println(hasNeighbour(tiles[frameWidth-1][5]));
        //  System.out.println(maxInLine(tiles[7][0]));


    }

    public int getFrameWidth(){return  frameWidth;}
    public int getFrameHeight(){return frameHeight;}




    private void createMaze(){

        tiles[5][1].setUnwalkable(true);
        tiles[5][2].setUnwalkable(true);

        buildWall(tiles[5][2]);


    }


    public int ranNumInRange(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("Minimum must be smaller than maximum");
        }else{
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
    }

    private void buildWall(Tile tile){

        int ranX = ranNumInRange(-1, 1);
        int ranY = ranNumInRange(-1, 1);

        ranX = tile.pos.x + ranX;
        ranY = tile.pos.y + ranY;


        if(ranX == tile.pos.x && ranY == tile.pos.y){
            buildWall(tile);
        }


        if(numOFUnwalks == 130) {
            return;
        }

        if (ranX >= 0 && ranX <= frameWidth - 1 && ranY >= 0 && ranY <= frameHeight - 1) {



            if (numOFUnwalkN(tile) <= 2 && numOFUnwalkN(tiles[ranX][ranY]) <= 1 && !unwalks.contains(tiles[ranX][ranY])) {

                unwalks.add(tiles[ranX][ranY]);
                numOFUnwalks += 1;
                tiles[ranX][ranY].setUnwalkable(true);
                buildWall(tiles[ranX][ranY]);


            } else {
                buildWall(tiles[ranX][ranY]);
            }
        }


    }

    private int numOFUnwalkN(Tile tile){

        int checkX = 0;
        int checkY = 0;

        int myCount = 0;


        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){

                if( i == 0 && j == 0){
                    continue;

                } else{

                    checkX = tile.pos.x + i;
                    checkY = tile.pos.y + j;

                    if(checkX >= 0 && checkX <= frameWidth-1 && checkY >= 0 && checkY <= frameHeight-1){
                        if(tiles[checkX][checkY].getUnwalkable())
                            myCount += 1;


                    }

                }


            }

        }

        return myCount;
    }


    public void createFrame(){

        for(int i = 0; i < frameWidth; i++){


            tiles[i][0].setUnwalkable(true);
            tiles[i][frameHeight-1].setUnwalkable(true);
            unwalks.add(tiles[i][0]);
            unwalks.add(tiles[i][frameHeight -1]);


        }

        for (int i = 0; i < frameHeight; i++){

            tiles[0][i].setUnwalkable(true);
            tiles[frameWidth-1][i].setUnwalkable(true);
            unwalks.add(tiles[0][i]);
            unwalks.add(tiles[frameWidth - 1][i]);

        }


    }


    public void createGrid(){


        for(int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){

                tiles[i][j] = new Tile(i, j);

            }

        }
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
        private int strength;

        public Tile(int x, int y){

            pos = new MathVector(x, y);
            width = 20;
            height = 17.5f;
            unwalkable = false;
           strength = 1;


        }

        public int getNCounter(){return nCounter;}
        public void setNCounter(int n){nCounter = n;}
        public float getWidth(){return width;}
        public float getHeight(){return height;}
        public int getX(){return pos.x;}
        public int getY(){return pos.y;}
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
            float force =  -1 * strength / (d * d);
            dir.mult((int)force);
          //  System.out.println(dir);
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
