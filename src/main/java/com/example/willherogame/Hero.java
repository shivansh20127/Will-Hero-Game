package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Hero extends GameObject implements Serializable
{
    private static final double heroScale = 0.6;
    private static final double widthScale = 0.7;
    private static final double heightScale = 1;
    private double v_y;
    private static final double GRAVITY = 1.0;
    private static final double MAX_Y = 14;
    private transient Timeline jumpTimeline;
    private int collectedCoins;
    private int currentWeapon;
    private boolean resurrected;
    
    public Hero(Game game) {
        super(game);
        this.path = "/Assets/Images/hero1.png";
        this.width = 84 * heroScale;
        this.height = 65 * heroScale;
        this.v_y = 0;
        this.jumpTimeline = null;
        this.collectedCoins = 0;
        this.resurrected = false;
        // -1 -------> no weapon
        // 0 --------> shuriken
        // 1 --------> throwing knife
        this.currentWeapon = -1;
        setCoordinates(220, 0);
    }
    
    public int getCurrentWeapon() {
        return currentWeapon;
    }
    
    public void setCollectedCoins(int collectedCoins) {
        this.collectedCoins = collectedCoins;
    }
    
    public void setCurrentWeapon(int currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public Game getGame() {return this.game;}
    
    public void startJumping() {
        // movement in y direction starts / resumes
        if (jumpTimeline == null) {
            jumpTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                try {
                    moveHeroY();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }));
            jumpTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        jumpTimeline.play();
        game.getTimelines().add(jumpTimeline);
        
    }
    
    public void pauseJumping() {
        jumpTimeline.pause();
    }
    
    private void moveHeroY() throws IOException {
        // movement in Y direction
        
        img.setY(img.getY() + v_y);
        getCoordinates().setY(getCoordinates().getY() + v_y);
        v_y += GRAVITY;
        if (isSurfaceCollidingWithIsland()) {
            v_y = -MAX_Y;
        }
        checkOrcCollision();
        checkCoinCollision();
        checkObstacleCollision();
        checkChestCollision();
        checkFall();
    }
    
    private void checkFall() throws IOException {
        if (img.getY() > 450) {
            resurrectHero();
        }
    }
    
    private void checkObstacleCollision() throws IOException {
        for (Obstacle obstacle : game.getObstacles()) {
            if (obstacle.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
                resurrectHero();
                break;
            }
        }
    }
    
    private void checkChestCollision() {
        for (Chest chest : game.getChests()) {
            if (chest.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
                chest.heroEffect(this);
                break;
            }
        }
    }
    
    private void checkCoinCollision() {
        for (Coin coin : game.getCoins()) {
            if (coin.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
                coin.getImg().setX(-100);
                collectedCoins++;
            }
        }
    }
    
    private void resurrectHero() throws IOException {
        // resurrect
        // game over
        jumpTimeline.pause();
        if (!resurrected && collectedCoins > 20) {
            game.askResurrection();
            if (!game.isToResurrect()) game.endGame();
            else jumpTimeline.play();
        }
        
        else game.endGame();
    }
    
    private void checkOrcCollision() throws IOException {
        for (Orc orc : game.getOrcs()) {
            if (isColliding(orc)) {
                int col = collisionType(orc);
                if (col == 0) {
                    System.out.println("FACE COLLISION");
                    TranslateTransition tt = new TranslateTransition(Duration.millis(100), orc.getImg());
                    if (orc.getClass() == BossOrc.class)  tt.setDuration(Duration.millis(10));
                    tt.setByX(-orc.getToMove());
                    tt.setCycleCount(1);
                    tt.play();
                }
                else if (col == -1) {
                    System.out.println("BOTTOM COLLISION");
                    resurrectHero();
//                    // resurrect
//                    // game over
                    orc.getJumpTimeline().stop();
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
        
        if (yOverlap > xOverlap) return 0;
        if (heroYStart < orcYStart) return 1;
        return -1;
    }
    
    private boolean isSurfaceCollidingWithIsland() {
        for (Island island : game.getIslands()) {
            if (island.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) return true;
        }
        return false;
    }
    
    public Timeline getJumpTimeline() {return jumpTimeline;}

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
