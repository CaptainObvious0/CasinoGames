package com.github.captainobvious0.games;

import java.util.ArrayList;

import com.github.captainobvious0.cardutil.Deck;
import com.github.captainobvious0.cardutil.GamePlayer;

public class BlackJack extends Game {
	
	GamePlayer player;
	GamePlayer dealer;
	
	public BlackJack(GamePlayer player) {
		super("BlackJack", player);
		this.player = player;
	}
	
	public void startGame() {
		ArrayList<Integer> startHand = player.getPlayerDeck();
		player.message("&9Casino> &7You have started a game of BlackJack!");
		player.message(" ");
		player.message("&9Casino> &7You have dealt the &6" + Deck.cardToString(startHand.get(0)) + " &7and the &6" + Deck.cardToString(startHand.get(1)));
	}
	
	

}
