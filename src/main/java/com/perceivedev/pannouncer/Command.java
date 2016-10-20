package com.perceivedev.pannouncer;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Command {

    private String  label;
    private String  usePerm;
    private String  bypassPerm;
    private String  message;
    private boolean hasReason;

    public Command(ConfigurationSection section) {
        // @formatter:off
        this.label      = section.getString  ("command"          );
        this.usePerm    = section.getString  ("permission.use"   );
        this.bypassPerm = section.getString  ("permission.bypass");
        this.message    = section.getString  ("message"          );
        this.hasReason  = section.getBoolean ("has-reason"       );
        // @formatter:on
        // Uncomment for debugging purposes
        // System.out.println(toString());
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

    /**
     * This will return true if no permission is provided ({@code null} or
     * {@code usePerm.length() < 1})
     * 
     * @param player
     * @return If the given player can use the punishment command
     */
    public boolean canUse(Player player) {
        return player != null && (usePerm != null || usePerm.trim().length() < 1 || player.hasPermission(usePerm));
    }

    /**
     * This will return true if no permission is provided ({@code null} or
     * {@code bypassPerm.length() < 1})
     * 
     * @param player
     * @return If the given player can bypass the punishment
     */
    public boolean canBypass(Player player) {
        return player != null && (bypassPerm == null || bypassPerm.trim().length() < 1 || player.hasPermission(bypassPerm));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Command [label=" + label + ", usePerm=" + usePerm + ", bypassPerm=" + bypassPerm + ", message=" + message + ", hasReason=" + hasReason + "]";
    }

}
