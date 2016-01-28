package me.itselliott.partymanager;

import me.itselliott.partymanager.party.Party;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyCollection<P extends Party> extends ArrayList<P> {

    public PartyCollection() {

    }

    /**
     * Checks weather target player is a member of a party
     *
     * @param player target player to check
     * @return true or false if player has a a party
     */
    public boolean hasParty(Player player) {
        for (P party : this) {
            if (party.hasPlayer(player))
                return true;
        }
        return false;
    }

    /**
     * Checks weather the party ID points to a valid party
     *
     * @param id party id to check
     * @return true or false if party is valid
     */
    public boolean isParty(UUID id) {
        for (P party : this) {
            if (party.getId().equals(id))
                return true;
        }
        return false;
    }

    /**
     * Gets the {@link Party} that the player belongs to
     *
     * @param player target player to get party
     * @return instance of party
     */
    public P getParty(Player player) {
        for (P party : this) {
            if (party.hasPlayer(player))
                return party;
        }
        return null;
    }

    /**
     * Gets the {@link Party} by its UUID
     *
     * @param id parties UUID
     * @return instance of party
     */
    public P getParty(UUID id) {
        for (P party : this) {
            if (party.getId().equals(id))
                return party;
        }
        return null;
    }

}
