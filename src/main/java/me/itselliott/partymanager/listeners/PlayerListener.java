package me.itselliott.partymanager.listeners;

import me.itselliott.partymanager.PartyManager;
import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.events.PartyDisbandEvent;
import me.itselliott.partymanager.events.PartyLeaveEvent;
import me.itselliott.partymanager.party.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerDisconect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
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

}
