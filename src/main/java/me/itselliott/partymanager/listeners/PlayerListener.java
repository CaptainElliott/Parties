package me.itselliott.partymanager.listeners;

import me.itselliott.partymanager.PartyManager;
import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.events.PartyDisbandEvent;
import me.itselliott.partymanager.events.PartyLeaveEvent;
import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PlayerListener implements Listener {

    HashMap<UUID, PermissionAttachment> permissionAttachment = new HashMap<>();

    @EventHandler
    public void onPlayerDisconect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.permissionAttachment.remove(event.getPlayer().getUniqueId());
        PartyManager partyManager = PartyPlugin.get().getPartyManager();
        if (partyManager.getParties().hasParty(player)) {
            Party party = partyManager.getParties().getParty(player);
            if (party.hasOwner(player) && party.getOwners().size() <= 1) {
                Bukkit.getPluginManager().callEvent(new PartyDisbandEvent(party));
            } else if (party.hasPlayer(player)) {
                Bukkit.getPluginManager().callEvent(new PartyLeaveEvent(party, player));
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.permissionAttachment.put(event.getPlayer().getUniqueId(), event.getPlayer().addAttachment(PartyPlugin.get()));
        for (String permssion : PartyPlugin.get().getConfiguration().getPermissions("default")) {
            this.permissionAttachment.get(event.getPlayer().getUniqueId()).setPermission(permssion, true);
        }
    }

}
