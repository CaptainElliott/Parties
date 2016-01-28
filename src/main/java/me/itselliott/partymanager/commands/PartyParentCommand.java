package me.itselliott.partymanager.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.NestedCommand;
import org.bukkit.command.CommandSender;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class PartyParentCommand {

    @Command(aliases = { "party"}, desc = "All party commands", min = 0, max = -1)
    @NestedCommand(PartyCommands.class) //All commands will get passed on to Commands.class
    public static void party(final CommandContext args, CommandSender sender) throws CommandException {
    }

}
