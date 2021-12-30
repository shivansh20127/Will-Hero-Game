package com.example.willherogame;

public class StrongOrc extends Orc
{
    public StrongOrc(double x, double y) {
        super(x, y);
        this.path = "/Assets/Images/Orc5.png";
        this.width = 84 * orcScale;
        this.height = 65 * orcScale;
        MAX_Y = 14;
    }
    
}
