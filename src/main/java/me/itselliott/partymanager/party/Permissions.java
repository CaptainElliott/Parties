package me.itselliott.partymanager.party;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Elliott2 on 28/01/2016.
 */
public enum Permissions {

    CREATE("party.create"),
    DISBAND("party.disband"),
    INVITE("party.invite"),
    INVITE_CANCEL("party.invite.cancel"),
    INVITE_SEND("party.invite.send"),
    INVITE_ACCEPT("party.invite.accept"),
    INVITE_DECLINE("party.invite.decline"),
    REMOVE("party.remove"),
    PROMOTE("party.promote"),
    LEAVE("party.leave"),
    CHAT("party.chat"),
    CHAT_SEND("party.chat.send"),
    CHAT_RECEIVE("party.chat.receive");

    private String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }

    public static Set<String> getPermissions() {
        Set<String> permissions = new HashSet<>();
        for (Permissions permission : Permissions.values()) {
            permissions.add(permission.getPermission());
        }
        return permissions;
    }
}
