package com.example.willherogame;

import java.io.Serializable;

public class BossOrc extends Orc implements Serializable
{
    public BossOrc(double x, double y, Game game) {
        super(x, y, game);
    }
    
    @Override
    public void moveObjectBack() {
    
    }
}
