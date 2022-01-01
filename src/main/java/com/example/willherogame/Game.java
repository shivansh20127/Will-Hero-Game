package com.example.willherogame;

import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable
{
    private static int gameCount = 0;
    private int gameID;
    private final Hero hero;
    private int currentPosition;
    private final ArrayList<GameObject> gameObjects;
    private final ArrayList<Island> islands;
    private final ArrayList<Orc> orcs;
    private final ArrayList<Coin> coins;
    private final ArrayList<Timeline> timelines;
    private transient AnchorPane root;
    private final int noOfIslands;
    private final Random random;
    
    public Game(int noOfIslands) {
        this.random = new Random();
        this.hero = new Hero(this);
        this.currentPosition = 0;
        this.noOfIslands = noOfIslands;
        this.gameID = gameCount;
        gameCount++;
        islands = new ArrayList<>();
        gameObjects = new ArrayList<>();
        orcs = new ArrayList<>();
        coins = new ArrayList<>();
        timelines = new ArrayList<>();
    }
    
    public void setRoot(AnchorPane anchorPane) {
        root = anchorPane;
    }
    
    public Hero getHero() {
        return hero;
    }
    
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    
    public ArrayList<Island> getIslands() {
        return islands;
    }
    
    public ArrayList<Orc> getOrcs() {
        return orcs;
    }
    
    public ArrayList<Coin> getCoins() {
        return coins;
    }
    
    public ArrayList<Timeline> getTimelines() {
        return timelines;
    }
    
    public void updateCurrentPosition() {
        currentPosition++;
    }
    
    public void initialiseData() {
        createDefaultIslands();
        createDefaultOrcs();
        createDefaultCoins();
    }
    
    public void renderObjects() {
        hero.renderImage(root);
        renderGameObjects();
        startAnimation();
    }
    
    
    private void createDefaultIslands() {
        for (int i = 0; i < noOfIslands; i++) {
            Island island = new Island(350 * i - 100, 310, this);
            getIslands().add(island);
            getGameObjects().add(island);
        }
    }
    
    private void createDefaultOrcs() {
        for (int i = 2; i < noOfIslands; i += 2) {
            double orcX = getIslands().get(i).getCoordinates().getX() + 30;
            double orcY = getIslands().get(i).getCoordinates().getY() - 120;
            
            Orc orc = null;
            if (i % 4 == 0) orc = new WeakOrc(orcX, orcY, this);
            else orc = new StrongOrc(orcX + 50, orcY, this);
            getOrcs().add(orc);
            getGameObjects().add(orc);
        }
    }
    
    private void createDefaultCoins() {
        for (int i = 1; i < noOfIslands; i += 2) {
            int offset = (random.nextInt() % 30) + 50;
            double coinY = getIslands().get(i).getCoordinates().getY() - offset;
            for (int j = 0; j < 5; j++) {
                double coinX = getIslands().get(i).getCoordinates().getX() + 30 + 20 * j;
                Coin coin = new Coin(coinX, coinY, this);
                getCoins().add(coin);
                getGameObjects().add(coin);
            }
        }
    }
    
    private void renderGameObjects() {
        for (GameObject go : getGameObjects()) {
            go.renderImage(root);
        }
    }
    
    private void startAnimation() {
        hero.startJumping();
        startOrcJumping();
    }
    
    private void startOrcJumping() {
        for (Orc orc : orcs) {
            orc.startJumping();
        }
    }
    
    public int getGameID() {
        return gameID;
    }
    
    @Override
    public String toString() {
        return "Game-" + gameID + ", Score - " + currentPosition;
    }
    
}
