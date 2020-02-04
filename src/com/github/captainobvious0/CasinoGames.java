package com.github.captainobvious0;

import org.bukkit.plugin.java.JavaPlugin;

public class CasinoGames extends JavaPlugin {
	
	private static CasinoGames plugin;
	
	public void onEnable() {
		plugin = this;
	} 
	
	public void onDisable() {
		
	}
	
	public static CasinoGames getPlugin() {
		return plugin;
	}

}
