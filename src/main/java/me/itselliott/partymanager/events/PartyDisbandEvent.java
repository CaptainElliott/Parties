package me.itselliott.partymanager.events;

import me.itselliott.partymanager.party.Party;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyDisbandEvent extends PartyEvent {

    /**
     * Event called when a party is destroyed
     *
     * @param party Party that has called this event
     */
    public PartyDisbandEvent(Party party) {
        super(party);
    }
}
