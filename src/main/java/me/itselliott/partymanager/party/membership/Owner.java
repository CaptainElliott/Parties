package me.itselliott.partymanager.party.membership;

import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public class Owner extends Member {


    public Owner(Player player) {
        super(player);
        this.permissions = PartyPlugin.get().getConfiguration().getPermissions("owner");
    }

    protected Owner(Player player, List<String> permissions) {
        super(player, permissions);
    }


}
