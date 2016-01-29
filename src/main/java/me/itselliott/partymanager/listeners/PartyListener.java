package me.itselliott.partymanager.listeners;

import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.events.*;
import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.util.ChatUtil;
import me.itselliott.partymanager.util.Time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyListener implements Listener {

    @EventHandler
    public void onPartyAcceptEvent(PartyAcceptEvent event) {
        event.getParty().acceptInvitation(event.getPlayer());
        event.getParty().getChannel().sendBroadcast(ChatColor.GOLD + event.getPlayer().getDisplayName() + " has joined the party!");
        event.getPlayer().sendMessage(ChatColor.GOLD + "You have joined the party!");
    }

    @EventHandler
    public void onPartyDeclineEvent(PartyDeclineEvent event) {
        event.getParty().cancelInvitation(event.getPlayer());
        event.getParty().getChannel().sendBroadcast(ChatColor.RED + event.getPlayer().getDisplayName() + " declined your party invitation");
    }

    @EventHandler
    public void onPartyInviteEvent(PartyInviteEvent event) {
        final Player player = event.getPlayer();
        final Player target = event.getRecipiant();
        final Party party = PartyPlugin.get().getPartyManager().getParties().getParty(event.getPlayer());
        party.sendInvitation(event.getRecipiant());
        /* Chat messages */
        party.getChannel().sendBroadcast(ChatColor.GREEN + target.getDisplayName() + " Has been invited to join the party!");
        /* Send target player messages */
        target.sendMessage(ChatColor.GREEN + player.getDisplayName() + " has send you an invitation to join their party!");
        ChatUtil.sendBlankLine(target);
        target.sendMessage("        " + ChatUtil.sendCommandClick(target, ChatColor.GREEN + "" + ChatColor.BOLD + "[ACCEPT]", "party accept " + party.getId()) + ChatUtil.sendCommandClick(target, ChatColor.RED + "" + ChatColor.BOLD + "[DECLINE]", "party decline " + party.getId()));
        ChatUtil.sendBlankLine(target);
        /* Invitation expiration */
        Bukkit.getScheduler().scheduleSyncDelayedTask(PartyPlugin.get(), new BukkitRunnable() {
            @Override
            public void run() {
                if (party.hasInvitation(target)) {
                    party.cancelInvitation(target);
                    player.sendMessage(ChatColor.GOLD + target.getDisplayName() + ChatColor.GOLD + " failed to accept your party invitation");
                    target.sendMessage(ChatColor.RED + "You failed to accept an invitation from " + player.getDisplayName() + " to join their party");
                }

            }
        }, Time.toTicks(60));
    }

    @EventHandler
    public void onCreateParyEvent(CreatePartyEvent event) {
        PartyPlugin.get().getPartyManager().registerParty(event.getParty());
        event.getPlayer().sendMessage(ChatColor.GREEN + "You created your party, invite players using /invite <username>");
    }

    @EventHandler
    public void onPlayerLeaveParty(PartyLeaveEvent event) {
        event.getParty().getChannel().sendBroadcast(event.getPlayer().getDisplayName() + ChatColor.GOLD + " has left the party");
        event.getParty().removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onPartyDisband(PartyDisbandEvent event) {
        event.getParty().getChannel().sendBroadcast(ChatColor.BOLD + "" + ChatColor.DARK_RED + "Party has been disbanded as the only owner left");
        event.getParty().disband();
    }

    @EventHandler
    public void onPartyKickEvet(PartyKickEvent event) {
        event.getParty().removePlayer(event.getPlayer());
        event.getParty().getChannel().sendBroadcast(ChatColor.RED + event.getPlayer().getDisplayName() + " Has been removed from your party");
        event.getPlayer().sendMessage(ChatColor.RED + "You have been removed from the party!");
    }

}
