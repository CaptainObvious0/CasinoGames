package com.github.captainobvious0;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.captainobvious0.cardutil.GamePlayer;
import com.github.captainobvious0.games.BlackJack;
import com.github.captainobvious0.games.Game;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		if (GamePlayer.checkIfPlaying(player)) {
			String gameType = GamePlayer.getGameType(player);
			GamePlayer gPlayer = GamePlayer.getGamePlayer(player);
			String msg = event.getMessage();
			Game pGame = GamePlayer.getGame(gPlayer);
			event.setCancelled(true);
			
			if (gameType.equalsIgnoreCase("blackjack")) {
				
				BlackJack bJack = (BlackJack) pGame;
				
				if (msg.equalsIgnoreCase("hit")) {
					bJack.hit();
				} else if (msg.equalsIgnoreCase("stand")) {
					bJack.stand();
				} else if (msg.equalsIgnoreCase("stop")) {
					gPlayer.removePlayer();
					player.sendMessage(addColor("&9Casino> &7You have left your game of &6BlackJack&7. Thanks for playing!"));
				} else {
					player.sendMessage(addColor("&9Casino> &7Please chat a valid game command or type &6stop &7to exit the game"));
				}
				
			} else if (gameType.equalsIgnoreCase("poker")) {
				
			}
			
		} else {
			player.sendMessage(GamePlayer.playersInGame + "");
		}
	}
	
	public String addColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
