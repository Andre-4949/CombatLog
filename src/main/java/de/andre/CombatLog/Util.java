package de.andre.CombatLog;

import de.andre.CombatLog.controller.PluginController;
import org.bukkit.Bukkit;
import org.bukkit.permissions.ServerOperator;

public class Util {
    public static PluginController controller;

    public static void sendMessageToAdmins(PluginController controller,Object o){
        Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).forEach(x->x.sendMessage(controller.getConfig().getMessageController().getSERVERPREFIX()+o.toString()));
    }
    public static void sendInfoLogMessage(PluginController controller, Object o){
        Bukkit.getLogger().info(controller.getConfig().getMessageController().getSERVERPREFIX()+o.toString());
    }
}
