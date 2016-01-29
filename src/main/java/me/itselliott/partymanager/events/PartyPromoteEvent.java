package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.membership.Membership;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyPromoteEvent extends PartyEvent {

    private Player player;

    /**
     * Event called when a player is promted inside a party
     *
     * @param party Party that has called this event
     */
    public PartyPromoteEvent(Party party, Player player) {
        super(party);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
