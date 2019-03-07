package SnakeGUI;


import SnakeLogic.Item;
import SnakeLogic.Player;
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
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =300;
    private Player player = new Player(0, 0);


    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    ArrayList<Item> items = new ArrayList<Item>();

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
        player.setPrevPos(0, 0);
                AddItems();

        calculateFields();
        getRandomPosition();
        //drawCanvas();

        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {


                player.setPreX(player.getX());
                player.setPreY(player.getY());

                if (now > lastUpdate + refreshRate * 1000000)
                {

                    lastUpdate = now;
                    update(now);
                }             }
        }.start();
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
        player.setPrevPos(player.getX(), player.getY());
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

        player.update();
       // System.out.println("now X = " + player.getX() + " now Y = " + player.getY());
       // System.out.println("Pre X = " + player.getPreX() + " pre Y = " + player.getPreY());

        // RANDOM RAMPLER UPDATE
        // PROBABLY LOOKING FOR WALLS
        // UPDATE RANDOM RAMBLER POSITION
      //  player.update();




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

        // draw 'player'
        g.setFill(Color.BLUE);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }
}
