package me.itselliott.partymanager.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import me.itselliott.partymanager.PartyCollection;
import me.itselliott.partymanager.PartyManager;
import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.events.*;
import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.Permissions;
import me.itselliott.partymanager.party.channel.PartyChannel;
import me.itselliott.partymanager.party.membership.Member;
import me.itselliott.partymanager.party.membership.Membership;
import me.itselliott.partymanager.party.membership.Owner;
import me.itselliott.partymanager.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyCommands {

    @Command(aliases = "create", desc = "Create a brand new party!", max = 0 )
    public static void create(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.CREATE.getPermission())) {
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (!partyManager.getParties().hasParty(player)) {
                    Party party = new Party(new Owner(player), new PartyChannel(ChatColor.DARK_PURPLE + "[P] " + ChatColor.GOLD + "%s: " + ChatColor.GRAY + "%s", player.getUniqueId()));
                    Bukkit.getPluginManager().callEvent(new CreatePartyEvent(party, player));
                } else throw new CommandException("You are already in a plugin, you must leave to create your own");
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = "disband", desc = "Disband your party, removes everyone else and destroys party", max = 0)
    public static void disband(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.DISBAND.getPermission())) {
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (partyManager.getParties().hasParty(player)) {
                    Party party = partyManager.getParties().getParty(player);
                    Bukkit.getPluginManager().callEvent(new PartyDisbandEvent(party));
                }
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = "invite", desc = "Sends an invitation for a player to join your party", usage = "[player] - The player to request to join party", min = 1, max = 1)
    public static void invite(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (player.hasPermission(Permissions.INVITE_SEND.getPermission())) {
                final Player target = Bukkit.getPlayer(args.getString(0));
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (target.isOnline()) {
                    if (!partyManager.getParties().hasParty(target)) {
                        Bukkit.getPluginManager().callEvent(new PartyInviteEvent(partyManager.getParties().getParty(player), player, target));
                    } else throw new CommandException("Target player is already in a party");
                } else throw new CommandException("Target player is not online!");
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = "accept", desc = "Accepts a pending party invitation", max = 1)
    public static void accept(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.INVITE_ACCEPT.getPermission())) {
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (args.argsLength() > 0 && partyManager.getParties().isParty(UUID.fromString(args.getString(0)))) {
                    Party party = partyManager.getParties().getParty(UUID.fromString(args.getString(0)));
                    if (party.hasInvitation(player)) {
                        Bukkit.getPluginManager().callEvent(new PartyAcceptEvent(party, player));
                    }
                } else {
                /* Accepts the latest invitation if there are multiple */
                    for (Party party : partyManager.getParties()) {
                        if (party.hasInvitation(player)) {
                            Bukkit.getPluginManager().callEvent(new PartyAcceptEvent(party, player));
                        }
                    }
                }
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = "decline", desc = "Declines a pending party invitation", max = 1)
    public static void decline(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.INVITE_DECLINE.getPermission())) {
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (args.argsLength() > 0 && partyManager.getParties().isParty(UUID.fromString(args.getString(0)))) {
                    Party party = partyManager.getParties().getParty(UUID.fromString(args.getString(0)));
                    if (party.hasInvitation(player)) {
                        Bukkit.getPluginManager().callEvent(new PartyDeclineEvent(party, player));
                    }
                } else {
                /* Declines the latest invitation if there are multiple */
                    for (Party party : partyManager.getParties()) {
                        if (party.hasInvitation(player)) {
                            Bukkit.getPluginManager().callEvent(new PartyDeclineEvent(party, player));
                        }
                    }
                }
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = "leave", desc = "Leave the current party you are in", max = 1)
    public static void leave(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.LEAVE.getPermission())) {
                PartyManager partyManager = PartyPlugin.get().getPartyManager();
                if (partyManager.getParties().hasParty(player)) {
                    Party party = partyManager.getParties().getParty(player);
                    if (!party.hasOwner(player) || (party.hasOwner(player) && party.getOwners().size() > 1)) {
                        Bukkit.getPluginManager().callEvent(new PartyLeaveEvent(party, player));
                    } else if (party.getOwners().size() <= 1) {
                        Bukkit.getPluginManager().callEvent(new PartyDisbandEvent(party));
                    }
                }
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = {"kick", "remove"}, desc = "Remove someone from your party", min = 1, max = 1)
    public static void kick(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.REMOVE.getPermission())) {
                Player target = Bukkit.getPlayer(args.getString(0));
                Party party = PartyPlugin.get().getPartyManager().getParties().getParty(player);
                if (!party.hasOwner(target)) {
                    Bukkit.getPluginManager().callEvent(new PartyKickEvent(party, target));
                } else throw new CommandException("You cannot kick another owner!");
            } else throw new CommandPermissionsException();
        }
    }

    @Command(aliases = {"info", "information"}, desc = "List information about your current party, or specified party", max = 0)
    public static void info(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PartyManager partyManager = PartyPlugin.get().getPartyManager();
            if (partyManager.getParties().hasParty(player)) {
                Party party = partyManager.getParties().getParty(player);
                StringBuilder owners = new StringBuilder("Owners: ");
                for (Owner owner : party.getOwners()) {
                    owners.append(owner.getPlayer().getDisplayName()).append(", ");
                }
                StringBuilder members = new StringBuilder("Members: ");
                for (Member member : party.getMembers()) {
                    members.append(member.getPlayer().getDisplayName()).append(", ");
                }
                ChatUtil.sendLine(player);
                player.sendMessage(ChatColor.GOLD + owners.toString());
                player.sendMessage(ChatColor.GREEN + members.toString());
                ChatUtil.sendLine(player);
            } else throw new CommandException("You are not in a party!");
        }
    }

    @Command(aliases = "list", desc = "List all current parties", max = 0)
    public static void list(final CommandContext args, CommandSender sender) throws CommandException {
        PartyManager partyManager = PartyPlugin.get().getPartyManager();
        PartyCollection<Party> parties = partyManager.getParties();
        if (parties.size() > 0) {
            int i = 1;
            for (Party party : parties) {
                StringBuilder owners = new StringBuilder(ChatColor.RED + "" + i + ": " + ChatColor.LIGHT_PURPLE + "Party owned by: ");
                for (Owner owner : party.getOwners()) {
                    owners.append(ChatColor.GOLD).append(owner.getPlayer().getDisplayName()).append(", ");
                }
                ChatUtil.sendLine(sender);
                sender.sendMessage(owners.toString());
                ChatUtil.sendLine(sender);
                i++;
            }
        } else throw new CommandException("There are no parties currently in use");
    }

    @Command(aliases = {"teleport", "tp", "bring", "warp"}, desc = "Bring all members of your party to your location", max = 0)
    public static void warp(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PartyManager partyManager = PartyPlugin.get().getPartyManager();
            if (partyManager.getParties().hasParty(player) && player.hasPermission(Permissions.WARP.getPermission())) {
                Party party = partyManager.getParties().getParty(player);
                Set<UUID> players = new HashSet<>();
                for (Membership member : party.getPlayers()) {
                    if (!member.getPlayer().equals(player)) {
                        players.add(member.getPlayer().getUniqueId());
                    }
                }
                Bukkit.getPluginManager().callEvent(new PartyWarpEvent(party, player, players));
            } else throw new CommandPermissionsException();
        }
    }
}
