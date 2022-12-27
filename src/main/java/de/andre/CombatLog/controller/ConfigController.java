package de.andre.CombatLog.controller;

import de.andre.CombatLog.Util;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigController {

    private final PluginController controller;
    private MessageController messageController;
    public static final int SECONDSTOTICKS = 20;

    //----------------------------------------------------------//
    private int combatTime = 60;


    //----------------------------------------------------------//
    private final File config;
    private final YamlConfiguration ymlConfig;

    public ConfigController(PluginController controller) {
        this.controller = controller;

        config = new File(controller.getMain().getDataFolder(), ConfigPaths.configFileName);
        ymlConfig = YamlConfiguration.loadConfiguration(config);
    }

    public void onEnable() {
        this.messageController = new MessageController();
        load();
        saveConfig();
    }

    /*
     load Config
     */

    public void load() {
        loadCombatTime();
    }

    private void loadCombatTime() {
        this.combatTime = ymlConfig.getInt(ConfigPaths.combatTime, this.combatTime);
    }


    /*
     save Config
     */

    public void saveConfig() {
        saveCombatTime();
        try {
            ymlConfig.save(this.config.getAbsoluteFile());
            Util.sendInfoLogMessage(controller, this.config.getAbsolutePath());
            Util.sendInfoLogMessage(controller, "Config saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCombatTime() {
        this.ymlConfig.set(ConfigPaths.combatTime, this.combatTime);
    }

    public MessageController getMessageController() {
        return messageController;
    }

    public int getCombatTime() {
        return combatTime;
    }

    public void setCombatTime(int combatTime) {
        this.combatTime = combatTime;
        this.saveConfig();
    }


}
