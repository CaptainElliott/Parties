package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyKickEvent extends PartyEvent {

    private Player player;

    /**
     * Event called when someone is kicked from a party
     *
     * @param party Party that has called this event
     */
    public PartyKickEvent(Party party, Player player) {
        super(party);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
