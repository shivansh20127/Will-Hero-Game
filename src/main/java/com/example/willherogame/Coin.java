package com.example.willherogame;

public class Coin extends GameObject
{
    public Coin(double x, double y) {
        super();
        this.path = "/Assets/Images/coins.png";
        this.setCoordinates(x, y);
        this.width = 12;
        this.height = 12;
    }
}
