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
    private Random random = new Random();
    private int gameLoopDelay = 500;
    private float refreshRate =150;
    private Player player = new Player(4, 6);
    private RandomRambler ranRam = new RandomRambler(4, 5);
    private MovingObject target = new RandomRambler(0, 0);
  //  private RandomRambler ranRam;

    Grid myGrid = new Grid();



  //  private Node[][] grid;
 //   private List<Node> closed = new ArrayList<>();
  //  private List<Node> myPath = new ArrayList<>();





  //  private MathVector target = new MathVector(0, 0);



    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    //  private ArrayList<Item> items = new ArrayList<Item>();



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

       // createGrid();
      //  addWalls();

      //  targetPos();
    givePos(ranRam);
    givePos(player);
    givePos(target);

        calculateFields();
      //  getRandomPosition();

        System.out.println(myGrid.tiles[player.getX()][player.getY()].getUnwalkable());
      //  myGrid.BFS(ranRam.getPos(), player.getPos());
        myGrid.BFS(ranRam.getPos(), target.getPos());

     //  myMaze.initialize();




        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {

                if (now > lastUpdate + refreshRate * 1000000)
                {

                    lastUpdate = now;
                 //   myBFS(ranRam.getPos().getPos(), player.getPos());
                  //  myBFS(ranRam.getPos().getPos(), target.getPos());
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
        //  ranRam.randomWalk();

        checkEdges(player);
        checkEdges(ranRam);
      //  reactToWalls(ranRam);
     //   reactToWalls(player);
        System.out.println(ranRam.getX() + " Rambler X");
        System.out.println(ranRam.getY() + " Rambler y");

        myGrid.playerScanner(player);
        myGrid.playerScanner(ranRam);


        for(int i = 0; i < myGrid.getPathSize(); i++ ){

            Grid.Tile t = myGrid.thePath.get(i);
            ranRam.setX(t.getX());
            ranRam.setY(t.getY());
            break;

        }


        /*
        // Walk the path
    for (int i = 0; i < myPath.size(); i++) {

        Node n = myPath.get(i);
        ranRam.setX(n.pos.x);
        ranRam.setY(n.pos.y);
        break;
    }
    */

        player.update();
        ranRam.update();

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

    /*
    private void targetPos(){

        MathVector pos = new MathVector(random.nextInt(width) , random.nextInt(height));

        target.setPos(pos);

        for(int i = 0; i < width; i ++){
            for (int j = 0; j < height; j++){


                    if (target.x == i && target.y == j && !isWalkable(grid[i][j])) {
                        targetPos();

                    }else{

                    }
            }
        }
    }
    */
/*    private void getRandomPosition() {
        this.player.setX(random.nextInt(width));
        this.player.setY(random.nextInt(height));
    }
    */

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



/*
// draw path
        for(Node n: myPath){
            g.setFill(Color.GREEN);
            g.fillRoundRect(n.pos.x * fieldWidth, n.pos.y * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        }
        */

        for(int i = 0; i < myGrid.getFrameWidth(); i++){
            for (int j = 0; j < myGrid.getFrameHeight(); j++){

                        g.setFill(Color.LIGHTGRAY);
                      //  g.setStroke(Color.BLACK);
                if(myGrid.tiles[i][j].getUnwalkable()){
                    g.setFill(Color.BLACK);
                }
                        g.fillRoundRect(myGrid.tiles[i][j].getPos().x * fieldWidth, myGrid.tiles[i][j].getPos().y * fieldHeight, myGrid.tiles[i][j].getWidth(), myGrid.tiles[i][j].getHeight(), 3, 3);
                     //   g.fillText("T", myGrid.tiles[i][j].getPos().x * fieldWidth, myGrid.tiles[i][j].getPos().y * fieldHeight);

            }
        }


        g.setFill(Color.RED);
        g.fillOval(target.getX() * fieldWidth, target.getY() * fieldHeight, 20, 20);



        // draw RandomRambler
        g.setFill(Color.YELLOW);
        g.fillRoundRect(this.ranRam.getX() * fieldWidth, this.ranRam.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);


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



 /*


    public void retracePath(Node starNode, Node endNode){

        List<Node> thePath = new ArrayList<>();
        Node currentNode = endNode;

        while(!currentNode.equals(starNode)){
            thePath.add(currentNode);
            currentNode = currentNode.parent;

        }
        Collections.reverse(thePath);
        myPath = thePath;


    }

 */
}
