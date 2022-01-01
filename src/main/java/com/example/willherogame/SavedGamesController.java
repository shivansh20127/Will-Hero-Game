package com.example.willherogame;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SavedGamesController implements Initializable
{
    
    @FXML
    private AnchorPane savedGamesPage;
    
    private ToggleGroup toggleGroup;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        toggleGroup = new ToggleGroup();
        int _count = 0;
        for (Game game : Application.getDatabase().getSavedGames()) {
            RadioButton radioButton = new RadioButton();
            System.out.println(game);
            radioButton.setText(game.toString());
            radioButton.setFont(new Font(26));
            radioButton.setLayoutX(80);
            radioButton.setLayoutY(140 + 50 * _count);
            radioButton.setMinHeight(270);
            radioButton.setMaxHeight(270);
            radioButton.setMinWidth(50);
            radioButton.setMaxWidth(50);
            toggleGroup.getToggles().add(radioButton);
            savedGamesPage.getChildren().add(radioButton);
            _count++;
        }
    }
    
    
    @FXML
    public void resumeGame(Event event) throws IOException {
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
        int id = Integer.parseInt(selectedButton.getText().split(",")[0].substring(5));
        AppController.setCurrentGame(Application.getDatabase().getGameByID(id));
        if (AppController.getCurrentGame() == null) {
            System.out.println("game not found, id = " + id);
            System.exit(0);
        }
        AppController.getCurrentGame().initialiseData();
        AppController.showGameplay(event);
    }
    
    @FXML
    public void showMainPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
}
