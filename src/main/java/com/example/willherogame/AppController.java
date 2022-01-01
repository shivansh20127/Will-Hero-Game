package com.example.willherogame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable
{
    @FXML
    private ImageView soundIcon;
    @FXML
    private ImageView musicIcon;
    
    private boolean sound;
    private boolean music;
    private static Game currentGame;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sound = true;
        music = true;
    }
    
    @FXML
    public void startNewGame(Event event) throws IOException {
        currentGame = new Game(20);
        currentGame.initialiseData();
        Application.getDatabase().addGame(currentGame);
        showGameplay(event);
    }
    
    
    public static Game getCurrentGame() { return currentGame; }
    public static void setCurrentGame(Game game) { currentGame = game; }
    
    @FXML
    public void showCreateAccountPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("create-account.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    @FXML
    public void showLoginPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    @FXML
    public void showMainPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    @FXML
    public void showSavedGames(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("show-saved-games.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    @FXML
    public void showSettings(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ingame_settings.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    public static void showGameplay(Event event) throws IOException {
//        System.out.println("HERE SHOWING");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("gameplay.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    
    
    @FXML
    public void no_music(MouseEvent event) throws IOException {
        Image newImage;
        if (music) {
            newImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/Assets/Images/no_music_icon.png")));
            music = false;
        }
        else {
            newImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/Assets/Images/music_icon.png")));
            music = true;
        }
        musicIcon.setImage(newImage);
    }
    
    
    @FXML
    public void no_sound(MouseEvent event) throws IOException {
        Image newimage;
        if (sound) {
            newimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/Assets/Images/no_sound_icon.png")));
            sound = false;
        }
        else {
            newimage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/Assets/Images/sound_icon.png")));
            sound = true;
        }
        soundIcon.setImage(newimage);
    }
    

//    @FXML
//    private Button logoutbutton;
//    @FXML
//    private AnchorPane scenePane;
//    Stage stage;
    
//    public void logout(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Logout");
//        alert.setHeaderText("You're about to logout!");
//        alert.setContentText("Do you want to save before exiting?:");
//
//        if (alert.showAndWait().get() == ButtonType.OK) {
//            stage = (Stage) scenePane.getScene().getWindow();
//            System.out.println("You successfully logged out!");
//            stage.close();
//        }
//    }
}