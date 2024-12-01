package org.lucky.basicluckyblock.blockitems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class BazookaItem implements Listener {

    private final Plugin plugin;

    public BazookaItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createBazookaItem() {
        ItemStack bazooka = new ItemStack(Material.BLAZE_ROD); // Use a Blaze Rod as the bazooka
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
        fireball.setYield(4.0f);
        fireball.setIsIncendiary(false);
        player.sendMessage("§eYou fired the bazooka!");
    }

    @EventHandler
    public void onFireballExplosion(EntityExplodeEvent event) {
        if (!(event.getEntity() instanceof Fireball)) return;

        Fireball fireball = (Fireball) event.getEntity();
        World world = fireball.getWorld();
        Location explosionLocation = fireball.getLocation();

        event.blockList().clear(); // Prevent block destruction

        Random rand = new Random();
        int radius = 5;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance > radius) continue;
                    Location blockLocation = explosionLocation.clone().add(x, y, z);
                    Material blockType = blockLocation.getBlock().getType();

                    // Skip invalid blocks
                    if (blockType == Material.AIR || !blockType.isBlock()) continue;

                    // Randomly decide whether to launch the block
                    if (rand.nextInt(10) < 5) { // 30% chance to launch
                        // Calculate velocity vector
                        Vector velocity = blockLocation.toVector().subtract(explosionLocation.toVector());
                        if (velocity.lengthSquared() == 0) continue; // Avoid zero-length vector

                        velocity = velocity.normalize().multiply(1.5); // Scale the velocity
                        FallingBlock fallingBlock = world.spawnFallingBlock(blockLocation, blockType.createBlockData());
                        fallingBlock.setVelocity(velocity);

                        blockLocation.getBlock().setType(Material.AIR); // Remove the original block
                    } else {
                        // Just remove the block without launching
                        blockLocation.getBlock().setType(Material.AIR);
                    }
                }
            }
        }
    }

}
