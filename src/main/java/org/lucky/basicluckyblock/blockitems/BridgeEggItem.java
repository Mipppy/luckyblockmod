package org.lucky.basicluckyblock.blockitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class BridgeEggItem implements Listener {
    private final Plugin plugin;

    public BridgeEggItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createBridgeEgg(Plugin plugin) {
        ItemStack bridgeEgg = new ItemStack(Material.EGG);
        ItemMeta meta = bridgeEgg.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + "Bridge Egg");
            meta.setLore(List.of(ChatColor.AQUA + "Creates a bridge on impact!"));
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "bridge_egg"), PersistentDataType.BYTE, (byte) 1);
        }
        bridgeEgg.setItemMeta(meta);
        return bridgeEgg;
    }

    @EventHandler
    public void onEggLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Egg egg && egg.getShooter() instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "bridge_egg"), PersistentDataType.BYTE)) {
                egg.getPersistentDataContainer().set(new NamespacedKey(plugin, "bridge_egg"), PersistentDataType.BYTE, (byte) 1);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (egg.isDead() || egg.isOnGround()) {
                            this.cancel();
                            return;
                        }
                        if (egg.getLocation().distance(player.getLocation()) < 4) {
                            return;
                        }
                        Block block = egg.getLocation()
                                .subtract(egg.getVelocity().normalize().multiply(0.7))
                                .subtract(0, 1, 0)
                                .getBlock();

                        if (block.getType() == Material.AIR) {
                            block.setType(Material.GLASS);
                        }
                    }
                }.runTaskTimer(plugin, 0L, 1L);
            }
        }
    }

    @EventHandler
    public void onEggHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Egg egg) {
            if (egg.getPersistentDataContainer().has(new NamespacedKey(plugin, "bridge_egg"), PersistentDataType.BYTE)) {
                event.getEntity().remove();
                event.setCancelled(true);
            }
        }
    }
}
