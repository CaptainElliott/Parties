package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class CreatePartyEvent extends PartyEvent {

    private Player player;

    /**
     * Event that is called when a player creates a new party
     *
     * @param party Party that has called this event
     */
    public CreatePartyEvent(Party party, Player player) {
        super(party);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
