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
    private int[][] neighbours = new int[16][16];

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

                ranRam.setPreX(ranRam.getX());
                ranRam.setPreY(ranRam.getY());
                player.setPreX(player.getX());
                player.setPreY(player.getY());


                if (now > lastUpdate + refreshRate * 1000000)
                {
                  //  System.out.println(fieldWidth + " " + fieldHeight);
                    lastUpdate = now;
                    update(now);
                 //   System.out.println("Pre X = " + player.getPreX() + "Pre Y = " + player.getPreY());
                  //  System.out.println("X = " + player.getX() + "Y = " + player.getY());




                }             }
        }.start();
    }

    private void addWalls(){
      for(int x = 10; x < 15; x++){
          for(int y = 10; y < 15; y++){

                  walls[x][y] = new Wall(Color.BLACK, x, y);
                  walls[x][y].setProportions();

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
       // player.setPrevPos(player.getX(), player.getY());


        switch (keyPressed)
        {
            case S:
                this.player.moveDown();
                break;
            case A:

                this.player.moveLeft();
                break;
            case D:

                this.player.moveRight();
                break;
            case W:
                this.player.moveUp();
                break;
        }
        player.setMoving(true);
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
        for(int x = 10; x < 15; x++){
            for(int y = 10; y < 15; y++){
                walls[x][y].update();
               g.setFill(Color.BLACK);
               g.fillRoundRect(walls[x][y].getX() * fieldWidth, walls[x][y].getY() * fieldHeight, walls[x][y].getWidth(), walls[x][y].getHeight(), 5, 5);

            }
        }

        // draw RandomRambler
        g.setFill(Color.PINK);
        g.fillRoundRect(this.ranRam.getX() * fieldWidth, this.ranRam.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        // draw 'player'
        g.setFill(Color.BLUE);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }


    int[][] reactToWalls(int x, int y, GameObject o){

        int[][] pos = new int[16][16];

        for (int i = x - 1; i < x +2; i++){
            for (int j = y -1; j< y+2; j++ ){

                if(i != x && j != y){
                    neighbours[i][j] = pos[x][y];

                    if(walls[x][y].getAxis().equals("VERTICAL") && o.getDir() == "LEFT"){
                        if(o.getX() == x -1 && o.getY() == y){
                          //  o.changeDir();
                            o.moveRight();
                            o.moveRight();
                        }
                    }
                    if(walls[x][y].getAxis().equals("VERTICAL") && o.getDir() == "RIGHT"){
                        if(o.getX() == x && o.getY() == y){
                            o.moveLeft();
                            o.moveLeft();
                            //o.changeDir();
                        }
                    }
                    if(o.getDir() == "UP" && walls[x][y].getAxis().equals("HORIZONTAL")){
                        if(o.getX() == x && o.getY() == y-1){

                            System.out.println("Moving Down");
                            //  o.changeDir();
                            o.moveDown();
                            o.moveDown();
                        }
                    }
                    if(o.getDir() == "DOWN" && walls[x][y].getAxis().equals("HORIZONTAL")){
                        if(o.getX() == x && o.getY() == y){
                            System.out.println("Moving Up");
                            //  o.changeDir();
                            o.moveUp();
                            o.moveUp();

                        }
                    }

                }
            }
        }
        return neighbours;
    }

    void detectWalls(GameObject o){

        for(int x = 10; x < 15; x++ ){
            for(int y = 10; y < 15; y++){

                reactToWalls(x, y, o);
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
