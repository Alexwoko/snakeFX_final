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



        /*
        for (int i = 0; i < frameWidth; i++){
            for (int j = 0; j < frameHeight; j++){

                Tile tile = tiles[i][j];


                    buildWall(tile);

                    System.out.println(numOFUnwalkN(tile) + " is the number of neighbours " + tile.getPos());
                   // tile.setUnwalkable(true);
                    System.out.println("Tile is unwalkable" + tile.getUnwalkable() + tile.getPos());



                    }



                }
*/



            }


    //    }




   // }


/*

private void getBuildDir(Tile tile){

    int checkX = 0;
    int checkY = 0;
    int leftCount = 0;
    int rightCount = 0;
    int upCount = 0;
    int downCount = 0;

    for(int i = -maxWallsInLine; i < maxWallsInLine;i++){
        for(int j = -maxWallsInLine; j < maxWallsInLine; j++){

            if(tile.getPos().x >= 4  )





            if ((i >= -maxWallsInLine && j >= -maxWallsInLine) || (i == 0 && j == 0) || (i >= -maxWallsInLine && j <= maxWallsInLine) || (i <= maxWallsInLine && j >= -maxWallsInLine )|| (i <= maxWallsInLine && j >= maxWallsInLine)) {
                continue;
            }


            checkX = tile.pos.x + i;
            checkY = tile.pos.y + j;



            if (tile.pos.x - checkX < 0 && tile.getUnwalkable() && tiles[checkX][checkY].getUnwalkable()){
                System.out.println();

               // return "LEFT-TOP"
            }


                    if( tile.pos.x + maxWallsInLine <= frameWidth - 1){
                checkX = tile.pos.x + i;
            }
            if(tile.pos.y - maxWallsInLine >= 1 && tile.pos.y + maxWallsInLine <= frameHeight - 1){
                checkY = tile.pos.y + j;
            }





            if(tile.getUnwalkable() && tiles[checkX][checkY].getUnwalkable()){
               // return true;
            }

        }

    }


    return null;
}
*/


    public int ranNumInRange(int min, int max){

        if(min >= max){
            throw new IllegalArgumentException("Minimum must be smaller than maximum");
        }else{
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        }
    }

