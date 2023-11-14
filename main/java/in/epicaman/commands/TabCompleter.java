package in.epicaman.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nexmedia.engine.utils.PlayerUtil;
import su.nightexpress.excellentcrates.ExcellentCratesAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Nullable
    @Override

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            Player[] player = new Player[Bukkit.getOnlinePlayers().size()];
            Bukkit.getOnlinePlayers().toArray(player);
            for ( int i = 0; i < player.length; i++ ) {
                list.add(player[i].getName());
            }
            List<String> namecompletions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0],list,namecompletions);
            return namecompletions;
        }else if (args.length == 2){
            List <String> keysid = new ArrayList<>( ExcellentCratesAPI.getKeyManager().getKeyIds() );
            List<String> keysidcompletions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1],keysid,keysidcompletions);
            return keysidcompletions;
        }else if (args.length == 3) {
            return Arrays.asList("1", "5", "10");
        }
        return null;
    }
}
