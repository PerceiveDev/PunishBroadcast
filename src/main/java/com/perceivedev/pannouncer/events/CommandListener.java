package com.perceivedev.pannouncer.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.perceivedev.pannouncer.Command;
import com.perceivedev.pannouncer.DelayedBroadcast;
import com.perceivedev.pannouncer.PunishmentAnnouncer;

public class CommandListener implements Listener {

    private PunishmentAnnouncer plugin;

    /**
     * @param plugin
     */
    public CommandListener(PunishmentAnnouncer plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {

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

        // -------------------------------------------------------------------
        // TODO: Most of the time commands that have a `reason` parameter do
        // not actually REQUIRE the reason. Maybe a `hasReason` and
        // `requiresReason` value?
        // -------------------------------------------------------------------
        // if (command.hasReason()) {
        // if (split.length < 3) {
        // return;
        // }
        // } else {
        if (split.length < 2) {
            return;
        }
        // }

        Player player = event.getPlayer();

        if (!command.canUse(player)) {
            return;
        }

        Player target = Bukkit.getPlayer(split[1]);

        // @ZP4RKER: Look at Command#canBypass. I have a null check inside
        // it (to avoid NPEs if it's passed a null player). We could omit
        // `target != null` but this shows more clearly that we're checking
        // `target`, since people probably won't look at Command#canBypass.
        if (target != null && command.canBypass(target)) {
            // Also worth mentioning that Command#canBypass checks if the
            // permission provided is null/an empty string so you could have
            // no-permission commands...
            // That's up to them ;P
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

        new DelayedBroadcast(message).send(plugin);

    }

}
