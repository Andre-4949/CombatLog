package de.andre.CombatLog.commands;

import de.andre.CombatLog.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CombatLog implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length!=1) {
            sender.sendMessage(Util.controller.getConfig().getMessageController().getSERVERPREFIX()+"This command takes 1 argument");
            return true;
        }
        int newCombatTime = 0;
        try{
            newCombatTime = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            sender.sendMessage(Util.controller.getConfig().getMessageController().getSERVERPREFIX()+ "Please enter a number not something else");
            return true;
        }
        Util.controller.getConfig().setCombatTime(newCombatTime);
        sender.sendMessage(Util.controller.getConfig().getMessageController().getSERVERPREFIX()+"New CombatTime has been set to " + Util.controller.getConfig().getCombatTime() + "s.");

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>(){{add("60");}};
    }
}
