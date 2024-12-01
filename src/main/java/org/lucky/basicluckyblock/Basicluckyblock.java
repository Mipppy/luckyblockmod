package org.lucky.basicluckyblock;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.lucky.basicluckyblock.blockitems.NukeItem;

public final class Basicluckyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Lucky block plugin enabled!");

        // Register NukeItem event listener
        Bukkit.getPluginManager().registerEvents(new NukeItem(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Lucky block plugin disabled!");
    }

    // Optional: Add a command to give the Nuke Stick
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nukestick")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().addItem(NukeItem.createNukeItem());
                player.sendMessage("§aYou have received the Nuke Stick!");
                return true;
            } else {
                sender.sendMessage("§cThis command can only be used by a player!");
                return true;
            }
        }
        return false;
    }
}
