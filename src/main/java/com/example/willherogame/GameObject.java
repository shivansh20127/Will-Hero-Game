package com.example.willherogame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public abstract class GameObject implements Serializable
{
    private Coordinates coordinates;
    protected String path;
    protected ImageView img;
    protected double width, height;
    protected static Timeline objTimeline;
    protected double v_x = 10;
    protected double toMove = -100;
    
    public double getToMove() {
        return toMove;
    }
    
    public void setToMove(double toMove) {
        this.toMove = toMove;
    }
    
    public double getV_x() {
        return v_x;
    }
    
    public GameObject() {
        objTimeline = null;
        this.coordinates = new Coordinates(0, 0);
    }
    
    public void renderImage(AnchorPane pane) {
        String url = Objects.requireNonNull(getClass().getResource(path)).toString();
        img = new ImageView(new Image(url, width, height, true, false));
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
    
    private void moveBack(GameObject go) {
        objTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveObjectBack(go)));
        objTimeline.setCycleCount(10);
        objTimeline.play();

//        TranslateTransition tt = new TranslateTransition(Duration.millis(100), go.getImg());
//        tt.setByX(go.getToMove());
//        tt.setCycleCount(1);
//        tt.play();
    }
    
    public void moveObjectBack(GameObject go) {
        if (go.getClass().equals(WeakOrc.class) || go.getClass().equals(StrongOrc.class)) {
            Orc orcc = (Orc) go;
            orcc.moveObjectBack();
        }
        else if (go.getClass().equals(BossOrc.class)) {
            BossOrc bossOrc = (BossOrc) go;
            bossOrc.moveObjectBack();
        }
        else {
            go.getImg().setLayoutX(go.getImg().getLayoutX() - getV_x());
            go.setXCoordinate(go.getCoordinates().getX() + go.getToMove());
        }
    }
    
    public void setV_x(double vx) {this.v_x = vx;}
    
    
    public void moveAllBack(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) moveBack(go);
    }
    
}
