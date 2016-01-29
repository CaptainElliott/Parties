package me.itselliott.partymanager.events.partyChat;

import me.itselliott.partymanager.events.PartyEvent;
import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyChatEvent extends PartyEvent {

    private Channel channel;
    private Player sender;
    private String message;

    /**
     * Base event for every event to do with chatting in the party channel
     *
     * @param party   Party that has called this event
     * @param channel channel that the messages are being sent to
     * @param player player that sent the message
     * @param message message that has been sent
     */
    public PartyChatEvent(Party party, Channel channel, Player sender, String message) {
        super(party);
        this.channel = channel;
        this.sender = sender;
        this.message = message;
    }

    public Player getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Channel getChannel() {
        return this.channel;
    }
}
