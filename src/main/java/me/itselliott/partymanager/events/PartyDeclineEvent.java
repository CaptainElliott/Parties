package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyDeclineEvent extends PartyEvent{

    private Player player;

    /**
     * Event called when a player declines a party invitation
     *
     * @param party party that player has accepted
     * @param player that has declines party request
     */
    public PartyDeclineEvent(Party party, Player player) {
        super(party);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
