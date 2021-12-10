package com.example.willherogame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AppController
{
    @FXML
    public void showCreateAccountPage(ActionEvent event) throws IOException
    {
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
        ScrollPane scrollPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane);
        stage.setScene(scene);
    }

    @FXML
    public void showsettings(MouseEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("temp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.setTitle("Settings screen");
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    public void backtomain(MouseEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage1.setScene(scene);
        stage1.show();
    }

    @FXML
    private ImageView musicicon;
    private boolean music=true;
    @FXML
    public void no_music(MouseEvent event) throws IOException
    {
        Image newimage;
        if (music)
        {
            newimage= new Image(getClass().getResourceAsStream("Images/no_music_icon.png"));
            music = false;
        }
        else
        {
            newimage= new Image(getClass().getResourceAsStream("Images/music_icon.png"));
            music = true;
        }
        musicicon.setImage(newimage);
    }

    @FXML
    private ImageView soundicon;
    private boolean sound=true;
    @FXML
    public void no_sound(MouseEvent event) throws IOException
    {
        Image newimage;
        if(sound)
        {
            newimage= new Image(getClass().getResourceAsStream("Images/no_sound_icon.png"));
            sound = false;
        }
        else
        {
            newimage= new Image(getClass().getResourceAsStream("Images/sound_icon.png"));
            sound= true;
        }
        soundicon.setImage(newimage);
    }

    @FXML
    private Button logoutbutton;
    @FXML
    private AnchorPane scenePane;
    Stage stage;
    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?:");

        if(alert.showAndWait().get() == ButtonType.OK)
        {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }
}