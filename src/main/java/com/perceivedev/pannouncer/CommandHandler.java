/**
 * 
 */
package com.perceivedev.pannouncer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Rayzr
 *
 */
public class CommandHandler implements CommandExecutor {

    private PunishmentAnnouncer plugin;

    /*
     * (non-Javadoc)
     * 
     * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.
     * CommandSender, org.bukkit.command.Command, java.lang.String,
     * java.lang.String[])
     */
    /**
     * @param punishmentAnnouncer
     */
    public CommandHandler(PunishmentAnnouncer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("PunishmentAnnouncer.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.GRAY + "This server is running " + ChatColor.GREEN + plugin.versionText());
            return true;
        }

        String arg = args[0].toLowerCase();

        if (arg.equals("reload")) {
            plugin.load();
            sender.sendMessage(ChatColor.GRAY + "The config has been " + ChatColor.GREEN + "reloaded");
        } else {
            sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.GREEN + "/punishmentannouncer [reload]");
        }

        return true;
    }

}
