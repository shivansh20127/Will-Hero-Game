package com.example.willherogame;

import java.io.Serializable;

public class StrongOrc extends Orc implements Serializable
{
    public StrongOrc(double x, double y, Game game) {
        super(x, y, game);
        this.path = "/Assets/Images/Orc5.png";
        this.width = 84 * orcScale;
        this.height = 65 * orcScale;
        MAX_Y = 14;
    }
    
}
