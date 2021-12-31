package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable
{
    @FXML
    private AnchorPane root;
    @FXML
    private Label coinDisplay;
    
//    private int collectedCoins;
    private static ArrayList<GameObject> gameObjects;
    private static ArrayList<Island> islands;
    private static ArrayList<Orc> orcs;
    private static ArrayList<Coin> coins;
    private static Hero hero;
    private Random random;
    private Game game;
    
    private final int noOfIslands = 20;
    
    public static ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    
    public static ArrayList<Coin> getCoins() {
        return coins;
    }
    
    public static ArrayList<Island> getIslands() {
        return islands;
    }
    
    public static ArrayList<Orc> getOrcs() {
        return orcs;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coinDisplay.setText("0");
//        collectedCoins = 0;
        this.random = new Random();
        hero = new Hero();
        islands = new ArrayList<>();
        orcs = new ArrayList<>();
        coins = new ArrayList<>();
        gameObjects = new ArrayList<>();
        createDefaultIslands();
        createDefaultOrcs();
        createDefaultCoins();
        hero.renderImage(root);
        renderGameObjects();
        startAnimation();
        this.game = new Game(hero);
        hero.setGame(game);
    }
    
    private void startAnimation() {
        hero.startJumping();
        startOrcJumping();
        hero.getJumpTimeline().getKeyFrames().add(new KeyFrame(Duration.ZERO, event -> {
            coinDisplay.setText(Integer.toString(hero.getCollectedCoins()));}));
    }
    
    private void startOrcJumping() {
        for (Orc orc : orcs) {
            orc.startJumping();
        }
    }
    
    private void createDefaultIslands() {
        for (int i = 0; i < noOfIslands; i++) {
            Island island = new Island(350 * i - 100, 310);
            islands.add(island);
            gameObjects.add(island);
        }
    }
    
    private void createDefaultOrcs() {
        for (int i = 2; i < noOfIslands; i += 2) {
            double orcX = islands.get(i).getCoordinates().getX() + 30;
            double orcY = islands.get(i).getCoordinates().getY() - 120;
            
            Orc orc = null;
            if (i % 4 == 0) orc = new WeakOrc(orcX, orcY);
            else orc = new StrongOrc(orcX + 50, orcY);
            orcs.add(orc);
            gameObjects.add(orc);
        }
    }
    
    private void createDefaultCoins() {
        for (int i = 1; i < noOfIslands; i += 2) {
            int offset = (random.nextInt() % 30) + 50;
            double coiny = islands.get(i).getCoordinates().getY() - offset;
            for (int j = 0; j < 5; j++) {
                double coinx = islands.get(i).getCoordinates().getX() + 30 + 20 * j;
                Coin coin = new Coin(coinx, coiny);
                coins.add(coin);
                gameObjects.add(coin);
            }
        }
    }
    
    private void renderGameObjects() {
        for (GameObject go : gameObjects) {
            go.renderImage(root);
        }
    }
    
    @FXML
    public void moveClick(Event e) {
        coinDisplay.setText(Integer.toString(hero.getCollectedCoins()));
        gameObjects.get(0).moveAllBack(gameObjects);
    }
    
    @FXML
    public void showInGameSettings(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ingame_settings.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    
    public static Hero getHero() {return hero;}
    
//    public void incrementCoins() {
//        collectedCoins++;
//        this.getCoinDisplay().setText(Integer.toString(collectedCoins));
//    }
////
//    public Label getCoinDisplay() { return coinDisplay; }
}


//package com.example.willherogame;
//
//import javafx.animation.Ke
// yFrame;
//import javafx.animation.Timeline;
//import javafx.event.Event;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.ImageCursor;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class GamePlayController
//{
//    private ArrayList<GameObject> gameObjects;
//    @FXML
//    private ImageView hero;
//    @FXML
//    private AnchorPane anchorPane;
//    private ArrayList<Island> allIslands;
//    private ArrayList<Orc> orcs;
//    private ArrayList<Tree> trees;
//    private ArrayList<Weapon> weapons;
//
//    @FXML
//    ImageView island1;
//    @FXML
//    ImageView island2;
//    @FXML
//    ImageView island3;
//
//    private ArrayList<ImageView> islands;
//    private static final double GRAVITY = 1.0;
//    private static final double MAX_Y = 14;
//    private double v_y;
//    private double v_x;
//    private double distanceToMove;
//
//
//    //    public GamePlayController(AnchorPane anchorPane) {
////        this.anchorPane = anchorPane;
////    }
////
//    public void initialize() {
//
//        v_y = 0.0;
//        v_x = 0.0;
//        distanceToMove = 0.0;
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> moveHero()));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//        islands = new ArrayList<>();
//        islands.add(island1);
//        islands.add(island2);
//        islands.add(island3);
//    }
//
//

//
//    public void moveHero() {
//        // movement in y direction
//        hero.setLayoutY(hero.getLayoutY() + v_y);
//        v_y += GRAVITY;
//        if (isColliding()) v_y = -MAX_Y;
//
//        // movement in x direction
//        if (distanceToMove > 0 && v_x > 0.0) {
//            hero.setLayoutX(hero.getLayoutX() + v_x);
//            distanceToMove -= v_x;
//        }
//        else {
//            distanceToMove = 0.0;
//            v_x = 0.0;
//        }
//    }
//
//    public boolean isColliding()
//    {
//        for (ImageView island : islands) {
//            if (hero.getLayoutX() + hero.getFitWidth() - 10 >= island.getLayoutX() + 10 &&
//                    hero.getLayoutX() + 10 <= island.getLayoutX() + island.getFitWidth() - 10 &&
//                    hero.getLayoutY() + hero.getFitHeight() >= island.getLayoutY()) {
//                System.out.println("COLLISION WITH: " + island);
//                return true;
//            }
//        }
//        return false;
//    }
//}
