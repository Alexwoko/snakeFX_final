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
    private Player player = new Player(0, 0);
    private RandomRambler ranRam = new RandomRambler(3, 3);
    private Node[][] grid;
    private List<Node> closed = new ArrayList<>();
    private List<Node> myPath = new ArrayList<>();
    private boolean showPath = false;
    private boolean showUnwalkables = false;
    private long startMillis = System.currentTimeMillis();
    private long currentMillis;
    private long counter = 0;

    private MathVector target = new MathVector(25, 18);


    private KeyCode keyPressed = KeyCode.BACK_SPACE;

    //  private ArrayList<Item> items = new ArrayList<Item>();

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


        //   AddItems();
        createGrid();
        addWalls();
        calculateFields();
        getRandomPosition();






        // Start and control game loop
        new AnimationTimer(){
            long lastUpdate;
            public void handle (long now) {

                counter += 1;

             //   if(counter > 40) {

                   // myBFS(ranRam.getPos().getPos(), target.getPos());
               // }




                if (now > lastUpdate + refreshRate * 1000000)
                {

                    lastUpdate = now;
                    myBFS(ranRam.getPos().getPos(), target.getPos());
                    update(now);

                }             }
        }.start();
    }

    private void addWalls(){
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 20; y++){

                if(x%2 == 0 && y % 3 == 0) {
                    walls[x][y] = new Wall(Color.BLACK, x , y);
                    walls[x][y].setProportions();

                    if(walls[x][y].getAxis().equals("VERTICAL")){
                        grid[x][y].walkable = false;
                        grid[x][y + 1].walkable = false;
                    }else if(walls[x][y].getAxis().equals("HORIZONTAL")){
                        grid[x][y].walkable = false;
                        grid[x +1 ][y].walkable = false;
                    }
                }
            }
        }
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
            case P:

                showPath = true;

                break;
            case U:

                showUnwalkables = true;

                break;
        }


        //  ranRam.randomWalk();

        checkEdges(player);
        checkEdges(ranRam);
        reactToWalls(ranRam);
        reactToWalls(player);



        for (int i = 0; i  < myPath.size(); i++){

            Node n = myPath.get(i);
            ranRam.setX(n.pos.x);
            ranRam.setY(n.pos.y);
            break;
        }



        player.update();
        ranRam.update();

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


        g.setFill(Color.BLACK);
        g.fillOval(target.x * fieldWidth, target.y * fieldHeight, 20, 20);


// draw walls
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 20; y++){
                if(x%2==0 && y%3==0) {
                    walls[x][y].update();

                    g.setFill(Color.BLACK);
                    g.fillRoundRect(walls[x][y].getX() * fieldWidth, walls[x][y].getY() * fieldHeight, walls[x][y].getWidth(), walls[x][y].getHeight(), 5, 5);

                }
            }
        }

// draw path
        for(Node n: myPath){
            g.setFill(Color.GREEN);
            g.fillRoundRect(n.pos.x * fieldWidth, n.pos.y * fieldHeight, fieldWidth, fieldHeight, 3, 3);

        }

        // draw RandomRambler
        g.setFill(Color.PINK);
        g.fillRoundRect(this.ranRam.getX() * fieldWidth, this.ranRam.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);


        // draw 'player'
        g.setFill(Color.BLUE);
        g.fillRoundRect(this.player.getX() * fieldWidth, this.player.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);
    }

    public void reactToWalls(GameObject o) {


        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {

                if(i%2 == 0 && j %3 == 0){

                    Wall w = walls[i][j];

                    if(o.atWall(w) == "LEFT" && o.getDir() == "LEFT"){
                        o.setX(o.getX() + 1);
                        o.applyRepeller(w);
                    }
                    if(o.atWall(w) == "RIGHT" && o.getDir() == "RIGHT"){
                        o.setX(o.getX() - 1);
                        o.applyRepeller(w);
                        o.setDir("LEFT");
                    }
                    if(o.atWall(w)== "UP" && o.getDir() == "UP"){
                        o.setY(o.getY() + 1);
                        o.applyRepeller(w);

                    }

                    if(o.atWall(w)== "DOWN" && o.getDir() == "DOWN"){
                        o.setY(o.getY() - 1);
                        o.applyRepeller(w);
                        o.setDir("UP");
                    }
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
    void createGrid(){

        grid = new Node[30][20];

        for (int i = 0; i < 30; i++){
            for(int j = 0; j < 20; j++){

                grid[i][j] = new Node(i, j, true);

            }
        }
    }


    public List<Node> getNeighbours(Node n){

        List<Node> neighbours = new ArrayList<>();

        for(int i =  - 1; i <=   1; i++){
            for(int j =  -1; j <= 1; j++){

                if(n.pos.x == i && n.pos.y == j || i == -1 && j == -1 || i == 1 && j == 1 || i == -1 && j == 1 || i == 1 && j == -1){

                }else{

                    int checkX = n.pos.x +i;
                    int checkY = n.pos.y + j;

                    if(checkX >= 0 && checkX < 30 && checkY >= 0 && checkY < 20){
                        neighbours.add(grid[checkX][checkY]);

                    }
                }
            }
        }
        return neighbours;
    }


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

    public void myBFS(MathVector startPos, MathVector endPos){

        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node currentNode;

        Node startNode = new Node(startPos.x, startPos.y, true);
        Node targetNode = new Node(endPos.x, endPos.y, true);

        openList.add(startNode);

        while(!openList.isEmpty()){

            currentNode = openList.get(0);
            openList.remove(currentNode);
            closedList.add(currentNode);


            //   if(currentNode.pos.getPos() == targetNode.pos.getPos()){
            if(currentNode.pos.x == targetNode.pos.x && currentNode.pos.y == targetNode.pos.y){
                targetNode = currentNode;
                closed = closedList;
                retracePath(startNode, targetNode);
                return;

            }

            for (Node n : getNeighbours(currentNode)){

                if(closedList.contains(n) || !n.walkable){
                    continue;
                }
                if(!openList.contains(n)){
                    n.parent = currentNode;
                    openList.add(n);
                }
            }

        }


    }


    class Node{

        MathVector pos;
        Node child;
        Node parent;
        Node root;
        boolean walkable;
        List<Node> childNodes;

        public Node(int x, int y, boolean walkable){

            pos = new MathVector(x, y);
            //  childNodes = new ArrayList<>();
            parent = null;
            this.walkable = walkable;

        }


        public void setChild(Node n){this.child = n;}
        public Node getChild(){return child;}

        public String toString(){

            StringBuilder sb = new StringBuilder();
            sb.append("x = " + pos.x);
            sb.append(" Y = " + pos.y);
            sb.append(" Parent node = " + parent);
            sb.append(" child node = " + child);

            return sb.toString();


        }


    }


}
