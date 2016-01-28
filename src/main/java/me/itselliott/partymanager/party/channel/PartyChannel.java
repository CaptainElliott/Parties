package me.itselliott.partymanager.party.channel;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public class PartyChannel implements Channel {

    private String format;
    private Set<UUID> members;

    public PartyChannel(String format, Set<UUID> members) {
        this.format = format;
        this.members = members;
    }

    public PartyChannel(String format, UUID member) {
        this.format = format;
        this.members = new HashSet<>();
        this.members.add(member);
    }

    @Override
    public String getFormat() {
        return this.format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void sendMessage(Player player, String message) {
        for (UUID uuid : this.members) {
            Bukkit.getPlayer(uuid).sendMessage(String.format(this.format,  player.getDisplayName(), message));
        }
    }

    @Override
    public void sendBroadcast(String message) {
        for (UUID uuid : this.members) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    @Override
    public Set<UUID> getMembers() {
        return this.getMembers();
    }

    @Override
    public void setMembers(Set<UUID> members) {
        this.members = members;
    }

    @Override
    public void addMember(UUID member) {
        this.members.add(member);
    }

    @Override
    public void addPlayer(UUID player) {
        this.members.add(player);
    }

    @Override
    public void removePlayer(UUID player) {
        this.members.add(player);
    }
}
