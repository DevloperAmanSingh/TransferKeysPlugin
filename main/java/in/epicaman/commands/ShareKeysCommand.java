package in.epicaman.commands;

import in.epicaman.handlers.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import su.nightexpress.excellentcrates.ExcellentCratesAPI;
import su.nightexpress.excellentcrates.key.CrateKey;


public class ShareKeysCommand implements CommandExecutor {
    @Override
    // -> /key give <playername> <cratekey> <amount>
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            if(args.length==0){
                sender.sendMessage(ConfigHandler.NotAnArgument);
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                ConfigHandler.updateConfig();
                player.sendMessage(ChatColor.BLUE + "Config reloaded.");
            }

            if (args.length != 3){
                player.sendMessage(ConfigHandler.NotAnArgument);
                return true;
            }

            String gk = args[2];
            try {
                Integer.parseInt(gk);
            } catch (NumberFormatException e) {
                player.sendMessage(ConfigHandler.NotANumber);
                return true;
            }

            CrateKey key = ExcellentCratesAPI.getKeyManager().getKeyById(args[1]);
            if (key == null) {
                player.sendMessage(ConfigHandler.NotAKey);
                return true;
            }

            int amt = ExcellentCratesAPI.getKeyManager().getKeysAmount(player, key);

            if(Bukkit.getPlayer(args[0]) == null){
                player.sendMessage(ConfigHandler.PlayerNotFound);
                return true;
            }

            Player receiver = Bukkit.getPlayer(args[0]);

            if (sender == receiver) {
                player.sendMessage(ConfigHandler.CannotSendToYourself);
                return true;
            }

            int givenkey = Integer.parseInt(args[2]);
            if (givenkey <= amt && givenkey > 0) {
                player.sendMessage(ConfigHandler.getInfoKeeper(player.getDisplayName(), Bukkit.getPlayer(args[0]).getDisplayName(), ConfigHandler.GivenKey, givenkey, key.getName()));
                receiver.sendMessage(ConfigHandler.getInfoKeeper(player.getDisplayName(), String.valueOf(Bukkit.getPlayer(args[0])), ConfigHandler.GotKey, givenkey, key.getName()));
                ExcellentCratesAPI.getKeyManager().takeKey(player, key, givenkey);
                ExcellentCratesAPI.getKeyManager().giveKey(receiver, key, givenkey);
            } else {
                player.sendMessage(ConfigHandler.InsufficientKeys);
            }
        }

        return false;
    }
}
