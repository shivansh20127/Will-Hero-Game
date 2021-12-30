package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Orc extends GameObject
{
    private static final double widthScale = 0.7;
    private static final double heightScale = 1;
    private double v_y;
    private static final double GRAVITY = 1.0;
    protected double MAX_Y;
    private Timeline jumpTimeline;
    protected static double orcScale;
    
    
    public Orc(double x, double y) {
        super();
        this.setCoordinates(x, y);
        orcScale = 0.6;
    }
    
    
    public void startJumping(ArrayList<Island> islands) {
        // movement in y direction starts / resumes
        if (jumpTimeline == null) {
            jumpTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveHeroY(islands)));
            jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        jumpTimeline.play();
    }
    
    public void pauseJumping() {
        jumpTimeline.pause();
    }
    
    private void moveHeroY(ArrayList<Island> islands) {
        // movement in Y direction
        
        img.setY(img.getY() + v_y);
        getCoordinates().setY(getCoordinates().getY() + v_y);
        v_y += GRAVITY;
        if (isSurfaceCollidingWithIsland(islands)) {
            v_y = -MAX_Y;
        }
    }
    
    private boolean isSurfaceCollidingWithIsland(ArrayList<Island> islands) {
        for (Island island : islands) {
            double islandX = island.getCoordinates().getX();
            double islandY = island.getCoordinates().getY() - 5;
            double heroWidth = img.getFitWidth() * widthScale;
            double heroHeight = img.getFitHeight() * heightScale;
            double heroX = getCoordinates().getX() + img.getFitWidth() - heroWidth;
            double heroY = getCoordinates().getY();
            double islandWidth = island.getImg().getFitWidth();
            
            if (heroX + heroWidth >= islandX &&
                    heroX <= islandX + islandWidth &&
                    heroY + heroHeight >= islandY) {
                return true;
            }
        }
        return false;
    }
}
