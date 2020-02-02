package com.github.captainobvious0.cardutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.github.captainobvious0.games.BlackJack;
import com.github.captainobvious0.games.Poker;

public class GamePlayer {
    
    Player player;
    Deck deck;
    ArrayList<Integer> pDeck;
    String game;
    static Map<Player, String> playersInGame = new HashMap<Player, String>();
    static Map<Player, GamePlayer> playerList = new HashMap<Player, GamePlayer>();
    
    public GamePlayer(Player player, String game, Deck deck) {
    	
        this.player = player;
        this.game = game;
        playersInGame.put(player, game);
        
        this.deck = deck;
        this.pDeck = deck.currentDeck;
        
        // start game
        if (game.equalsIgnoreCase("blackjack")) {
        	new BlackJack(this);
        } else if (game.equalsIgnoreCase("poker")) {
        	new Poker(this); 
        }
        
        playerList.put(player, this);
    }
    
    public ArrayList<Integer> getPlayerDeck() {
        return this.pDeck;
    }
    
    public void setPlayerDeck(ArrayList<Integer> deck) {
        this.pDeck = deck;
    }
    
    public void addPlayerDeck(int card) {
        pDeck.add(card);
    }
    
    public void resetPlayerDeck() {
        pDeck.clear();
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public Deck getDeck() {
    	return this.deck;
    }
    
	public static boolean removePlayer(Player player) {
		
		if (playersInGame.containsKey(player)) {
			playersInGame.remove(player);
			playerList.remove(player);
			return true;
		}
		
		return false;
	}
	
	public static boolean checkIfPlaying(Player player) {
		if (playersInGame.containsKey(player)) return true;
		return false;
	}
	
	public static String getGameType(Player player) {
		if (checkIfPlaying(player)) return playersInGame.get(player);
		return "None";
	}
	
	public static GamePlayer getGamePlayer(Player player) {
		return playerList.get(player);
	}
	
	public void message(String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
    
}