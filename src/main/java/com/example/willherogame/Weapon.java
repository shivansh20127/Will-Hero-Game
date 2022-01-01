package com.example.willherogame;

import java.io.Serializable;

public abstract class Weapon extends GameObject implements Serializable
{
    public Weapon(Game game) {
        super(game);
    }
    
}
