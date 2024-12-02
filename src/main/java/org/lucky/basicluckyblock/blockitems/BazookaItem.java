package org.lucky.basicluckyblock.blockitems;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.lucky.basicluckyblock.HelperFunction;

import java.util.List;
import java.util.Random;

public class BazookaItem implements Listener {

    private final Plugin plugin;

    public BazookaItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createBazookaItem() {
        ItemStack bazooka = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = bazooka.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§cBazooka");
            meta.setLore(List.of("§7Shoots explosive fireballs!"));
        }
        bazooka.setItemMeta(meta);
        return bazooka;
    }

    @EventHandler
    public void onPlayerUseBazooka(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() != Material.BLAZE_ROD) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName() || !meta.getDisplayName().equals("§cBazooka")) return;

        Fireball fireball = player.getWorld().spawn(player.getEyeLocation().add(player.getLocation().getDirection().multiply(1.5)), Fireball.class);
        fireball.setShooter(player);
        fireball.setYield(3.0f);
        fireball.setIsIncendiary(false);
        player.sendMessage("§eYou fired the bazooka!");
        new BukkitRunnable() {
            final Location initialLocation = fireball.getLocation();
            double distanceTraveled = 0;
            double rotationAngle = 0;
            @Override
            public void run() {
                if (fireball.isDead() || !fireball.isValid()) {
                    cancel();
                    return;
                }
                double radiuz = 3D;
                distanceTraveled = initialLocation.distance(fireball.getLocation());

                if (distanceTraveled >= 150) {
                    fireball.getWorld().createExplosion(fireball.getLocation(), fireball.getYield(), false, false);
                    fireball.remove();
                    cancel();
                    return;
                }
                radiuz -= 0.5D;
                radiuz = Math.max(radiuz,0.1);
                rotationAngle += Math.PI / 16;
                HelperFunction.spawnParticleCircle(fireball.getLocation(), radiuz, Particle.FLAME, 20, rotationAngle, fireball.getVelocity());
            }
        }.runTaskTimer(plugin, 0L, 3L);
    }

    @EventHandler
    public void onFireballExplosion(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Fireball)) return;
        HelperFunction.EnhancedExplosion(event, 10,50);
    }

}
