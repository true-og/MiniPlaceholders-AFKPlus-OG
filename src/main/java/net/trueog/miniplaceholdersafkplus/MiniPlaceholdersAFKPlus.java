package net.trueog.miniplaceholdersafkplus;

import org.bukkit.plugin.java.JavaPlugin;

public final class MiniPlaceholdersAFKPlus extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Register the hook.
        new MiniPlaceholdersHook(this);

        getLogger().info(getName() + " v." + getPluginMeta().getVersion() + " has been enabled!");

    }

}
