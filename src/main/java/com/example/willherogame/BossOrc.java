package com.example.willherogame;

import java.io.Serializable;

public class BossOrc extends Orc implements Serializable
{
    private int health;
    
    public BossOrc(double x, double y, Game game) {
        super(x, y, game);
        this.health = 5;
        this.path = "/Assets/Images/OrcBoss.png";
        this.width = 200;
        this.height = 175;
        MAX_Y = 20;
        this.setToMove(-10);
    }
    
//    @Override
//    public void moveObjectBack() {
//        getImg().setLayoutX(getImg().getLayoutX() - 2);
//        setXCoordinate(getCoordinates().getX() - 2);
//    }
    
    @Override
    protected void checkWeaponCollision() {
        for (Weapon w : game.getWeapons()) {
            if (w.getImg().getBoundsInParent().intersects(img.getBoundsInParent())) {
                if (health < 0) killOrc();
                else health--;
                break;
            }
        }
    }
}
