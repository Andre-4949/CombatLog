package de.andre.CombatLog.controller;

import de.andre.CombatLog.listeners.CombatLogListeners;
import de.andre.CombatLog.listeners.ConflictListener;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class ListenerController {
    private final PluginController controller;

    private ArrayList<CombatLogListeners> listeners = new ArrayList<>();

    public ListenerController(PluginController controller) {
        this.controller = controller;
    }

    public void onEnable(){
        this.listeners.add(new ConflictListener());
        registerListener();
    }

    private void registerListener(){
        HandlerList.unregisterAll(controller.getMain());
        listeners.forEach(listener->controller.getMain().getServer().getPluginManager().registerEvents(listener, controller.getMain()));
    }

    public void addListener(CombatLogListeners listener){
        this.listeners.add(listener);
        controller.getMain().getServer().getPluginManager().registerEvents(listener,controller.getMain());
    }

    public CombatLogListeners getListener(Class<? extends CombatLogListeners> c){
        for (CombatLogListeners listener : listeners) {
            if (listener.getClass().equals(c))return listener;
        }
        return null;
    }
}