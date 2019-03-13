package SnakeGUI;

import SnakeLogic.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    Label labelStatus;
    @FXML
    Canvas canvas;

    private double fieldHeight;
    private double fieldWidth;
    private int width = 30;
    private int height = 20;
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =150;
    private Player player = new Player(0, 0);
    private RandomRambler ranRam = new RandomRambler(width/2, height/2);


    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    private ArrayList<Item> items = new ArrayList<Item>();
  //  private Wall[][] neighbours = new Wall[29][19];

    private Wall[][] walls = new Wall[29][19];

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
        getRandomPosition();
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {


        AddItems();
        addWalls();

        calculateFields();
        getRandomPosition();
        //drawCanvas();

        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {


                if (now > lastUpdate + refreshRate * 1000000)
                {
                    lastUpdate = now;
                    update(now);





                }             }
        }.start();
    }

    private void addWalls(){
      for(int x = 10; x < 25; x++){
          for(int y = 5; y < 15; y++){

              if(x%2 == 0 && y % 2 == 0) {
                  walls[x][y] = new Wall(Color.BLACK, x, y);
                  walls[x][y].setProportions();
              }
          }
      }
    }

    private void AddItems() {

        // PROGRAM TO INTERFACE SO WE HAVE A LIST THAT CAN CONTAIN PLAYER, WALLS, AND ITEMS
        items.add(new Item(Color.GREEN, 3,3));
        items.add(new Item(Color.RED, 12,9));
        // Would be nice to add player and walls here somehow


    }

    public void keyPressed(KeyCode keyCode)
    {

        System.out.println("key pressed: " + keyCode);
        this.keyPressed = keyCode;
    }



    /**
     * Game loop - executed continously during the game
     * @param now game time in nano seconds
     */
    private void update(long now)
    {

        switch (keyPressed)
        {
            case S:
              //  this.player.setVelY(1);
                this.player.moveDown();
                break;
            case A:
             //   this.player.setVelX(1);

                this.player.moveLeft();
                break;
            case D:
               // this.player.setVelX(1);

                this.player.moveRight();
                break;
            case W:
               // this.player.setVelY(1);

                this.player.moveUp();
                break;
        }

        System.out.println(fieldHeight + " Height");
        System.out.println(fieldWidth + " Width");


        checkEdges(player);
       checkEdges(ranRam);
        detectWalls(player);
       detectWalls(ranRam);
        player.update();




        // RANDOM RAMPLER UPDATE
        // PROBABLY LOOKING FOR WALLS



       ranRam.update();


        //getRandomPosition();
        drawCanvas();


    }

    /**
     * Get a random position
     */

    private void getRandomPosition() {
        this.player.setX(random.nextInt(width));
        this.player.setY(random.nextInt(height));
    }

    /**
     * Calculate height and width of each field
     */
    private void calculateFields() {
        this.fieldHeight = canvas.getHeight() / this.height;
        this.fieldWidth = canvas.getWidth() / this.width;
    }

    /**
     * Draw the canvas - used in the gameloop
     */
    private void drawCanvas() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        // Clears the whole screen before re-drawing
        g.clearRect(0,0,width*fieldWidth ,height*fieldHeight);

        // draw items
        for (Item item : items)
        {
            g.setFill(item.getColor());
            g.fillRoundRect(item.getX() * fieldWidth, item.getY() * fieldHeight, fieldWidth, fieldHeight, 5,5);
        }

