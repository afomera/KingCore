package com.king601.KingCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: king601
 * Date: 9/30/13
 */
public class KingCoreCommandExecutor implements CommandExecutor {

    private KingCore plugin;

    public KingCoreCommandExecutor(KingCore plugin) {
        this.plugin = plugin;
    }


    //@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sup")){ // If the player typed /kc then do the following...
            // do something...
            sender.sendMessage(ChatColor.GOLD + "Sup Brah!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("kc2")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.BLUE + "This command can only be run by a player.");
            } else {
                Player player = (Player) sender;
                sender.sendMessage("Hey Hey " + player + "!");
                Bukkit.broadcastMessage("hello world2 for kc2 from player");
            }
            return true;
        }
        return false;
    }

}
