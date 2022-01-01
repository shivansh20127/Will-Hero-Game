package com.example.willherogame;

import java.io.Serializable;

public abstract class Weapon extends GameObject implements Serializable
{
    public Weapon(double x, double y, Game game) {
        super(game);
        this.setCoordinates(x, y);
        this.height = 20;
        this.width = 20;
    }
    
}