private void buildWall(Tile tile){

   // System.out.println(unwalks.size());

  //  int checkX;
   // int checkY;


    // Find to tilfældige tal mellem  -1 og 1
   int ranX = ranNumInRange(-1, 1);
int ranY = ranNumInRange(-1, 1);


// Tallet oversættes i relation til this.tile

                ranX = tile.pos.x + ranX;
                ranY = tile.pos.y + ranY;

                //Hvis tallet er lig med this.tile position - recursive

                if(ranX == tile.pos.x && ranY == tile.pos.y){
                    buildWall(tile);
                }


// hvis der er under 200 unwalks (rammen udgør 127)
    if(numOFUnwalks == 130) {
return;
    }
        // Hvis tiles[ranX][ranY] er inden for rammen

        if (ranX >= 0 && ranX <= frameWidth - 1 && ranY >= 0 && ranY <= frameHeight - 1) {


            // Hvis der er 2 eller færere naboer til this.tile -- hvis der er 2 eller færere naboer til tiles[ranX][ranY] -- hvis listen ikke allerede indeholder tiles[ranX][ranY]

            if (numOFUnwalkN(tile) <= 2 && numOFUnwalkN(tiles[ranX][ranY]) <= 1 && !unwalks.contains(tiles[ranX][ranY])) {

                unwalks.add(tiles[ranX][ranY]);
                numOFUnwalks += 1;
                tiles[ranX][ranY].setUnwalkable(true);
                buildWall(tiles[ranX][ranY]);


            } else {
                buildWall(tiles[ranX][ranY]);
            }


/*
                    else{
                        buildWall(tile);
                    }
*/


        }


//return 0;


}

    private int numOFUnwalkN(Tile tile){

        int checkX = 0;
        int checkY = 0;

        int myCount = 0;


        for(int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
              //  if(tile.pos.x == i && tile.pos.y == j || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1 || i == 0 && j == 0){
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


        /*
        for(int i = -1; i < 1; i++){
            for(int j = -1; j < 1; j++) {

                if (tile.pos.x - i >= 0 && tile.pos.x <= frameWidth - 1) {
                    checkX = tile.pos.x + i;


                }
                if (tile.pos.y - i >= 0 && tile.pos.y <= frameHeight - 1) {
                    checkY = tile.pos.y + j;

                }

                if ((checkX == -1 && checkY == -1) || (checkX == 0 && checkY == 0) || (checkX == -1 && checkY == 1) || (checkX == 1 && checkY == -1 )|| (checkX == 1 && checkY == 1)) {

                    continue;
                }

                    if (tile.getUnwalkable() && tiles[checkX][checkY].getUnwalkable()) {
                      myCount += 1;

                    }

                    tile.setNCounter(myCount);

                    }
            }

        */
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

    /*
    public String toString(){






    }
*/


    /*
    public Tile findWall(int indexO, int indexT){

        for(int i = 0; i < frameWidth; i++){
            for (int j = 0; j < frameHeight; j++){

             if(indexO == i && indexT == j){
                 return walls[indexO][indexT];
             }

            }

        }
        return null;
    }
*/

/*
    public void createInside(){

      //  int xCount = 0;
     //  int yCount = 0;
        int wallCounter = 0;




        for(int x = 0; x < frameWidth; x++){

            for (int y = 0; y < frameHeight; y++){


             //   if(wallCounter < maxAmount && !walls[x][y].aWall && hasNeighbour(x, y)){
                if(hasNeighbour(x, y)){
                   Tile currWall = new Tile(x, y);

                  walls[x][y] = currWall;
                }
/*
            if(checkStraightLines(x, y)){

// change direction



            }








            }

        }
    }

*/
/*
    public boolean hasNeighbour(int x, int y){

        int wallCounter = 0;

        if (((x - 1) > 0 && (x + 1) < frameWidth) && ((y - 1) > 0 && (y + 1) < frameHeight)){

            for(int i = -1; i < 1; i++){

                Tile wX = tiles[x + i][y];
                Tile wY = tiles[x][y + i];

                if(wX.aWall || wY.aWall){
                    wallCounter++;

                    if(wallCounter > 0 && wallCounter <= 2){
                        return true;
                    }


                }


            }

        }
        return false;

    }
*/
    /*
    public boolean checkStraightLines(int x, int y) {

        int wallCounter = 0;

        // If were are inside Horizontal line +/- 2 OR inside Vertical line +/- 2
        if (x - maxWallsInLine > 2 && x + maxWallsInLine < frameWidth - 2 || y - maxAmount > 2 && y + maxWallsInLine < frameHeight -2) {

            for (int i = -4; i < maxWallsInLine; i++) {
                Tile wX = tiles[x + i][y];
                Tile wY = tiles[x][y + i];
                if (wX.aWall || wY.aWall) {
                    wallCounter++;
                    if (wallCounter == maxWallsInLine) {

                        return true;

                    }
                }
            }
        }


        return false;
    }
    */


    /*
    public Tile returnWallPiece(){

        for(int i = 0; i < frameWidth - 1; i++){
            for (int j = 0; j < frameHeight - 1; j++){
                Tile currWall = tiles[i][j];
                if(currWall.aWall){
                    return currWall;
                }

            }
        }
        return null;

    }
    */


/*
    public void createFrame(){

       for(int i = 0; i < frameWidth; i++){
           for (int j = 0; j < frameHeight; j++){

               if(j == 0 || j == frameHeight -1|| i == 0 || i == frameWidth){
                   tiles[i][j] = new Tile(i, j);
               }


           }
       }


        /*
        for(int i = 0; i < frameWidth; i++) {
            for(int j = 0; j < frameHeight; j++) {

                tiles[i][0] = new Tile(i, 0);
                tiles[i][frameWidth] = new Tile(i, frameHeight);

                tiles[0][j] = new Tile(0, j);
                tiles[frameWidth][j] = new Tile(frameWidth, j);

            }
            }

    }
    */


   // public tiles[][] getTiles(){return tiles;}



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

        public Tile(int x, int y){

            pos = new MathVector(x, y);
            width = 20;
            height = 17.5f;
            unwalkable = false;


        }

        public int getNCounter(){return nCounter;}
        public void setNCounter(int n){nCounter = n;}
        public float getWidth(){return width;}
        public float getHeight(){return height;}
        public MathVector getPos(){return pos;}
        public void setUnwalkable(boolean unW){this.unwalkable = unW;}


        public Boolean getUnwalkable() {return unwalkable;}

        public String toString(){

            StringBuilder sb = new StringBuilder();

            sb.append(" x = " + this.pos.x + " y = " + this.pos.y);
            sb.append( " unwalkable = " + this.unwalkable);

            return sb.toString();

        }
    }

}
