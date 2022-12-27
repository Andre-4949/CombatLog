package de.andre.CombatLog.listeners;

import de.andre.CombatLog.Util;
import de.andre.CombatLog.controller.ConfigController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;

public class ConflictListener implements CombatLogListeners{
    private ArrayList<Player> playerList = new ArrayList<>();

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event){
        Entity attacked = event.getEntity();
        if (!(attacked instanceof Player p))return;
        if (!(event.getDamager() instanceof Player p1))return;
        playerList.add(p1);
        Bukkit.getScheduler().runTaskLater(Util.controller.getMain(),()->{
            playerList.remove(p1);
        },Util.controller.getConfig().getCombatTime());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if (this.playerList.contains(event.getPlayer())){
            event.getPlayer().setHealth(0d);
        }
    }

    @EventHandler
    public void useElytra(EntityToggleGlideEvent event){
        if (!event.isGliding())return;
        if (!(event.getEntity() instanceof Player p))return;
        if (playerList.contains(p)){
            Location loc = p.getLocation().clone();
            p.sendMessage(Util.controller.getConfig().getMessageController().getSERVERPREFIX()+"Please land in the next 5s or you will be eliminated. Don't try to flee.");
            Bukkit.getScheduler().runTaskLater(Util.controller.getMain(),()->{
                if (p.isGliding()||(loc.distance(p.getLocation())>=10)){
                    loc.setPitch(90f);
                    p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
                }
            },5* ConfigController.SECONDSTOTICKS);
        }
    }
}
