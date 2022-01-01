package com.example.willherogame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;

public class Application extends javafx.application.Application
{
    private static Database database;
    private static Stage mainRootStage;
    
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        mainRootStage = stage;
        deserialize();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Will Hero");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                logout(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    private static void deserialize() throws ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("database.txt"));
            database = (Database) in.readObject();
            in.close();
        }
        catch (NullPointerException | IOException e) {
            database = new Database();
        }
    }
    
    private static void serialize() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.txt"))) {
            out.writeObject(database);
        }
    }
    
    public static Database getDatabase() { return database; }
    
    // to be preferably put in AppController
    public void logout(Stage stage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?:");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            if (database != null) serialize();
            stage.close();
        }
    }
    
    public static Stage getStage() { return mainRootStage; }
    
    public static void main(String[] args) {
        launch();
    }
}