package me.itselliott.partymanager.party.membership;

import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public interface Membership {

    /**
     * Gets the player
     *
     * @return the player
     */
    public Player getPlayer();

    /**
     * Gets all permissions for member
     * @return permissions for member
     */
    public List<String> getPermissions();

    /**
     * Add a permission string to the permissions list
     * @param string permission to add
     */
    public void addPermission(String string);

    /**
     * Remove a permission from the permission list
     * @param string permission to remove
     */
    public void removePermission(String string);


}
