package me.itselliott.partymanager.party;

import com.google.common.base.Preconditions;
import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.party.channel.Channel;
import me.itselliott.partymanager.party.membership.Member;
import me.itselliott.partymanager.party.membership.Membership;
import me.itselliott.partymanager.party.membership.Owner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public class Party implements IParty {

    private List<Membership> players;
    private Channel channel;
    private UUID id;

    private List<UUID> pending;

    public Party(List<Membership> members, Channel channel) {
        this.players = members;
        this.channel = channel;
        this.id = UUID.randomUUID();

        this.pending = new ArrayList<>();
    }

    public Party(Membership membership, Channel channel) {
        this(new ArrayList<Membership>(), channel);
        this.players.add(membership);

    }


    public List<Membership> getPlayers() {
        return this.players;
    }

    @Override
    public boolean hasMember(Player player) {
        for (Membership member : this.getMembers()) {
           if (member.getPlayer().equals(player) && member instanceof Member)
               return true;
        }
        return false;
    }

    @Override
    public boolean hasPlayer(Player player) {
        for (Membership member : this.players) {
            if (member.getPlayer().equals(player))
                return true;
        }
        return false;
    }

    public Set<Owner> getOwners() {
        Set<Owner> owners = new HashSet<Owner>();
        for (Membership member : this.players) {
            if (member instanceof Owner)
                owners.add((Owner)member);
        }
        return owners;
    }

    @Override
    public void addOwner(Player player) {
        if (this.hasMember(player)) {
            this.setMembership(player, Owner.class);
        }
    }

    @Override
    public void removeOwner(Player player) {
        if (this.hasOwner(player)) {
            for (Membership membership : this.players) {
                if (membership.getPlayer().equals(player) && membership instanceof Owner) {
                    this.players.remove(membership);
                    this.channel.removePlayer(membership.getPlayer().getUniqueId());
                    this.players.add(new Member(player));
                }
            }
        }
    }

    @Override
    public boolean hasOwner(Player player) {
        for (Membership membership : this.players) {
             return membership.getPlayer().equals(player) && membership instanceof Owner;
        }
        return false;
    }

    public Set<Member> getMembers() {
        Set<Member> members = new HashSet<Member>();
        for (Membership member : this.players) {
            if (member instanceof Member && !(member instanceof Owner))
                members.add((Member)member);
        }
        return members;
    }

    @Override
    public void sendInvitation(final Player player) {
        this.pending.add(player.getUniqueId());
    }

    @Override
    public void cancelInvitation(Player player) {
        this.pending.remove(player.getUniqueId());
    }

    @Override
    public boolean hasInvitation(Player player) {
        if (this.pending.contains(player.getUniqueId()))
            return true;
        return false;
    }

    @Override
    public void acceptInvitation(Player player) {
        if (this.hasInvitation(player)) {
            this.addPlayer(player);
            this.channel.addPlayer(player.getUniqueId());
            this.cancelInvitation(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        if (this.hasPlayer(player)) {
            for (Membership membership : this.players) {
                if (membership.getPlayer().equals(player)) {
                    this.players.remove(membership);
                    this.channel.removePlayer(membership.getPlayer().getUniqueId());
                }
            }
        }
    }

    @Override
    public void addPlayer(Player player) {
        if (!this.hasPlayer(player)) {
            this.players.add(new Member(player));
            this.channel.addPlayer(player.getUniqueId());
        }

    }

    @Override
    public void setMembership(Player player, Class<? extends Membership> membership) {
        if (this.hasPlayer(player) || (membership.equals(Member.class) && this.getOwners().size() > 1)) {
            this.removePlayer(player);
            try {
                this.players.add(membership.getDeclaredConstructor(Player.class).newInstance(player));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Membership getMembership(Player player) {
        for (Membership membership : this.players) {
            if (membership.getPlayer().equals(player))
                return membership;
        }
        return null;
    }

    @Override
    public void disband() {
        this.players.clear();
        PartyPlugin.get().getPartyManager().unregisterParty(this);
    }

    public Channel getChannel() {
        return this.channel;
    }

    public UUID getId() {
        return this.id;
    }

}
