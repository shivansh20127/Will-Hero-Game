package com.example.willherogame;

import java.io.Serializable;

public class WeakOrc extends Orc implements Serializable
{
    public WeakOrc(double x, double y, Game game) {
        super(x, y, game);
        this.path = "/Assets/Images/Orc2.png";
        this.width = 84 * orcScale;
        this.height = 65 * orcScale;
        MAX_Y = 14;
    }
}
