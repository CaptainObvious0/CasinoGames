package com.github.captainobvious0;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.captainobvious0.cardutil.Deck;
import com.github.captainobvious0.cardutil.GamePlayer;

public class CasinoCommand implements CommandExecutor {
	
	// TODO: config for language

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(addColor("&9Casino> &7Command can only be executed as a player"));
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("casino.play")) {
			player.sendMessage(addColor("&9Casino> &7You do not have permission for this command"));
			return true;
		}
		
		if (args.length == 0) {
			player.sendMessage(addColor("&9Casino> &7Welcome to the Minecraft Casino! You can play the following game modes:"));
			ArrayList<String> games = new ArrayList<String>();
			if (player.hasPermission("casino.game.blackjack")) {
				games.add("BlackJack");
			}
			if (player.hasPermission("casino.game.poker")) {
				games.add("Poker");
			}
			if (games.isEmpty()) {
				games.add("None");
			}
			
			player.sendMessage(addColor("&9Casino> &6" + games));
			player.sendMessage(addColor("&9Casino> &7To get started, type &a/casino play &6[game]"));
			return true;
			
		}
		
		if (args[0].equalsIgnoreCase("play")) {
			if (args.length == 1) {
				player.sendMessage(addColor("&9Casino> &7Please select a game to play. To see the games available, type &a/casino"));
				return true;
			}
			
			if (args[1].equalsIgnoreCase("blackjack")) {
				Deck deck = new Deck();
				new GamePlayer(player, "BlackJack", deck);
			} else if (args[1].equalsIgnoreCase("poker")) {
				new GamePlayer(player, "Poker", new Deck());
			}
			
		}
		
		else if (args[0].equalsIgnoreCase("stop")) {
			
		} else {
			player.sendMessage(addColor("&9Casino> &7Unknown args"));
		}
		
		return true;
	}
	
	public String addColor(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
