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
    private float refreshRate =150;
    private Player player = new Player(13, 14);
    private RandomRambler ranRam = new RandomRambler(13, 9);
    private RandomRambler ranRamTwo = new RandomRambler(14, 9);
    private RandomRambler ranRamThree = new RandomRambler(15, 9);

    BFS pathfinder;
    Grid myGrid = new Grid();

    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    //public void btnStartAction(ActionEvent event)
    public void btnStartAction()
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

        calculateFields();


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
            default:
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

        if(player.getPos().x >= 0 && player.getPos().x < 29) {
            pathfinder = new BFS(ranRam, player, myGrid);
            pathfinder = new BFS(ranRamTwo, player, myGrid);
            pathfinder = new BFS(ranRamThree, player, myGrid);

        }else{
            ranRam.stop();
           ranRamTwo.stop();
            ranRamThree.stop();
        }

        ranRam.followPath();
        ranRamTwo.followPath();
        ranRamThree.followPath();

        player.update();
        ranRam.update();
        ranRamTwo.update();
        ranRamThree.update();

        drawCanvas();

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

        myGrid.displayGrid(g);

       ranRam.displayPath(g, Color.LIGHTBLUE);
      //  ranRamTwo.displayPath(g, Color.LIGHTSALMON);
       // ranRamThree.displayPath(g, Color.LIGHTPINK);

        ranRam.displaySelf(g, Color.PURPLE);
        ranRamTwo.displaySelf(g, Color.RED);
        ranRamThree.displaySelf(g, Color.PINK);


        // draw 'player'
        g.setFill(Color.YELLOW);
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

    public double getFieldWidth(){return fieldWidth;}
    public double getFieldHeight(){return fieldHeight;}

}
