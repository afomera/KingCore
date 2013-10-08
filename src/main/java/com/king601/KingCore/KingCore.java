package com.king601.KingCore;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: king601
 * Date: 9/30/13
 */
public class KingCore extends JavaPlugin {

    @Override
    public void onEnable() {
        new KingCoreListener(this);

        getLogger().info("King Core has been enabled");

        this.getCommand("sup").setExecutor(new KingCoreCommandExecutor(this));
        this.getCommand("kc2").setExecutor(new KingCoreCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("KingCore is now disabled!");
    }
}
