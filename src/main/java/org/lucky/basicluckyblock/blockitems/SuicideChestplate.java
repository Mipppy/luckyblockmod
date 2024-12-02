package org.lucky.basicluckyblock.blockitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.lucky.basicluckyblock.HelperFunction;

import java.util.Arrays;

public class SuicideChestplate implements Listener {
    private final Plugin plugin;

    public SuicideChestplate(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createSuicideChestplate() {
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§cSuicide Chestplate");
            meta.setUnbreakable(false);
            meta.setLore(Arrays.asList("§eUpon losing all durability, kaboolie!"));
            chestplate.setItemMeta(meta);
        }
        return chestplate;
    }
    @EventHandler
    public void onChestplateDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType() == Material.DIAMOND_CHESTPLATE &&
                item.hasItemMeta() &&
                "§cSuicide Chestplate".equals(item.getItemMeta().getDisplayName())) {

            boolean broke = HelperFunction.damageItem(item, 75*event.getDamage());
            if (broke) {
                Location location = player.getLocation();
                player.getWorld().createExplosion(location, 10.0f, true, true);

                player.getInventory().setChestplate(null);

                event.setCancelled(true);
            }
        }
    }
}
