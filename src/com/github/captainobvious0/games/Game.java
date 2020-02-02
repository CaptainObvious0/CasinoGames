package com.github.captainobvious0.games;

import com.github.captainobvious0.cardutil.GamePlayer;

public class Game {
	
	GamePlayer player;
	String game;
	
	public Game(String game, GamePlayer player) {
		this.game = game;
		this.player = player;
		startGame();
	}
	
	public void startGame() {
		
	}

}
