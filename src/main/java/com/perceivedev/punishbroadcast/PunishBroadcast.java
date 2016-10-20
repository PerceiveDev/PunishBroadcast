package com.perceivedev.punishbroadcast;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.punishbroadcast.events.CommandListener;

public class PunishBroadcast extends JavaPlugin {

    private static PunishBroadcast   instance;
    private HashMap<String, Command> commands = new HashMap<String, Command>();
    private boolean                  enabled  = true;

    public void onEnable() {
        // Allow static access to instance
        instance = this;
        // Save default config
        saveDefaultConfig();
        load();
        getCommand("punishbroadcast").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    public void load() {

        reloadConfig();
        commands.clear();

        ConfigurationSection section = getConfig().getConfigurationSection("punishments");
        if (section == null || section.getKeys(false).size() < 1) {
            getLogger().warning("The punishments section of the config was not found or was empty!");
            getConfig().createSection("punishments");
            saveConfig();
        } else {
            for (String command : section.getKeys(false)) {
                Command cmd = new Command(section.getConfigurationSection(command));
                commands.put(cmd.getLabel(), cmd);
            }
        }

        enabled = getConfig().getBoolean("enabled");

    }

    public Command matchCommand(String label) {
        return commands.get(label);
    }

    public static PunishBroadcast getInstance() {
        return instance;
    }

    /**
     * @return
     */
    public String versionText() {
        return getName() + " v" + getDescription().getVersion();
    }

    /**
     * @return
     */
    public boolean toggle() {
        enabled = !enabled;
        getConfig().set("enabled", enabled);
        saveConfig();
        return enabled;
    }

    /**
     * @return If the plugin has punish-broadcasting enabled
     */
    public boolean isBroadcastEnabled() {
        return enabled;
    }

}