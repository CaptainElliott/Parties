package me.itselliott.partymanager.config;

import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.party.Permissions;

import java.util.List;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class Config {

    private PartyPlugin plugin;

    public Config(PartyPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getConfig().options().copyDefaults(true);
        this.plugin.saveConfig();

        validatePermissions();
    }

    public List<String> getPermissions(String membership) {
        return this.plugin.getConfig().getStringList("permissions" + membership);
    }

    private boolean validatePermissions() {
        //TODO: Validate permissions strings in config with valid permission values from the enumeration Permissions.java
        return true;
    }

}
