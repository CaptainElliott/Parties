package me.itselliott.partymanager.events;


import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyInviteEvent extends PartyEvent {

    private Player player;
    private Player target;

    /**
     * Event called when a player invites another player to join their party
     *
     * @param party party that player has accepted
     * @param player player that is inviting
     * @param target player that the invitation has been sent to
     */
    public PartyInviteEvent(Party party, Player player, Player target) {
        super(party);
        this.player = player;
        this.target = target;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getRecipiant() {
        return this.target;
    }
}
