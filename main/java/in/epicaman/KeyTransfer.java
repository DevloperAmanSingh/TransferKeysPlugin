package in.epicaman;

import in.epicaman.commands.TabCompleter;
import in.epicaman.commands.ShareKeysCommand;
import in.epicaman.handlers.ConfigHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class KeyTransfer extends JavaPlugin {
    public static Plugin instance;

    @Override
    public void onEnable() {
        getCommand("transferkeys").setExecutor(new ShareKeysCommand());
        getCommand("transferkeys").setTabCompleter(new TabCompleter());
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ConfigHandler.updateConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
