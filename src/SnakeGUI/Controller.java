package SnakeGUI;

import SnakeLogic.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

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
    private RandomRambler ranRam = new RandomRambler(13, 9, "One");
    private RandomRambler ranRamTwo = new RandomRambler(14, 9, "Two");
    private RandomRambler ranRamThree = new RandomRambler(15, 9, "Three");
    private Grid myGrid = new Grid();

    private KeyCode keyPressed = KeyCode.BACK_SPACE;

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

controlPlayers();



        drawCanvas();

    }

    private void controlPlayers(){

        myGrid.wallScanner(player);
        myGrid.wallScanner(ranRam);
        myGrid.wallScanner(ranRamTwo);
        myGrid.wallScanner(ranRamThree);
        checkEdges(player);
        checkEdges(ranRam);
        checkEdges(ranRamTwo);
        checkEdges(ranRamThree);

        if(player.getPos().x >= 0 && player.getPos().x < 29) {

            myGrid.controlTheHunt(ranRam, player, "DEPTH FIRST SEARCH");
            myGrid.controlTheHunt(ranRamTwo, player, "BREADTH FIRST SEARCH");
            myGrid.controlTheHunt(ranRamThree,player, "BEST FIRST SEARCH");

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
        ranRamTwo.displayPath(g, Color.LIGHTSALMON);
         ranRamThree.displayPath(g, Color.LIGHTPINK);

        ranRam.displaySelf(g, Color.PURPLE);
        ranRamTwo.displaySelf(g, Color.RED);
        ranRamThree.displaySelf(g, Color.PINK);


        // draw 'player'
        g.setFill(Color.YELLOW);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }


    void checkEdges(GameObject o){

        int canvasX = 0;
        int canvasY = 0;
        int canvasWidth = width - 1;
        int canvasHeight = height - 1;

        if(o.getX() * fieldWidth > canvas.getWidth() - fieldWidth){
            o.setX(canvasX);
        }
        if(o.getX() < canvasX){
            o.setX(canvasWidth);
        }

        if(o.getY() * fieldHeight >= canvas.getHeight()){
            o.setY(canvasY);
        }
        if(o.getY() < canvasY){
            o.setY(canvasHeight);
        }
    }
}
