package de.andre.CombatLog.controller;

import org.bukkit.ChatColor;

public class MessageController {
    private final String PLUGINPREFIX = "[CombatLog] ";
    private final String SERVERPREFIX = ChatColor.RED + "[CombatLog]" + ChatColor.RESET + " ";

    public String getPLUGINPREFIX() {
        return PLUGINPREFIX;
    }

    public String getSERVERPREFIX() {
        return SERVERPREFIX;
    }
}
