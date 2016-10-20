package com.perceivedev.punishbroadcast.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.perceivedev.punishbroadcast.Command;
import com.perceivedev.punishbroadcast.DelayedBroadcast;
import com.perceivedev.punishbroadcast.PunishBroadcast;

public class CommandListener implements Listener {

    private PunishBroadcast plugin;

    /**
     * @param plugin
     */
    public CommandListener(PunishBroadcast plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {

        if (!plugin.isBroadcastEnabled()) {
            return;
        }

        if (event.isCancelled()) {
            return;
        }

        String[] split = event.getMessage().substring(1).split(" ");
        if (split == null || split.length < 1) {
            // Something is wrong!
            return;
        }

        Command command = plugin.matchCommand(split[0].toLowerCase());
        if (command == null) {
            // No command was found
            return;
        }

        if (split.length < 2) {
            return;
        }

        Player player = event.getPlayer();

        if (!command.canUse(player)) {
            return;
        }

        Player target = Bukkit.getPlayer(split[1]);

        if (target != null && command.canBypass(target)) {
            return;
        }

        String reason = "";

        if (command.hasReason() && split.length > 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(split[2]);
            for (int i = 3; i < split.length; i++) {
                sb.append(" ").append(split[i]);
            }
            reason = sb.toString();
        }

        // @formatter:off
        String message = command.getMessage()
                .replace("%player%", split[1])
                .replace("%sender%", player.getName())
                .replace("%reason%", reason);
        // @formatter:on

        new DelayedBroadcast(message, "pb.read").send(plugin);

    }

}
