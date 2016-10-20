/**
 * 
 */
package com.perceivedev.punishbroadcast;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Rayzr
 *
 */
public abstract class DelayedMessage extends BukkitRunnable {

    private String message;

    public DelayedMessage(String message) {
        this.message = message;
    }

    @Override
    public final void run() {
        send(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void send(JavaPlugin plugin) {
        send(plugin, 1L);
    }

    public void send(JavaPlugin plugin, long delay) {
        runTaskLater(plugin, delay);
    }

    protected abstract void send(String message);

}
