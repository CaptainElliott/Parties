package me.itselliott.partymanager.util;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public class ChatUtil {

    public static void sendBlankLine(Player player) {
        player.sendMessage("\n");
    }

    public static TextComponent sendCommandClick(Player player, String string, String command) {
        TextComponent textComponent = new TextComponent(string);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return textComponent;
    }

}
