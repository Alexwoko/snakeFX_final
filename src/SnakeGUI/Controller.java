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
import javafx.scene.text.Font;

import java.util.*;

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
    private Player player = new Player(14, 14);
    private RandomRambler ranRam = new RandomRambler(13, 9);
    private RandomRambler ranRamTwo = new RandomRambler(14, 9);
    private RandomRambler ranRamThree = new RandomRambler(15, 9);
   // private MovingObject target = new RandomRambler(0, 0);

    Grid myGrid = new Grid();


    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    public void btnStartAction(ActionEvent event)
    {
        System.out.println("btn clicked");
        labelStatus.setText("test");
      //  getRandomPosition();
        drawCanvas();
    }

    /**
     * Executed when JavaFX is initialized. Used to setup the Snake game
     */
    public void initialize()
    {

  //  givePos(ranRam);
  //  givePos(player);
   // givePos(target);

        calculateFields();


        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {

                if (now > lastUpdate + refreshRate * 1000000)
                {

                    lastUpdate = now;

                    if(player.getPos().x >= 0 && player.getPos().x < 29) {
                        myGrid.BFS(ranRam, player);
                        myGrid.BFS(ranRamTwo, player);
                        myGrid.BFS(ranRamThree, ranRamTwo);


                    }else{
                        ranRam.stop();
                    }



                    update(now);

                }             }
        }.start();
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



        myGrid.playerScanner(player);
        myGrid.playerScanner(ranRam);
        myGrid.playerScanner(ranRamTwo);
        myGrid.playerScanner(ranRamThree);
        checkEdges(player);
        checkEdges(ranRam);
        checkEdges(ranRamTwo);
        checkEdges(ranRamThree);




        ranRam.followPath(myGrid.thePath);
        ranRamTwo.followPath(myGrid.thePath);
        ranRamThree.followPath(myGrid.thePath);


        player.update();
        ranRam.update();
        ranRamTwo.update();
        ranRamThree.update();

        drawCanvas();

    }

    /**
     * Get a random position
     */

    private void givePos(MovingObject o){

        MathVector pos = new MathVector(random.nextInt(width), random.nextInt(height));
       // ranRam = new RandomRambler(pos.x, pos.y);
        o.setX(pos.x);
        o.setY(pos.y);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){

                if(o.getX() == i && o.getY() == j && myGrid.tiles[i][j].getUnwalkable()){
                    givePos(o);
                }else{

                }
            }
        }
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



        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for (int j = 0; j < myGrid.getFrameHeight(); j++){


                        g.setFill(Color.DARKGREEN);
                   if(myGrid.tiles[i][j].getUnwalkable()){
                    g.setFill(Color.BLACK);
                }

                   if(myGrid.tree.containsValue(myGrid.tiles[i][j].getIndex())){
                      // g.fillRoundRect(myGrid.tiles[i][]);
                       g.setFill(Color.RED);
                   }


                        g.fillRoundRect(myGrid.tiles[i][j].getPos().x * fieldWidth, myGrid.tiles[i][j].getPos().y * fieldHeight, myGrid.tiles[i][j].getWidth(), myGrid.tiles[i][j].getHeight(), 3, 3);
            }
        }

        // draw path
        for(Grid.Tile t: myGrid.thePath){
            g.setFill(Color.WHITE);
            g.fillRoundRect(t.getX() * fieldWidth, t.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        }


       // g.setFill(Color.RED);
       // g.fillOval(target.getX() * fieldWidth, target.getY() * fieldHeight, 20, 20);



        // draw RandomRambler
        g.setFill(Color.YELLOW);
        g.fillRoundRect(this.ranRam.getX() * fieldWidth, this.ranRam.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        g.setFill(Color.TEAL);
        g.fillRoundRect(this.ranRamTwo.getX() * fieldWidth, this.ranRamTwo.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
        g.setFill(Color.PINK);
        g.fillRoundRect(this.ranRamThree.getX() * fieldWidth, this.ranRamThree.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);


        // draw 'player'
        g.setFill(Color.BLUE);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
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
