package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<Chest> chests;
    private final ArrayList<Weapon> weapons;
    private transient AnchorPane root;
    private final int noOfIslands;
    private final Random random;
    private boolean toResurrect;
    
    public Game(int noOfIslands) {
        this.toResurrect = false;
        this.random = new Random(12);
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
        obstacles = new ArrayList<>();
        chests = new ArrayList<>();
        weapons = new ArrayList<>();
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
    
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    public ArrayList<Chest> getChests() {
        return chests;
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
        createDefaultObstacles();
        createDefaultChests();
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
    
    private void createDefaultObstacles() {
        for (int i = 3; i < noOfIslands; i += 5) {
            double obx = islands.get(i).getCoordinates().getX() + 30;
            double oby = islands.get(i).getCoordinates().getY() - 30;
            Obstacle obstacle = new Obstacle(obx, oby, this);
            obstacles.add(obstacle);
            gameObjects.add(obstacle);
        }
    }
    
    private void createDefaultChests() {
        for (int i = 2; i < noOfIslands; i += 5) {
            double chestX = islands.get(i).getCoordinates().getX() + 30;
            double chestY = islands.get(i).getCoordinates().getY() - 40;
            Chest chest = null;
            if ((i - 2) % 10 == 0) chest = new CoinChest(chestX, chestY, this);
            else chest = new WeaponChest(chestX, chestY, this);
            chests.add(chest);
            gameObjects.add(chest);
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
    
    public void shootWeapon() {
        Weapon throwWeapon = null;
        double wx = hero.getCoordinates().getX() + 52;
        double wy = hero.getCoordinates().getY() + 10;
        if (hero.getCurrentWeapon() == 0) {
            throwWeapon = new Shuriken(wx, wy, this);
        }
        else if (hero.getCurrentWeapon() == 1) {
            throwWeapon = new Knife(wx, wy, this);
        }
        else return;
        weapons.add(throwWeapon);
        throwWeapon.renderImage(root);
        Weapon finalThrowWeapon = throwWeapon;
        throwWeapon.objTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveWeapon(finalThrowWeapon)));
        throwWeapon.objTimeline.setCycleCount(20);
        throwWeapon.objTimeline.play();
        throwWeapon.objTimeline.setOnFinished(e -> removeWeapon(finalThrowWeapon));
    }
    
    private void moveWeapon(Weapon w) {
        w.getImg().setLayoutX(w.getImg().getLayoutX() + 10);
        w.setXCoordinate(w.getCoordinates().getX() + 10);
    }
    
    private void removeWeapon(Weapon w) {
        w.getImg().setX(-1000);
        w.setXCoordinate(-1000);
    }
    
    public void endGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = Application.getStage();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    public boolean isToResurrect() {
        return toResurrect;
    }
    
    public void askResurrection() throws IOException {
//        AtomicBoolean resAns = new AtomicBoolean(true);
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("res.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage resStage = new Stage();
//        resStage.setScene(scene);
//        resStage.show();
//        resStage.setOnCloseRequest(e -> {
//            e.consume();
//            resAns.set(ResurrectController.getStatus());
//            resStage.close();
//        });
//        this.toResurrect = resAns.get();
    }
    
}
