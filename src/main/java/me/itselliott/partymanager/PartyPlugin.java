package me.itselliott.partymanager;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import me.itselliott.partymanager.commands.PartyParentCommand;
import me.itselliott.partymanager.config.Config;
import me.itselliott.partymanager.listeners.PartyListener;
import me.itselliott.partymanager.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Elliott2 on 25/01/2016.
 */
public class PartyPlugin extends JavaPlugin {

    private static PartyPlugin instance;
    private CommandsManager<CommandSender> commands;
    private Config config;
    private PartyManager partyManager;

    @Override
    public void onEnable() {
        instance = this;
        this.setupCommands();
        this.config = new Config(this);
        this.partyManager = new PartyManager();
        Bukkit.getPluginManager().registerEvents(new PartyListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        this.config = null;
        this.partyManager = null;
        this.commands = null;
    }

    private void setupCommands() {
        this.commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender sender, String perm) {
                return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
            }
        };
        CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
        cmdRegister.register(PartyParentCommand.class);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
            } else {
                sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }

    public static PartyPlugin get() {
        return instance;
    }

    public Config getConfiguration() {
        return this.config;
    }

    public PartyManager getPartyManager() {
        return this.partyManager;
    }
}
