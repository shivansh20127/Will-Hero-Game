package com.example.willherogame;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable
{
    private final ArrayList<Game> savedGames;
    
    public Database() {
        savedGames = new ArrayList<>();
    }
    
    public void addGame(Game game) {
        savedGames.add(game);
    }
    
    public ArrayList<Game> getSavedGames() { return savedGames; }
    
    public Game getGameByID(int id) {
        for (Game game : savedGames) {
            if (game.getGameID() == id) return game;
        }
        return null;
    }
}
