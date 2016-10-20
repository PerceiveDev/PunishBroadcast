/**
 * 
 */
package com.perceivedev.pannouncer;

import org.bukkit.Bukkit;

/**
 * @author Rayzr
 *
 */
public class DelayedBroadcast extends DelayedMessage {

    /**
     * @param message
     */
    public DelayedBroadcast(String message) {
        super(message);
    }

    @Override
    public void send(String message) {
        Bukkit.broadcastMessage(message);
    }

}
