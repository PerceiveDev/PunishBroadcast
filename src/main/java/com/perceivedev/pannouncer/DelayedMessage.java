/**
 * 
 */
package com.perceivedev.pannouncer;

import org.bukkit.ChatColor;
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

    public abstract void send(String message);

}
