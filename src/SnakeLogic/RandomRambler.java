package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;

/**
 * class Random Rambler --> extends MovingObject
 */

public class RandomRambler extends MovingObject {

    /**
     * Class variables
     */

    private double fieldWidth;
    private double fieldHeight;
    private String name;


    /**
     * Class constructor
     * @param x Position X
     * @param y position Y
     * @param name
     */

    public RandomRambler(int x, int y, String name) {
        super(x, y);
        setMaxSpeed(1f);
        fieldWidth = 20;
        fieldHeight = 17.85;
        this.name = name;

    }

    /**
     * Getter for name
     * @return
     */

    public String getName(){return  name;}

    /**
     * Method used in wall detection
     * @param node
     */

    @Override
    public void applyRepeller(GNode node) {

        MathVector force = node.repel(this);
        this.applyForce(force);
    }

    /**
     * Method to follow the path give by Pathfinder class
     */

    public void followPath() {

        if (getMyPath() != null) {

            for (int i = 0; i < getMyPath().size(); i++) {

                GraphItem t = getMyPath().get(i);

                this.setX(t.getX());
                this.setY(t.getY());
                break;

            }
        }
    }

    /**
     * Method to display self
     * @param g GraphicsContext
     * @param c Color
     */

    public void displaySelf(GraphicsContext g, Color c) {


        g.setFill(c);
        g.fillRoundRect(this.getX() * fieldWidth, this.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

    }

    /**
     * method to display the path
     * @param g GraphicsContext
     * @param c Color
     */
    public void displayPath(GraphicsContext g, Color c) {

        if (this.getMyPath() != null) {
            for (GraphItem t : this.getMyPath()) {
                g.setFill(c);
                g.fillRoundRect(t.getX() * fieldWidth, t.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

            }
        }
    }

    /**
     * Update methods
     * updates velocity and position an resets acceleration so it doesn't accumulate
     */

    @Override
    public void update() {


        getVel().add(getAccel());
        getVel().limit(1f);
        getPos().add(getVel());
        getAccel().mult(0f);

    }
}
