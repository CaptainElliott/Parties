package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyWarpEvent extends PartyEvent {

    private Set<UUID> players;
    private Player target;

    /**
     * Event that is called when an owner warps all players to their current location (no bungee implementation the core)
     *
     * @param party Party that has called this event
     * @param target player that all other members will be teleported to
     * @param players Set of all players that are being teleported
     */
    public PartyWarpEvent(Party party, Player target, Set<UUID> players) {
        super(party);
        this.players = players;
        this.target = target;
    }

    public Player getTarget() {
        return this.target;
    }

    public Set<UUID> getPlayers() {
        return this.players;
    }
}
