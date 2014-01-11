package com.king601.KingCore;


import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * User: king601
 * Date: 9/30/13
 */
public class KingCoreListener implements Listener {
      private final KingCore plugin; //reference main plugin :D

    //Initialization Plugin
    public KingCoreListener(KingCore plugin) {
        //Tell Plugin manager this plugin handles implemented events
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    //Stop the Rain!
    @EventHandler
    public void noWeather(WeatherChangeEvent event) {
        if(event.toWeatherState()) { //its gonna pouuuurrr
            event.setCancelled(true);   //Cancel the weather state change form occuring
            //Bukkit.broadcastMessage(ChatColor.GREEN + "Rain Prevented");
        }
    }

    //This makes it so people who aren't ops cannot break blocks.
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(!player.isOp()) {
            //Handles Rejection for block breaking
            event.setCancelled(true);
            player.sendMessage(ChatColor.WHITE + "You are not permitted to break blocks");
        }
    }
    //This makes it so people who aren't op cannot place blocks
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(!player.isOp()) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are not permitted to place blocks");
        }
    }

    //This welcomes players to the servers, if OP shows green else says Dark Purple.
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!player.isOp()){
           player.sendMessage(ChatColor.GREEN + "Welcome to the Server!");
        }  else {
           player.sendMessage(ChatColor.DARK_PURPLE + "Welcome back to the Server!");
        }
    }
    //This checks if Player's Item in Hand is Stone Hoe, if so Send em a message and cancel event
    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(player.getItemInHand().getType()== Material.STONE_HOE) {
            //now I know the player has a Hoe in hand.. let's make it do fun stuff.
            player.sendMessage("Hey there");
            event.setCancelled(true);
        }
    }
    //Tree Falling part, checks block above one you use an Axe on, if returns wood type then break it and all above
    @EventHandler
    public void treeBreak (BlockBreakEvent event) {
        Material block = event.getBlock().getType();
        Player player = event.getPlayer();
        if (block == Material.LOG && player.getItemInHand().getType().name().toLowerCase().contains("axe")){
           Location blockLocation = event.getBlock().getLocation();
            double y = blockLocation.getBlockY();
            double x = blockLocation.getBlockX();
            double z = blockLocation.getBlockZ();

            World currentWorld = event.getPlayer().getWorld();

            boolean logsLeft = true;

            while(logsLeft == true) {
                y++ ; //Increse Y Cord.
                Location blockAbove = new Location(currentWorld, x, y, z);
                Material blockAboveType = blockAbove.getBlock().getType();
                Byte blockAboveData = blockAbove.getBlock().getData();
                if (blockAboveType == Material.LOG) {
                    ItemStack droppedItem = new ItemStack(blockAboveType, 1, blockAboveData);
                    currentWorld.playEffect(blockAbove, Effect.MOBSPAWNER_FLAMES, 1);
                    blockAbove.getBlock().setType(Material.AIR);
                    currentWorld.dropItem(blockAbove, droppedItem);
                    logsLeft = true;
                } else {
                    logsLeft = false;
                }

            }
        }
    }
    //More Handlers go below me

}