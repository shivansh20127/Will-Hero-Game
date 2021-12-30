package com.example.willherogame;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GameObject implements Serializable
{
    private Coordinates coordinates;
    protected String path;
    protected ImageView img;
    protected double width, height;
    protected static Timeline objTimeline;
    protected double v_x;
    private double forwardDistanceToMove;
    
    public GameObject() {
        objTimeline = null;
        this.v_x = 0;
        this.forwardDistanceToMove = 0;
        this.coordinates = new Coordinates(0, 0);
    }
    
    public void renderImage(AnchorPane pane) {
//        System.out.println("Path: " + this.path);
        String url = getClass().getResource(path).toString();
        img = new ImageView(new Image(url, width, height, true, false));
//        System.out.println(url);
//        imageView = new ImageView(new Image(url, width, height, true, false));
//        imageView = new ImageView(new Image(path, width, height, true, false));
        img.setX(coordinates.getX());
        img.setY(coordinates.getY());
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setPreserveRatio(true);
        pane.getChildren().add(img);
    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setCoordinates(double x, double y) {
        this.coordinates.setX(x);
        this.coordinates.setY(y);
    }
    
    public void setXCoordinate(double x) {
        this.coordinates.setX(x);
    }
    
    public void setYCoordinate(double y) {
        this.coordinates.setY(y);
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public ImageView getImg() {return img;}
    
    private static void moveBack(GameObject go) {
        // movement in x direction when hero moves forward
//        moveBackTimeline.getKeyFrames().add()
        int offset = -100;
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), go.getImg());
        tt.setByX(offset);
        tt.setCycleCount(1);
        tt.play();
        go.setXCoordinate(go.getCoordinates().getX() + offset);
    }
    
    public static void moveAllBack(ArrayList<GameObject> gameObjects) {
//        Timeline moveBackTimeline = new Timeline();
        for (GameObject go : gameObjects) moveBack(go);
//        moveBackTimeline.setCycleCount(1);
//        moveBackTimeline.play();
//        System.out.println(gameObjects.get(0).getImg().getX());
//        System.out.println(gameObjects.get(0).getImg().getLayoutY());
//        System.out.println(gameObjects.get(0).getImg().getTranslateX());
//        System.out.println(gameObjects.get(1).getCoordinates().getX());
//        System.out.println(gameObjects.get(1).getCoordinates().getX() + gameObjects.get(1).getImg().getFitWidth());
    }
}
