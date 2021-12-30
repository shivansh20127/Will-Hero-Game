package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
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
    
    public void moveObjectBack() {
        ImageView hero = GamePlayController.getHero().getImg();
        if (hero.getBoundsInParent().intersects(getImg().getBoundsInParent())) {
            objTimeline.stop();
            TranslateTransition tt = new TranslateTransition(Duration.millis(100), getImg());
            tt.setByX(-getToMove());
            tt.setCycleCount(1);
            tt.play();
        }
        else {
            getImg().setLayoutX(getImg().getLayoutX() - getV_x());
            setXCoordinate(getCoordinates().getX() + getToMove());
        }
    }
    
    public void startJumping(ArrayList<Island> islands) {
        // movement in y direction starts / resumes
        if (jumpTimeline == null) {
            jumpTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveOrcY(islands)));
            jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        jumpTimeline.play();
    }
    
    public void pauseJumping() {
        jumpTimeline.pause();
    }
    
    private void moveOrcY(ArrayList<Island> islands) {
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
            if (island.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) return true;
        }
        return false;
    }
//    private boolean isSurfaceCollidingWithIsland(ArrayList<Island> islands) {
//        for (Island island : islands) {
//            double islandX = island.getCoordinates().getX();
//            double islandY = island.getCoordinates().getY() - 5;
//            double orcWidth = img.getFitWidth() * widthScale;
//            double orcHeight = img.getFitHeight() * heightScale;
//            double orcX = getCoordinates().getX() + img.getFitWidth() - orcWidth;
//            double orcY = getCoordinates().getY();
//            double islandWidth = island.getImg().getFitWidth();
//
//            if (orcX + orcWidth >= islandX &&
//                    orcX <= islandX + islandWidth &&
//                    orcY + orcHeight >= islandY) {
//                return true;
//            }
//        }
//        return false;
//    }
}
