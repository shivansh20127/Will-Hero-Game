package com.example.willherogame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController
{
    public void showCreateAccountPage(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("create-account.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    public void showLoginPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    public void showMainPage(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-page.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
    }
    
    public void showSavedGames(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("show-saved-games.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(scrollPane);
        stage.setScene(scene);
    }
    
}