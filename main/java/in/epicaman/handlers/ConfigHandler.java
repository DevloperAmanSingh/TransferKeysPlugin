package in.epicaman.handlers;
import in.epicaman.KeyTransfer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {
    private static FileConfiguration config = getUpdatedConfig();
    // Language
    public static String NotANumber, GivenKey, InsufficientKeys, PlayerNotFound, GotKey, NotAKey, NotAnArgument, CannotSendToYourself;

    // this is used to update the config,
    public static void updateConfig() {
        config = getUpdatedConfig();

        // Language
        NotANumber = ChatColor.translateAlternateColorCodes('&', config.getString("NotANumber"));
        GivenKey = ChatColor.translateAlternateColorCodes('&', config.getString("GivenKey"));
        InsufficientKeys = ChatColor.translateAlternateColorCodes('&', config.getString("InsufficientKeys"));
        PlayerNotFound = ChatColor.translateAlternateColorCodes('&', config.getString("PlayerNotFound"));
        GotKey = ChatColor.translateAlternateColorCodes('&', config.getString("GotKey"));
        NotAKey = ChatColor.translateAlternateColorCodes('&', config.getString("NotAKey"));
        NotAnArgument = ChatColor.translateAlternateColorCodes('&', config.getString("NotAnArgument"));
        CannotSendToYourself = ChatColor.translateAlternateColorCodes('&', config.getString("CannotSendToYourself"));
    }

    public static String getInfoKeeper(String sender, String reciever ,  String string, int keysamount , String keysname) {

        string = string.replace("%sender%", sender);
        string = string.replace("%reciever%", reciever);
        string = string.replace("%keys%", Integer.toString(keysamount));
        string = string.replace("%keysname%", keysname);
        return string;
    }
    // This is used to getUpdated config
    private static FileConfiguration getUpdatedConfig() {
        File customConfigFile;
        FileConfiguration customConfig;
        customConfigFile = new File(KeyTransfer.instance.getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            KeyTransfer.instance.saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return customConfig;
    }
}


