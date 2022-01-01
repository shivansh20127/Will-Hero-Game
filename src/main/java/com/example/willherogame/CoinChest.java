package com.example.willherogame;

public class CoinChest extends Chest
{
    public CoinChest(double x, double y, Game game) {
        super(x, y, game);
    }
    
    @Override
    public void heroEffect(Hero hero) {
        if (!used) {
            hero.setCollectedCoins(hero.getCollectedCoins() + 10);
            setImg("/Assets/Images/openchest.png");
            used = true;
        }
        
    }
}