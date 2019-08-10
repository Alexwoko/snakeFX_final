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
    private MovingObject[] movingObjects = {player, ranRam, ranRamTwo, ranRamThree};



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
        //  myGrid.getPathfinder().setPlayer(player);

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



//player.setMaxSpeed(2);




        myGrid.wallScanner(player);
        myGrid.wallScanner(ranRam);
        myGrid.wallScanner(ranRamTwo);
        myGrid.wallScanner(ranRamThree);

        myGrid.checkEdges(player);
        myGrid.checkEdges(ranRam);
        myGrid.checkEdges(ranRamTwo);
        myGrid.checkEdges(ranRamThree);

        if(player.getPos().x >= 0 && player.getPos().x < 29) {


            //   System.out.println(ranRam.getDistance(player));


            myGrid.controlTheHunt(ranRam, player, "DEPTH FIRST SEARCH");


            if(ranRamTwo.getDistance(player) < -5 || ranRamTwo.getDistance(player) > 5){

                myGrid.controlTheHunt(ranRamTwo, ranRam, "BREADTH FIRST SEARCH");

            } else{

                myGrid.controlTheHunt(ranRamTwo, player, "BREADTH FIRST SEARCH");

            }



            if(ranRamThree.getDistance(player) < -5 || ranRamThree.getDistance(player) > 5){

                myGrid.controlTheHunt(ranRamThree, predictTheFuture(player), "BEST FIRST SEARCH");

              //  myGrid.controlTheHunt(ranRamThree, ranRamTwo, "BEST FIRST SEARCH");

            } else{
                myGrid.controlTheHunt(ranRamThree,player, "BEST FIRST SEARCH");
            }

            controlPlayerSpeed(player);




        }

        ranRam.followPath();
        ranRamTwo.followPath();
        ranRamThree.followPath();

        myGrid.eatCookie(player);

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



        g.fillText("Score: " + Integer.toString(myGrid.getShowScore()), canvas.getWidth() - 160, 10, 100);

    }


    public MovingObject predictTheFuture(MovingObject o){

        int checkX;
        int checkY;

        MovingObject predictionGhost;

        for(int i = (int)o.getX() - 2; i < (int)o.getX() + 2; i++){
           for(int j = (int)o.getY() - 2; j < (int)o.getX() + 2; j++){

              checkX = i;
              checkY = j;


              if(checkX < 30 && checkX > 0 && checkY > 0 && checkY < 20) {
                  if (myGrid.getTile(checkX, checkY).getWalkable()) {
                      predictionGhost = new Player(checkX, checkY);
                      return predictionGhost;

                  }

              }
           }



        }

        return o;


    }


    /*
    public MovingObject predictTheFuture(MovingObject o) {


        MovingObject predictionGhost;

        if (o.getDir() != null) {

            if (o.getDir().equals("RIGHT")) {

                int prediction = (int) o.getX() + 2;

                if (prediction < 30 && myGrid.getTile(prediction, o.getY()).getWalkable()) {

                    predictionGhost = new Player(prediction, (int) o.getY());
                    return predictionGhost;

                }
            }

            if (o.getDir().equals("LEFT")) {

                int prediction = (int) o.getX() - 2;

                if (prediction > 0 && myGrid.getTile(prediction, o.getY()).getWalkable()) {

                    predictionGhost = new Player(prediction, (int) o.getY());
                    return predictionGhost;
                }
            }

            if (o.getDir().equals("UP")) {

                int prediction = (int) o.getY() - 2;

                if (prediction > 0 && myGrid.getTile(o.getX(), prediction).getWalkable()) {

                    predictionGhost = new Player((int) o.getX(), prediction);
                    return predictionGhost;
                }
            }

            if (o.getDir().equals("DOWN")) {

                int prediction = (int) o.getY() + 2;

                if (prediction < 20 && myGrid.getTile(o.getX(), prediction).getWalkable()) {

                    predictionGhost = new Player((int) player.getX(), prediction);
                    return predictionGhost;

                }

            }



        } else{
            return ranRam;
        }

        return null;
    }

*/

    public void controlPlayerSpeed(MovingObject o){

            int firstNode;
            int secondNode;

         //   if (o.getDir() != null) {
        if(o.getVel().x != 0 || o.getVel().y != 0){

                if (o.getDir().equals("UP")) {

                    firstNode = (int) o.getY() - 1;
                    secondNode = (int) o.getY() - 2;

                    if (myGrid.getTile(o.getX(), firstNode).getWalkable() && myGrid.getTile(o.getX(), secondNode).getWalkable()) {

                        o.setMaxSpeed(2);

                    } else if(!myGrid.getTile(o.getX(), secondNode).getWalkable()){
                        o.setMaxSpeed(1);
                    }


                } else if (o.getDir().equals("DOWN")) {

                    firstNode = (int) o.getY() + 1;
                    secondNode = (int) o.getY() + 2;

                    if (myGrid.getTile(o.getX(), firstNode).getWalkable() && myGrid.getTile(o.getX(), secondNode).getWalkable()) {

                        o.setMaxSpeed(2);

                    } else if(!myGrid.getTile(o.getX(), secondNode).getWalkable()){
                        o.setMaxSpeed(1);
                    }

                } else if (o.getDir().equals("RIGHT")) {

                    firstNode = (int) o.getX() + 1;
                    secondNode = (int) o.getX() + 2;

                    if (myGrid.getTile(firstNode, o.getY()).getWalkable() && myGrid.getTile(secondNode, o.getY()).getWalkable()) {

                        o.setMaxSpeed(2);

                    } else if(!myGrid.getTile(secondNode, o.getY()).getWalkable()){

                        o.setMaxSpeed(1);
                    }

                } else if (o.getDir().equals("LEFT")) {

                    firstNode = (int) o.getX() - 1;
                    secondNode = (int) o.getX() - 2;

                    if (myGrid.getTile(firstNode, o.getY()).getWalkable() && myGrid.getTile(secondNode, o.getY()).getWalkable()) {

                        o.setMaxSpeed(2);

                    } else if(!myGrid.getTile(secondNode, o.getY()).getWalkable()){
                        o.setMaxSpeed(1);

                    }


                }

            }

        }



}
