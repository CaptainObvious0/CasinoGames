package com.github.captainobvious0.cardutil;

import java.util.ArrayList;

public class Dealer {
	
	GamePlayer player;
	Deck deck;
	public ArrayList<Integer> pDeck;
	
	public Dealer(GamePlayer player, Deck deck) {
		this.player = player;
		this.deck = deck;
		this.pDeck = deck.createPlayerHand();
	}
	
	public GamePlayer getGPlayer() {
		return player;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void addDealerDeck(int card) {
        pDeck.add(card);
    }

}
