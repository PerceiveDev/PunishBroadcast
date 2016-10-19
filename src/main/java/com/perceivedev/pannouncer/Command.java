package com.perceivedev.pannouncer;

import org.bukkit.plugin.java.JavaPlugin;

public class Command {
	
	private String label;
	private String usePerm;
	private String bypassPerm;
	private String message;
	private boolean hasReason;
	
	public Command(String configSection) {
		JavaPlugin plugin = PunishmentAnnouncer.getPlugin();
		this.label = plugin.getConfig().getString(configSection + ".command");
		this.usePerm = plugin.getConfig().getString(configSection + ".permission.use");
		this.bypassPerm = plugin.getConfig().getString(configSection + ".permission.bypass");
		this.message = plugin.getConfig().getString(configSection + ".message");
		this.hasReason = plugin.getConfig().getBoolean(configSection + ".has-reason");
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUsePerm() {
		return usePerm;
	}
	public void setUsePerm(String usePerm) {
		this.usePerm = usePerm;
	}
	public String getBypassPerm() {
		return bypassPerm;
	}
	public void setBypassPerm(String bypassPerm) {
		this.bypassPerm = bypassPerm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean hasReason() {
		return hasReason;
	}

	public void setReason(boolean reason) {
		this.hasReason = reason;
	}

}
