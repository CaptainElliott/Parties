package me.itselliott.partymanager.party.membership;

import me.itselliott.partymanager.PartyPlugin;
import me.itselliott.partymanager.party.channel.Channel;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.List;

/**
 * Created by Elliott2 on 26/01/2016.
 */
public class Member implements Membership {

    protected Player player;
    protected List<String> permissions;

    public PermissionAttachment permissionAttachment;

    public Member(Player player) {
        this(player, PartyPlugin.get().getConfiguration().getPermissions("member"));
    }

    protected Member(Player player, List<String> permissions) {
        this.player = player;
        this.permissions = permissions;
        this.permissionAttachment = player.addAttachment(PartyPlugin.get());
        this.setPermissions();
    }


    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public List<String> getPermissions() {
        return this.permissions;
    }

    @Override
    public void addPermission(String string) {
        this.permissions.add(string);
        this.permissionAttachment.setPermission(string, true);
    }

    @Override
    public void removePermission(String string) {
        this.permissions.remove(string);
        this.permissionAttachment.setPermission(string, false);
    }

    @Override
    public void setPermissions() {
        for (String permission : this.permissions) {
            this.permissionAttachment.setPermission(permission, true);
        }
    }

}
