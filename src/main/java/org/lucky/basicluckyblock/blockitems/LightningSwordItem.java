package org.lucky.basicluckyblock.blockitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class LightningSwordItem implements Listener {
    private final Plugin plugin;

    public LightningSwordItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createLightningSword(Plugin plugin) {
        ItemStack lightningSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = lightningSword.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.YELLOW + "Lightning Sword");
            meta.setLore(List.of(ChatColor.GOLD + "Strike Lightning on hit!", "Still enchantable!"));
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "lightning_sword"), PersistentDataType.BYTE, (byte) 1);
        }
        lightningSword.setItemMeta(meta);
        return lightningSword;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand.hasItemMeta()) {
                ItemMeta meta = itemInHand.getItemMeta();
                if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "lightning_sword"), PersistentDataType.BYTE)) {
                    Entity target = event.getEntity();
                    if (target instanceof LivingEntity) {
                        target.getWorld().strikeLightning(target.getLocation());
                        player.sendMessage(ChatColor.GREEN + "You struck lightning!");
                    }
                }
            }
        }
    }
}
