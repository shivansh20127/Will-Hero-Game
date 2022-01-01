package com.example.willherogame;

public class WeaponChest extends Chest
{
    private static int weaponSelect = 0;
    
    public WeaponChest(double x, double y, Game game) {
        super(x, y, game);
    }
    
    @Override
    public void heroEffect(Hero hero) {
        if (!used) {
            hero.setCurrentWeapon(weaponSelect);
            setImg("/Assets/Images/openchest.png");
            weaponSelect = 1 - weaponSelect;
            used = true;
        }
    }
}
