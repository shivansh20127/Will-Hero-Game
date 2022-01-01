package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable
{
    @FXML
    private AnchorPane gameplayRoot;
    @FXML
    private Label coinDisplay;
    @FXML
    private Label positionDisplay;
    
    private Game game;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.game = AppController.getCurrentGame();
        game.setRoot(gameplayRoot);
        game.renderObjects();
        coinDisplay.setText("0");
        positionDisplay.setText(Integer.toString(game.getCurrentPosition()));
        game.getHero().getJumpTimeline().getKeyFrames().add(new KeyFrame(Duration.ZERO, event -> {
            coinDisplay.setText(Integer.toString(game.getHero().getCollectedCoins()));
        }));
    }
    
    @FXML
    public void moveClick(Event e) {
        coinDisplay.setText(Integer.toString(game.getHero().getCollectedCoins()));
        game.getGameObjects().get(0).moveAllBack(game.getGameObjects());
        game.updateCurrentPosition();
        positionDisplay.setText(Integer.toString(game.getCurrentPosition()));
    }
    
    @FXML
    public void showPauseMenu(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("pause-menu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
}