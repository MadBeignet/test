package io.github.MadBeignet;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player;




public class fly extends JavaPlugin implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command fly, String flight, String args){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            playerToggleFlightEvent(player, player.isFlying());
            return true;
        }
        return false;
    }
    //this would go in the main file but this isn't a real plugin afaik
    public void onEnable(){
        this.getCommand("fly").setExecutor(new CommandKIt());
    }
}

