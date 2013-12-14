package com.king601.KingCore;


import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(player.getItemInHand().getType()== Material.STONE_HOE) {

            player.sendMessage("Hey there");
        }
    }

}
