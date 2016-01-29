package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyAcceptEvent extends PartyEvent {

    private Player player;

    /**
     * Event called when a player accepts a party invitation
     *
     * @param party party that player has accepted
     * @param player that has accepted party request
     */
    public PartyAcceptEvent(Party party, Player player) {
        super(party);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
