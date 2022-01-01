package com.example.willherogame;

import java.io.Serializable;

public abstract class Chest extends GameObject implements Serializable
{
    public Chest(Game game) {
        super(game);
    }
//    public Chest(Coordinates coordinates, String path, int width, int height) {
//        super(coordinates, path, width, height);
//    }
}
