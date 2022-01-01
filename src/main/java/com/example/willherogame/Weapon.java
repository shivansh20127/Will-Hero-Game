package com.example.willherogame;

import java.io.Serializable;

public abstract class Weapon extends GameObject implements Serializable
{
    public Weapon(Game game) {
        super(game);
    }
//    public Weapon(Coordinates coordinates, String path, int width, int height) {
//        super(coordinates, path, width, height);
//    }
}
