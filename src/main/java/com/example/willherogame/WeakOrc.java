package com.example.willherogame;

public class WeakOrc extends Orc
{
    public WeakOrc(double x, double y) {
        super(x, y);
        this.path = "/Assets/Images/Orc2.png";
        this.width = 84 * orcScale;
        this.height = 65 * orcScale;
        MAX_Y = 14;
    }
}
