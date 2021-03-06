package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Orc extends GameObject implements Serializable
{
    private static final double widthScale = 0.7;
    private static final double heightScale = 1;
    private double v_y;
    private static final double GRAVITY = 1.0;
    protected double MAX_Y;
    private transient Timeline jumpTimeline;
    protected static double orcScale;
    
    public Orc(double x, double y, Game game) {
        super(game);
        this.setCoordinates(x, y);
        orcScale = 0.6;
    }
    
    public void moveObjectBack() {
//        ImageView hero = GamePlayController.getHero().getImg();
//        if (hero.getBoundsInParent().intersects(getImg().getBoundsInParent())) {
//            objTimeline.stop();
//            TranslateTransition tt = new TranslateTransition(Duration.millis(100), getImg());
//            tt.setByX(-getToMove());
//            tt.setCycleCount(1);
//            tt.play();
//        }
//        else {
        getImg().setLayoutX(getImg().getLayoutX() - getV_x());
        setXCoordinate(getCoordinates().getX() + getToMove() / 10);
//        }
    }
    
    public void startJumping() {
        // movement in y direction starts / resumes
        if (jumpTimeline == null) {
            jumpTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveOrcY()));
            jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        jumpTimeline.play();
        game.getTimelines().add(jumpTimeline);
    }
    
    public void pauseJumping() {
        jumpTimeline.pause();
    }
    
    private void moveOrcY() {
        // movement in Y direction
        
        img.setY(img.getY() + v_y);
        getCoordinates().setY(getCoordinates().getY() + v_y);
        v_y += GRAVITY;
        if (isSurfaceCollidingWithIsland()) {
            v_y = -MAX_Y;
        }
        checkWeaponCollision();
        checkFall();
    }
    
    protected void checkFall() {
        if (img.getY() > 450) killOrc();
    }
    
    protected void checkWeaponCollision() {
        for (Weapon w : game.getWeapons()) {
            if (w.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
                killOrc();
                break;
            }
        }
    }
    
    protected void killOrc() {
        setXCoordinate(-1000);
        img.setX(-1000);
        jumpTimeline.stop();
    }
    
    private boolean isSurfaceCollidingWithIsland() {
        for (Island island : game.getIslands()) {
            if (island.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) return true;
        }
        return false;
    }
    
    public Timeline getJumpTimeline() { return jumpTimeline; }
    
//    public Timeline getJumpTimeline() {return jumpTimeline;}

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
