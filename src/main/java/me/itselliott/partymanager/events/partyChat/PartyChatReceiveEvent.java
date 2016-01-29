package me.itselliott.partymanager.events.partyChat;

import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyChatReceiveEvent extends PartyChatEvent {

    private Player recipiant;

    /**
     * Event called when a player receives a message from the party chat
     *
     * @param party   Party that has called this event
     * @param channel channel that the messages are being sent to
     * @param sender player that sent the message
     * @param message message that has been sent
     */
    public PartyChatReceiveEvent(Party party, Channel channel, Player sender, String message, Player recipiant) {
        super(party, channel, sender, message);
        this.recipiant = recipiant;
    }

    public Player getRecipiant() {
        return this.recipiant;
    }
}
