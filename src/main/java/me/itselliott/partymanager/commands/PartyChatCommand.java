package me.itselliott.partymanager.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import me.itselliott.partymanager.PartyManager;
import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.events.partyChat.PartyChatSendEvent;
import me.itselliott.partymanager.party.Party;
import me.itselliott.partymanager.party.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 29/01/2016.
 */
public class PartyChatCommand {

    @Command(aliases = {"partychat", "pc", "p"}, desc = "Talk to your party members!", min = 1)
    public static void partyChat(final CommandContext args, CommandSender sender) throws CommandException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PartyManager partyManager = PartyPlugin.get().getPartyManager();
            if (partyManager.getParties().hasParty(player) && player.hasPermission(Permissions.CHAT.getPermission()) || player.hasPermission(Permissions.CHAT_SEND.getPermission())) {
                Party party = partyManager.getParties().getParty(player);
                Bukkit.getPluginManager().callEvent(new PartyChatSendEvent(party, party.getChannel(), player, args.getJoinedStrings(0)));
            }
        } else throw new CommandPermissionsException();
    }

}
