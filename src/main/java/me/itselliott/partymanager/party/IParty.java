package me.itselliott.partymanager.party;

import me.itselliott.partymanager.party.channel.Channel;
import me.itselliott.partymanager.party.membership.Member;
import me.itselliott.partymanager.party.membership.Membership;
import me.itselliott.partymanager.party.membership.Owner;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Elliott2 on 27/01/2016.
 */
public interface IParty {

    /**
     * Gets all players inside the party and their "position"
     *
     * @return all players inside party
     */
    public List<Membership> getPlayers();

    /**
     * Gets all the owners of a party
     *
     * @return all owners of the party
     */
    public Set<Owner> getOwners();

    /**
     * Adds a player to the list of party owners
     *
     * @param player to promote to owner
     */
    public void addOwner(Player player);

    /**
     * Removes a player from the owner position of the party
     *
     * @param player player to become a default member
     */
    public void removeOwner(Player player);


    /**
     * Checks if a player has ownership of a party
     *
     * @param player player to check role
     * @return true/false if player is an owner
     */
    public boolean hasOwner(Player player);

    /**
     * Gets all standard players in the party that are not owners
     *
     * @return all players
     */
    public Set<Member> getMembers();


    /**
     * Checks if a player is in a party as a default role
     * @param player Player to check if they are in party as a standard player
     * @return true/false if player is a standard member
     */
    public boolean hasMember(Player player);


    /**
     * Checks if a player is in a party regardless of position
     *
     * @param player player to check if they are in the party
     * @return true/false if they are a member of the party
     */
    public boolean hasPlayer(Player player);

    /**
     * Sends a request for a player to join the party
     *
     * @param player player to send request to
     */

    public void sendInvitation(Player player);

    /**
     * Cancels sent request for a player to join a party
     *
     * @param player player whos invitaiton is to be canceled
     */
    public void cancelInvitation(Player player);

    /**
     * Checks weather player is on the pending list of the party
     *
     * @param player target player to check
     * @return true/false depending on if player has an active invitation
     */
    public boolean hasInvitation(Player player);

    /**
     * Removes a player from pending invitations and adds them to the party's members
     *
     * @param player player to accept into party
     */
    public void acceptInvitation(Player player);

    /**
     * Removes a player from the party regardless of position inside
     *
     * @param player player to remove
     */
    public void removePlayer(Player player);


    /**
     * Adds a player to the party as the default member position
     *
     * @param player Player to add to the party
     */
    public void addPlayer(Player player);


    /**
     * Sets a players position inside the party
     * @param player player who's position to change
     * @param membership position inside the party to make the player
     */
    public void setMembership(Player player, Class<? extends Membership> membership);


    /**
     * Gets the role a player has in a party
     *
     * @param player Player to check
     * @return the membership of the player
     */
    public Membership getMembership(Player player);

    /**
     * Deletes party and kicks all players
     */
    public void disband();

    /**
     * Gets the chat channel for the party
     *
     * @return Chat channel for party
     */
    public Channel getChannel();

    /**
     * Unique ID for this party
     *
     * @return Parties unique ID
     */
    public UUID getId();

}
