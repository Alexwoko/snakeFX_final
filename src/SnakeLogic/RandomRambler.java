package SnakeLogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.*;


public class RandomRambler extends MovingObject {

    private double fieldWidth;
    private double fieldHeight;


    public RandomRambler(int x, int y) {
        super(x, y);
        setMaxSpeed(1f);
        fieldWidth = 20;
        fieldHeight = 17.85;


    }




    @Override
    public void applyRepeller(GNode node) {

        MathVector force = node.repel(this);
        this.applyForce(force);
    }

    public void followPath() {

        if (getMyPath() != null) {

            for (int i = 0; i < getMyPath().size(); i++) {

                GraphItem t = getMyPath().get(i); //    path.get(i);

                this.setX(getMyPath().get(i).getX());
                this.setY(getMyPath().get(i).getY());
                break;

            }
        }
    }

    public void displaySelf(GraphicsContext g, Color c) {


        g.setFill(c);
        g.fillRoundRect(this.getX() * fieldWidth, this.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

    }

    public void displayPath(GraphicsContext g, Color c) {

        if (this.getMyPath() != null) {
            for (GraphItem t : this.getMyPath()) {
                g.setFill(c);
                g.fillRoundRect(t.getX() * fieldWidth, t.getY() * fieldHeight, fieldWidth, fieldHeight, 3, 3);

            }
        }
    }

    @Override
    public void update() {


        getVel().add(getAccel());
        getVel().limit(1f);
        getPos().add(getVel());
        getAccel().mult(0f);


    }
}
