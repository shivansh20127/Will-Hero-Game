package com.example.willherogame;

import java.io.Serializable;
import java.util.Random;

public class Island extends GameObject implements Serializable
{
    private final int islandID;
    private final static double islandScale = 0.58;
    private final static double offsetY = 100;
    private transient final static Random random = new Random();
    private static int islandCount = 0;
    
    public Island(double x, double y, Game game) {
        super(game);
        islandCount++;
        this.islandID = (islandCount % 3) + 1;
        this.path = "/Assets/Images/GameIsland" + islandID + ".png";
        this.setCoordinates(x, y + random.nextInt(100) - 50);
        
        if (islandID == 1) {
            height = 225 * islandScale;
            width = 328 * islandScale;
        }
        else if (islandID == 2) {
            height = 259 * islandScale;
            width = 352 * islandScale;
        }
        else {
            height = 184 * islandScale;
            width = 194 * islandScale;
        }
    }
    
    @Override
    public String toString() {
        return "Island " + islandID;
    }
    
}
