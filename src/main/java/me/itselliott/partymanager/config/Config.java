package me.itselliott.partymanager.config;

import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.party.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class Config {

    private PartyPlugin plugin;
    private Configuration config;

    public Config(PartyPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.plugin.getConfig().options().copyDefaults(true);
        this.plugin.saveConfig();

        validatePermissions();
    }

    public List<String> getPermissions(String membership) {
        return this.plugin.getConfig().getStringList("permissions" + membership);
    }

    private boolean validatePermissions() {
        Set<String> validPermissions = Permissions.getPermissions();
        for (String permissionSet : this.config.getConfigurationSection("permissions").getKeys(true)) {
            for (String permission : this.config.getStringList("permissions." + permissionSet)) {
                if (!validPermissions.contains(permission)) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid permission node: " + permission);
                }
            }
        }
        return true;

    }

}