// draw walls
        for(int x = 10; x < 25; x++){
            for(int y = 5; y < 15; y++){
                if(x%2==0 && y%2==0) {
                    walls[x][y].update();
                    //  System.out.println(walls[x][y].getX() + walls[x][y].getY());
                    g.setFill(Color.BLACK);
                    g.fillRoundRect(walls[x][y].getX() * fieldWidth, walls[x][y].getY() * fieldHeight, walls[x][y].getWidth(), walls[x][y].getHeight(), 5, 5);
                }
            }
        }

        // draw RandomRambler
       g.setFill(Color.PINK);
        g.fillRoundRect(this.ranRam.getX() * fieldWidth, this.ranRam.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        // draw 'player'
        g.setFill(Color.BLUE);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }

    public void reactToWalls(Wall[][] w, GameObject o){


        for(int i = 0; i < w.length; i++){
            for(int j = 0; j < w.length; j++){


                if(o.getX() >= w[i][j].getX() && o.getX() <= w[i][j].getX() + w[i][j].getWidth()){
                    if(o.getY() == w[i][j].getY() && w[i][j].getAxis().equals("HORIZONTAL")){
                        System.out.println("In line!");
                    }

                }

            }


        }



    }


/*
    Wall[][] reactToWalls(int x, int y, GameObject o){



        for (int i = x - 1; i < x +2; i++){
            for (int j = y -1; j< y+2; j++ ){

                if(i != x && j != y){
                    neighbours[i][j] = walls[x][y];

                    if(neighbours[i][j].getAxis().equals("VERTICAL") && o.getDir() == "LEFT"){
                        if(o.getX() == x  && o.getY() >= y && o.getY() <= y + neighbours[i][j].getFinalHeight()){
                            o.setLeftN(true);
                            o.setX(o.getX() + 1);

                            o.applyRepeller(walls[x][y]);


                        }else{
                            o.setLeftN(false);
                        }
                    }
                    if(neighbours[i][j].getAxis().equals("VERTICAL") && o.getDir() == "RIGHT"){
                        if(o.getX() == x -1  && o.getY() >= y && o.getY() <= y + neighbours[i][j].getFinalHeight()){
                            o.setRightN(true);

                            o.applyRepeller(walls[x][y]);


                        } else{
                            o.setRightN(false);
                        }
                    }
                    if(o.getDir() == "UP" && neighbours[i][j].getAxis().equals("HORIZONTAL")){
                        if(o.getX() >= x && o.getX() <= x + neighbours[i][j].getFinalWidth() && o.getY() == y){
                            o.setTopN(true);
                            o.setY(o.getY() + 1);

                            o.applyRepeller(walls[x][y]);


                        }else{
                            o.setTopN(false);
                        }
                    }
                    if(o.getDir() == "DOWN" && neighbours[i][j].getAxis().equals("HORIZONTAL")){
                        if(o.getX() >= x && o.getX() <= x + neighbours[i][j].getFinalWidth() && o.getY() == y-1){
                            o.setDownN(true);

                            o.applyRepeller(walls[x][y]);

                        }else{
                            o.setDownN(false);
                        }
                    }

                }
            }
        }
        return neighbours;
    }
*/

public Wall[][] findNeighbours(int x, int y){

    Wall[][] neighbours = new Wall[3][3];
    for(int i = x - 1; i < x + 1; i++){
        for(int j = y -1; j < y +1; j++){

            if(i != x && j != y){
                neighbours[i][j] = walls[i][j];
                return neighbours;

            }


        }


    }
    return null;

}
    void detectWalls(GameObject o){

        for(int x = 10; x < 25; x++ ){
            for(int y = 5; y < 15; y++){

                if(x%2==0 && y%2==0) {
                Wall[][] neighbours = findNeighbours(x, y);
                    // o.applyRepeller(walls[x][y]);
                    // reactToWalls(walls[x][y], o);
                    reactToWalls(neighbours, o);
                }
            }

        }

    }

    void checkEdges(GameObject o){

        if(o.getX() * fieldWidth > canvas.getWidth() - fieldWidth){
            o.setX(0);
        }
        if(o.getX() < 0){
            o.setX(29);
        }

        if(o.getY() * fieldHeight >= canvas.getHeight()){
            o.setY(0);
        }
        if(o.getY() < 0){
            o.setY(19);
        }


    }
}
