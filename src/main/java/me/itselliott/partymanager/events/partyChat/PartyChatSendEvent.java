package me.itselliott.partymanager.events.partyChat;

import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyChatSendEvent extends PartyChatEvent {

    /**
     * Event called when a message is sent to a  party channel
     *
     * @param party   Party that has called this event
     * @param channel channel that the messages are being sent to
     * @param player player that sent the message
     * @param message message that has been sent
     */
    public PartyChatSendEvent(Party party, Channel channel, Player player, String message) {
        super(party, channel, player, message);
    }
}
