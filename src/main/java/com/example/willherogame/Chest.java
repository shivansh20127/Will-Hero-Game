package com.example.willherogame;

import java.io.Serializable;

public abstract class Chest extends GameObject implements Serializable
{
    protected boolean used;
    
    public Chest(double x, double y, Game game) {
        super(game);
        this.path = "/Assets/Images/closedchest.png";
        this.setCoordinates(x, y);
        this.height = 40;
        this.used = false;
    }
    
    public abstract void heroEffect(Hero hero);
}