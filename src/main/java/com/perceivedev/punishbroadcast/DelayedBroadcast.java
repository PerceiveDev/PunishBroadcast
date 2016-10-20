/**
 * 
 */
package com.perceivedev.punishbroadcast;

import org.bukkit.Bukkit;

/**
 * @author Rayzr
 *
 */
public class DelayedBroadcast extends DelayedMessage {

    private String permission;

    /**
     * @param message the message to send
     */
    public DelayedBroadcast(String message) {
        this(message, null);
    }

    /**
     * 
     * @param message the message to send
     * @param permission the permission to check when broadcasting. If this is
     *            {@code null} or empty it will be ignored.
     */
    public DelayedBroadcast(String message, String permission) {
        super(message);
        this.permission = permission;
    }

    @Override
    protected void send(String message) {
        if (permission == null || permission.trim().length() < 1) {
            Bukkit.broadcastMessage(message);
        } else {
            Bukkit.broadcast(message, permission);
        }
    }

}
