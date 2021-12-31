package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Hero extends GameObject
{
    private static final double heroScale = 0.6;
    private static final double widthScale = 0.7;
    private static final double heightScale = 1;
    private double v_y;
    private static final double GRAVITY = 1.0;
    private static final double MAX_Y = 14;
    private Timeline jumpTimeline;
    private int collectedCoins;
    private Game game;
    
    
    public Hero() {
        super();
        this.path = "/Assets/Images/hero1.png";
        this.width = 84 * heroScale;
        this.height = 65 * heroScale;
        this.v_y = 0;
        this.jumpTimeline = null;
        this.collectedCoins = 0;
        setCoordinates(220, 0);
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public void startJumping() {
        // movement in y direction starts / resumes
        if (jumpTimeline == null) {
            jumpTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveHeroY()));
            jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        jumpTimeline.play();
    }
    
    public void pauseJumping() {
        jumpTimeline.pause();
    }
    
    private void moveHeroY() {
        // movement in Y direction
        
        img.setY(img.getY() + v_y);
        getCoordinates().setY(getCoordinates().getY() + v_y);
        v_y += GRAVITY;
        if (isSurfaceCollidingWithIsland()) {
            v_y = -MAX_Y;
        }
        checkOrcCollision();
        checkCoinCollision();
    }
    
    private void checkCoinCollision() {
        for (Coin coin : GamePlayController.getCoins()) {
            if (coin.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
                coin.getImg().setX(-100);
                collectedCoins++;
            }
        }
    }
    
    private void checkOrcCollision() {
        for (Orc orc : GamePlayController.getOrcs()) {
            if (isColliding(orc)) {
                int col = collisionType(orc);
                if (col == 0) {
                    System.out.println("FACE COLLISION");
                    TranslateTransition tt = new TranslateTransition(Duration.millis(100), orc.getImg());
                    tt.setByX(-orc.getToMove());
                    tt.setCycleCount(1);
                    tt.play();
                }
                else if (col == -1) {
                    System.out.println("BOTTOM COLLISION");
//                    // resurrect
//                    // game over
                    jumpTimeline.stop();
                }
                else {
                    System.out.println("TOP COLLISION");
                    // jump over orc
                    v_y = -MAX_Y;
                }
                break;
            }
        }
    }
    
    public int getCollectedCoins() {
        return collectedCoins;
    }
    
    private boolean isColliding(Orc orc) {
        return getImg().getBoundsInParent().intersects(orc.getImg().getBoundsInParent());
    }
    
    
    private int collisionType(Orc orc) {
        double heroXStart = getCoordinates().getX();
        double heroXEnd = heroXStart + getImg().getFitWidth();

        double orcXStart = orc.getCoordinates().getX();
        double orcXEnd = orcXStart + orc.getImg().getFitWidth();

        double heroYStart = getCoordinates().getY();
        double heroYEnd = heroYStart + getImg().getFitHeight();

        double orcYStart = orc.getCoordinates().getY();
        double orcYEnd = orcYStart + orc.getImg().getFitHeight();

        double xOverlap = Math.min(heroXEnd - orcXStart, orcXEnd - heroXStart);
        double yOverlap = Math.min(heroYEnd - orcYStart, orcYEnd - heroYStart);
    
//        System.out.println(orc.getCoordinates().getX());
//        System.out.println(getCoordinates().getX());
//        System.out.println(heroYStart + ", " + heroYEnd);
//        System.out.println(orcYStart + ", " + orcYEnd);
//        System.out.println(xOverlap);
//        System.out.println(yOverlap);
        if (yOverlap > xOverlap) return 0;
        if (heroYStart < orcYStart) return 1;
        return -1;
    }
    
    private boolean isSurfaceCollidingWithIsland() {
        for (Island island : GamePlayController.getIslands()) {
            if (island.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) return true;
        }
        return false;
    }
    
    public Timeline getJumpTimeline() { return jumpTimeline; }
    
//    private boolean isSurfaceCollidingWithIsland(ArrayList<Island> islands) {
//        for (Island island : islands) {
//
//            double islandX = island.getCoordinates().getX();
//            double islandY = island.getCoordinates().getY() - 5;
//            double heroWidth = img.getFitWidth() * widthScale;
//            double heroHeight = img.getFitHeight() * heightScale;
//            double heroX = getCoordinates().getX() + img.getFitWidth() - heroWidth;
//            double heroY = getCoordinates().getY();
//            double islandWidth = island.getImg().getFitWidth();
//
//            if (heroX + heroWidth >= islandX &&
//                    heroX <= islandX + islandWidth &&
//                    heroY + heroHeight >= islandY) {
//                return true;
//            }
//        }
//        return false;
//    }
    
    
}
