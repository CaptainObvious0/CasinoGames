package com.github.captainobvious0.games;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.github.captainobvious0.CasinoGames;
import com.github.captainobvious0.cardutil.Dealer;
import com.github.captainobvious0.cardutil.Deck;
import com.github.captainobvious0.cardutil.GamePlayer;

public class BlackJack extends Game {
	
	GamePlayer player;
	Dealer dealer;
	
	int finalTotal = 0;
	
	public BlackJack(GamePlayer player) {
		super("BlackJack", player);
		this.player = player;
		this.dealer = new Dealer(player, player.getDeck());
	}
	
	public void startGame() {
		ArrayList<Integer> startHand = player.getPlayerDeck();
		player.message("&9Casino> &7You have started a game of BlackJack!");
		player.message(" ");
		player.message("&9Casino> &7You have dealt the &6" + Deck.cardToString(startHand.get(0)) + " &7and the &6" + Deck.cardToString(startHand.get(1)) + " &7for a total of " + Deck.getHandTotal(player.getPlayerDeck()));
		player.message("&9Casino> &7Dealers revealed card: " + Deck.cardToString(dealer.pDeck.get(0)));
		player.message(" ");
		shouldContinue();
	}
	
	public void hit() {
		int drawCard = player.getDeck().drawAnotherCard();
		player.addPlayerDeck(drawCard);
		player.message("&9Casinio> &7You drew the &6" + Deck.cardToString(drawCard) + " for a new total of &6" + Deck.getHandTotal(player.getPlayerDeck()));
		player.message(" ");
		shouldContinue();
	}
	
	public void stand() {
		finalTotal = Deck.getHandTotal((player.getPlayerDeck()));
		player.message("&9Casino> &7You finished with a final total of &6" + finalTotal);
		player.message(" ");
		dealersTurn();
	}
	
	public void dealersTurn() {
		int total = Deck.getHandTotal(dealer.pDeck);
		player.message("&9Casino> &7Dealers turn! Dealers second card: &6" + Deck.cardToString(dealer.pDeck.get(1)) + " &7for a total of " + total);
		player.message(" ");
		if (total < finalTotal) {
			dealerPick();
		} else if (total <= 21) {
			
		}
	}
	
	public void dealerPick() {
		int drawCard = dealer.getDeck().drawAnotherCard();
		dealer.addDealerDeck(drawCard);
		int total = Deck.getHandTotal(dealer.pDeck);
		player.message("&9Casino> &7Dealer picks... They drew the &6" + Deck.cardToString(drawCard) + " &7for a new total of &6" + total);
		player.message(" ");
		
		if (total < finalTotal) {
			Bukkit.getScheduler().runTaskLater(CasinoGames.getPlugin(), () -> 
			new Runnable() {

				@Override
				public void run() {
					dealerPick();
				}
				
			}, 50);
		} else if (total <= 21) {
			if (total == 21) {
				player.message("&9Casino> &7Dealer &6BlackJack!");
			} else {
				player.message("&9Casino> &7Dealer win!");
			}
			player.playerLose();
			// dealer win
		} else {
			player.message("&9Casino> &7Dealer bust!");
			player.playerWin();
			// player win, dealer bust
		}
	}
	
	public void shouldContinue() {
		
		int handValue = Deck.getHandTotal(player.getPlayerDeck());
		if (handValue == 21) {
			player.message("&9Casino> &6BlackJack&7!");
			player.playerWin();
			return;
		} else if (handValue > 21) {
			player.message("&9Casino> &cBust!");
			player.playerLose();
			return;
		}
		
		player.message("&9Casino> &7Enter &6Hit &7to draw another card or &6Stand &7to stop your turn");
	}
	
	

}
