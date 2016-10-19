package com.perceivedev.pannouncer;

import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceivedev.pannouncer.events.CommandListener;

public class PunishmentAnnouncer extends JavaPlugin {

    private static PunishmentAnnouncer instance;
    private HashMap<String, Command>   commands = new HashMap<String, Command>();

    public void onEnable() {
        // Allow static access to instance
        instance = this;
        // Save default config
        saveDefaultConfig();
        load();
        getCommand("punishmentannouncer").setExecutor(new CommandHandler(this));
        // @ZP4RKER: You forgot to register the event listener XD
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

    }

    public Command matchCommand(String label) {
        return commands.get(label);
    }

    public static PunishmentAnnouncer getInstance() {
        return instance;
    }

    /**
     * @return
     */
    public String versionText() {
        return getName() + " v" + getDescription().getVersion();
    }

}