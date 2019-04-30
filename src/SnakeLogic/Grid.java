package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.*;

public class Grid {

    private int frameWidth;
    private int frameHeight;
    private GNode[][] gNodes;
    private double fieldWidth;
    private double fieldHeight;
    private Pathfinder pathfinder;
    private int showScore;



    public Grid(){

        fieldWidth = 20;
        fieldHeight = 17.85;
        frameWidth = 30;
        frameHeight = 20;
        gNodes = new GNode[frameWidth][frameHeight];
        createGrid();
        buildMaze();
        createFrame();
        insertCookies();
        insertSuperCookies();
        pathfinder = new Pathfinder(this);
        showScore = 0;


    }

    public Pathfinder getPathfinder(){return pathfinder;}

    public void controlTheHunt(RandomRambler seeker, MovingObject target, String algorithm){

        pathfinder.findPath(seeker, target, algorithm);

    }


    public int getShowScore(){return showScore;}


    public GNode[][] getGNodes(){return gNodes;}
    public int getFrameWidth(){return  frameWidth;}
    public int getFrameHeight(){return frameHeight;}

    public void resetVisited(){

        for (int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){
                gNodes[i][j].setVisited(false);
            }
        }
    }

    public void insertSuperCookies(){

        for(int i = 0; i < frameWidth; i ++){
            for(int j = 0; j < frameHeight; j++){

                if(gNodes[i][j].getWalkable() && !gNodes[i][j].getHasCookie()){
                    gNodes[i][j].setHasSuperCookie(true);

                }

            }
        }



    }


   public void insertCookies(){


    Random ran = new Random();

        for(int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){

int cookieOdds = ran.nextInt(100);
                if(!gNodes[i][j].getWalkable()){
               continue;
                } else{

                    if(cookieOdds < 98) {
                        gNodes[i][j].setHasCookie(true);
                    }
                    }

            }
        }
   }

    public GNode getTile(float x, float y){

        for(int i = 0; i < frameWidth; i ++){
            for (int j = 0; j < frameHeight; j++){

                if(i == x && j == y){
                    return gNodes[i][j];
                }

            }
        }
        return null;

    }

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

    private void buildWallHorizLine(int[] fields, int posY){

        for(int i = 0; i < frameWidth; i++){

            for (int j = 0; j < fields.length; j ++){

                if(fields[j] == i){
                    gNodes[i][posY].setWalkable(false);
                    gNodes[i][posY].setWalkable(false);
                }
            }
        }
    }

    public String scanForWalls(MovingObject o){

        int checkX;
        int checkY;

        GNode left;
        GNode right;
        GNode up;
        GNode down;

        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){

                checkX = (int)o.getX() + i;
                checkY = (int)o.getY() + j;

                if(checkX >= 0 && checkX <= 29 && checkY >= 0 && checkY <= frameHeight) {

                    if (checkX == o.getX() + 1 && checkY == o.getY() && !gNodes[checkX][checkY].getWalkable() && o.getDir() == "RIGHT") {
                        right = gNodes[checkX][checkY];
                        o.applyRepeller(right);
                        return "right";
                    }
                    if (checkX == o.getX() -1 && checkY == o.getY() && !gNodes[checkX][checkY].getWalkable() && o.getDir() == "LEFT") {
                        left = gNodes[checkX][checkY];
                        o.applyRepeller(left);
                        return "left";
                    }
                    if (checkX == o.getX() && checkY == o.getY() -1 && !gNodes[checkX][checkY].getWalkable()&& o.getDir() == "UP") {
                        up = gNodes[checkX][checkY];
                        o.applyRepeller(up);
                        return "up";
                    }
                    if (checkX == o.getX() && checkY == o.getY() + 1 && !gNodes[checkX][checkY].getWalkable() && o.getDir() == "DOWN") {
                        down = gNodes[checkX][checkY];
                        o.applyRepeller(down);
                        return "down";
                    }
                }
            }
        }
        return null;
    }

    public void wallScanner(MovingObject o){

        for(int i = 0; i < frameWidth; i+=1f){
            for(int j = 0; j <  frameHeight; j+=1f){

                if(o.getX() == i && o.getY() == j && gNodes[i][j].getWalkable()){

                    o.stopMoving(scanForWalls(o));
                }
            }
        }
    }


    public void eatCookie(Player player){

        for(int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){

                if(!gNodes[i][j].getWalkable()){
                    continue;
                }

                if(player.getX() == i && player.getY() == j && gNodes[i][j].getHasCookie()){

                    gNodes[i][j].setHasCookie(false);
                    player.setScore(player.getScore() + 1);
                    showScore = player.getScore();

                }

                else if(player.getX() == i && player.getY() == j && gNodes[i][j].getHasSuperCookie());

             //   System.out.println("HAAAAAAAAAAPSSSS!!!!");
                gNodes[i][j].setHasSuperCookie(false);
                player.setInvincible(true);

            }
        }


    }

    private void createFrame(){

        int canvasX = 0;
        int canvasY = 0;
        int canvasWidth = frameWidth - 1;
        int canvasHeight = frameHeight - 1;

        for(int i = 0; i < frameWidth; i+=1){

            gNodes[i][canvasY].setWalkable(false);
            gNodes[i][canvasHeight].setWalkable(false);
        }

        for (int i = 0; i < frameHeight; i+= 1){

            gNodes[canvasX][i].setWalkable(false);
            gNodes[canvasWidth][i].setWalkable(false);

        }
        gNodes[canvasX][9].setWalkable(true);
        gNodes[canvasWidth][9].setWalkable(true);
    }


    public void createGrid(){

        int indexer = 0;

        for(int i = 0; i < frameWidth; i++){
            for(int j = 0; j < frameHeight; j++){

                gNodes[i][j] = new GNode(i, j, indexer);
                indexer++;
            }
        }
    }


    public void displayGrid(GraphicsContext g){


        for(int i = 0; i < getFrameWidth(); i++){
            for (int j = 0; j < getFrameHeight(); j++) {


                System.out.println(gNodes[i][j].getHasSuperCookie());


                g.setFill(Color.DARKGREEN);

                if (!gNodes[i][j].getWalkable()) {
                    g.setFill(Color.BLACK);
                }


                g.fillRoundRect(gNodes[i][j].getX() * fieldWidth, gNodes[i][j].getY() * fieldHeight, gNodes[i][j].getWidth(), gNodes[i][j].getHeight(), 3, 3);

                //    if (currentMover.visitedContainsNode(gNodes[i][j]) && gNodes[i][j].getWalkable()) {

                if (gNodes[i][j].getPrevVisited() && gNodes[i][j].getWalkable()) {

                    g.setFill(new Color(0, 0.70, 1, 0.50f));

                } else if(gNodes[i][j].getPrevVisitedTwo() && gNodes[i][j].getWalkable()){

                    g.setFill(new Color(1, 0.5, 0.5, 0.50f));

                } else if(gNodes[i][j].getPrevVisitedThree() && gNodes[i][j].getWalkable()){

                    g.setFill(new Color(1, 0, 0.5, 0.50f));
                }



                g.fillRoundRect(gNodes[i][j].getX() * fieldWidth, gNodes[i][j].getY() * fieldHeight, gNodes[i][j].getWidth(), gNodes[i][j].getHeight(), 3, 3);
                gNodes[i][j].setPrevVisited(false);
                gNodes[i][j].setPrevVisitedTwo(false);
                gNodes[i][j].setPrevVisitedThree(false);
                gNodes[i][j].setMoveCost(Float.MAX_VALUE);

                if(gNodes[i][j].getHasSuperCookie()){

                      System.out.println(gNodes[i][j].getHasSuperCookie() + " " + gNodes[i][j].toString());
                    g.setFill(Color.YELLOW);
                    g.fillRoundRect(gNodes[i][j].getX() * fieldHeight + (fieldWidth/2), gNodes[i][j].getY() * fieldHeight + (fieldHeight/2), 15, 15, 3, 3);

                }

                if(gNodes[i][j].getHasCookie()){

                    g.setFill(Color.LIGHTGRAY);
                    g.fillRoundRect(gNodes[i][j].getX() * fieldWidth + (fieldWidth/2), gNodes[i][j].getY() * fieldHeight + (fieldHeight/2), 3, 3, 2,2);

                }



            }
        }
    }

   public void checkEdges(GameObject o){

        int canvasX = 0;
        int canvasY = 0;
        int canvasWidth = frameWidth - 1;
        int canvasHeight = frameHeight - 1;

        if(o.getX() * fieldWidth > 600 - fieldWidth){
            o.setX(canvasX);
        }
        if(o.getX() < canvasX){
            o.setX(canvasWidth);
        }

        if(o.getY() * fieldHeight >= 400){
            o.setY(canvasY);
        }
        if(o.getY() < canvasY){
            o.setY(canvasHeight);
        }
    }


    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append(showScore);

        return sb.toString();

    }
}
