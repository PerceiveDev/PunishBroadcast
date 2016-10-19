package com.perceivedev.pannouncer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class PunishmentAnnouncer extends JavaPlugin {
	
	private static PunishmentAnnouncer plugin;
	
	public void onEnable() {
		// Save default config
		saveDefaultConfig();
		// Allow static access to instance
		plugin = this;
	}
	
	public List<Command> getCommands() {
		List<Command> commands = new ArrayList<Command>();
		for (String command : getConfig().getKeys(false)) {
			commands.add(new Command(command));
		}
		return commands;
	}
	
	public static PunishmentAnnouncer getPlugin() {
		return plugin;
	}

}