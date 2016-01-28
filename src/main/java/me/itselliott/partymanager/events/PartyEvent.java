package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Party party;
    private boolean canceled;

    /**
     * Base event for every event to do with parties
     *
     * @param party Party that has called this event
     */
    public PartyEvent(Party party) {
        this.party = party;
    }

    public Party getParty() {
        return this.party;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }
}
