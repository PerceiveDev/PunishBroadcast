package com.perceivedev.pannouncer.lstnr;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceivedev.pannouncer.Command;
import com.perceivedev.pannouncer.PunishmentAnnouncer;

@SuppressWarnings("deprecation")
public class CommandListener implements Listener {
	
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		final String[] split = event.getMessage().substring(1).split(" ");
		
		for (final Command command : PunishmentAnnouncer.getPlugin().getCommands()) {
			
			if (command.hasReason()) {
				if (split.length != 3) {
					return;
				}
			} else {
				if (split.length != 2) {
					return;
				}
			}
			
			if (!(command.getLabel().equalsIgnoreCase(split[0]))) {
				return;
			}
			
			if (!(event.getPlayer().hasPermission(command.getUsePerm()))) {
				return;
			}
			
			if (Bukkit.getPlayer(split[1]) == null) {
				return;
			}
			
			if (!(Bukkit.getPlayer(split[1]).hasPermission(command.getBypassPerm()))) {
				return;
			}
			
			final Player sender = event.getPlayer();
			
			new BukkitRunnable() {
				@Override
				public void run() {
					
					String reason = "";
					
					if (command.hasReason()) {
						StringBuilder sb = new StringBuilder();
						for (int i = 2; i < split.length; i++) {
							sb.append(split[i]);
						}
						reason = sb.toString();
					}
					
					String message = command.getMessage()
							.replace("%player%", split[1])
							.replace("%sender%", sender.getName())
							.replace("%reason%", reason);
					
					sender.getServer().broadcastMessage(message);
					
				}
			}.runTaskLater(PunishmentAnnouncer.getPlugin(), 1);

		}
	}

}
