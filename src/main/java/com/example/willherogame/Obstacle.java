package com.example.willherogame;

public class Obstacle extends GameObject
{
    public Obstacle(double x, double y, Game game) {
        super(game);
        this.path = "/Assets/Images/TNT.png";
        this.setCoordinates(x, y);
        this.width = 30;
        this.height = 30;
    }
}