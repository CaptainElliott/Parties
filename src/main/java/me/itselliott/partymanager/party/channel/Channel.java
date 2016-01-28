package me.itselliott.partymanager.party.channel;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public interface Channel {

    /**
     * Gets the format for the message sent
     *
     * @return format for message sent
     */
    public String getFormat();

    /**
     * Sets the format for the message to be sent
     *
     * @param format fot the message
     */
    public void setFormat(String format);

    /**
     * Sends a message from a player to all @see #getMembers of a party
     *
     * @param player player sending the message
     * @param message message to be sent
     */
    public void sendMessage(Player player, String message);


    /**
     * Sends a message to all players inside of a party
     *
     * @param message to be sent
     */
    public void sendBroadcast(String message);

    /**
     * All players that can see messages in this channel
     *
     * @return member players for this channel
     */
    public Set<UUID> getMembers();

    /**
     * Set's the members of the channel to a new list
     *
     * @param members new list of members
     */
    public void setMembers(Set<UUID> members);

    /**
     * Adds a new member to the channel
     *
     * @param member new player to add to member list
     */
    public void addMember(UUID member);

    /**
     * Adds a player to the membership of this channel
     *
     * @param player to add to this channel
     */
    public void addPlayer(UUID player);

    /**
     * Removes a player from the membership of this channel
     *
     * @param player player to remove
     */
    public void removePlayer(UUID player);

}
