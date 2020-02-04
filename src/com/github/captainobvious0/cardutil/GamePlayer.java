package com.github.captainobvious0.cardutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.github.captainobvious0.games.BlackJack;
import com.github.captainobvious0.games.Game;
import com.github.captainobvious0.games.Poker;


public class GamePlayer {
    
    Player player;
    Deck deck;
    ArrayList<Integer> pDeck;
    String game;
    public static Map<Player, String> playersInGame = new HashMap<Player, String>(); // TODO - remove this, just use playerList
    static Map<Player, GamePlayer> playerList = new HashMap<Player, GamePlayer>();
    static Map<GamePlayer, Game> playerGame = new HashMap<GamePlayer, Game>();
    
    public GamePlayer(Player player, String game, Deck deck) {
    	
        this.player = player;
        this.game = game;
        playersInGame.put(player, game);
        
        this.deck = deck;
        this.pDeck = deck.createPlayerHand();
        
        // start game
        if (game.equalsIgnoreCase("blackjack")) {
        	playerGame.put(this, new BlackJack(this));
        } else if (game.equalsIgnoreCase("poker")) {
        	playerGame.put(this, new Poker(this)); 
        }
        
        playerList.put(player, this);
    }
    
    public ArrayList<Integer> getPlayerDeck() {
        return this.pDeck;
    }
    
    public void setPlayerDeck(ArrayList<Integer> deck) {
        this.pDeck = deck;
    }
    
    public void removePlayer() {
    	playersInGame.remove(player);
    	playerList.remove(player);
    	playerGame.remove(player);
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
	
	public static Game getGame(GamePlayer player) {
		return playerGame.get(player);
		
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
	
	public void playerWin() {
		removePlayer();
		message("&9Casino> &aYou won!");
		// Rewards...
	}
	
	public void playerLose() {
		removePlayer();
		message("&9Casino> &cYou lost!");
	}
    
}