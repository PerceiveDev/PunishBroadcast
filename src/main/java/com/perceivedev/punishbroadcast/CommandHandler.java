/**
 * 
 */
package com.perceivedev.punishbroadcast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Rayzr
 *
 */
public class CommandHandler implements CommandExecutor {

    private PunishBroadcast plugin;

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
    public CommandHandler(PunishBroadcast plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(ChatColor.GRAY + "This server is running " + ChatColor.GREEN + plugin.versionText());
            return true;
        }

        String subCmd = args[0].toLowerCase();

        if (subCmd.equals("reload")) {
            if (!sender.hasPermission("pb.reload")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
            plugin.load();
            sender.sendMessage(ChatColor.GRAY + "The config has been " + ChatColor.GREEN + "reloaded");
        } else if (subCmd.equals("toggle")) {
            if (!sender.hasPermission("pb.toggle")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            }
            boolean enabled = plugin.toggle();
            sender.sendMessage(ChatColor.GRAY + "PunishBroadcast is now " + ChatColor.GREEN + (enabled ? "enabled" : "disabled"));
        } else {
            sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.GREEN + "/punishbroadcast [toggle|reload]");
        }

        return true;
    }

}
