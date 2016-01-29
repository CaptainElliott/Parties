package me.itselliott.partymanager;

import me.itselliott.partymanager.party.Party;
import java.util.List;
import java.util.UUID;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public class PartyManager {

    private PartyCollection<Party> parties;

    public PartyManager() {
        this.parties = new PartyCollection<>();
    }

    public <P extends Party> void registerParty(P party) {
        this.parties.add(party);
    }

    public <P extends Party> void unregisterParty(P party) {
        this.parties.remove(party);
    }

    public PartyCollection<Party> getParties() {
        return this.parties;
    }
}
