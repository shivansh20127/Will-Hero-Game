package com.example.willherogame;

import java.io.Serializable;

public class Coin extends GameObject implements Serializable
{
    public Coin(double x, double y, Game game) {
        super(game);
        this.path = "/Assets/Images/coins.png";
        this.setCoordinates(x, y);
        this.width = 12;
        this.height = 12;
    }
}
