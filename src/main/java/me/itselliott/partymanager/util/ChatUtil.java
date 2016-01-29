package me.itselliott.partymanager.util;

import com.google.gson.JsonElement;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class ChatUtil {

    public static void sendBlankLine(CommandSender sender) {
        sender.sendMessage("\n");
    }

    public static void sendLine(CommandSender sender) {
        sender.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------");
    }

    public static TextComponent sendCommandClick(Player player, String string, String command) {
        TextComponent textComponent = new TextComponent(string);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return textComponent;
    }

}
