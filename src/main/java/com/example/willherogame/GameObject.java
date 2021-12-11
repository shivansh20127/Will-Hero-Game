package com.example.willherogame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public abstract class GameObject implements Serializable
{
    protected Coordinates coordinates;
    protected String path;
    protected ImageView imageView;
    protected int width, height;
    
    public GameObject(Coordinates coordinates, String path, int width, int height) {
        this.coordinates = coordinates;
        this.path = path;
        this.width = width;
        this.height = height;
    }
    
//    public void renderImage(Pane pane) {
//        imageView = new ImageView(new Image(path, width, height, true, false));
//        imageView.setLayoutX(coordinates.getX());
//        imageView.setLayoutY(coordinates.getY());
//        pane.getChildren().add(imageView);
//    }
    
    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public void setXCoordinate(int x) {
        this.coordinates.setX(x);
    }
    
    public void setYCoordinate(int y) {
        this.coordinates.setY(y);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
