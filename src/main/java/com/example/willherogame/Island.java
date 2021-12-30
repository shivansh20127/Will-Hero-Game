package com.example.willherogame;

import java.util.Random;

public class Island extends GameObject
{
    private final int islandID;
    private final static double islandScale = 0.58;
    private final static double offsetY = 100;
    private final static Random random = new Random();
    private static int islandCount = 0;
    
    public Island(int x, int y) {
        super();
        islandCount++;
        this.islandID = (islandCount % 3) + 1;
//        this.islandID = 2;
        this.path = "/Assets/Images/GameIsland" + islandID + ".png";
        this.setCoordinates(x, y);
        
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
