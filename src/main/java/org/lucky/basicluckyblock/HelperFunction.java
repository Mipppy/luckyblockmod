package org.lucky.basicluckyblock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Random;

public class HelperFunction {
    public static void EnhancedExplosion(EntityExplodeEvent event, int radius, int percentFlight) {

        World world = event.getEntity().getWorld();
        Location explosionLocation = event.getEntity().getLocation();

        event.blockList().clear();

        Random rand = new Random();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance > radius) continue;
                    Location blockLocation = explosionLocation.clone().add(x, y, z);
                    Material blockType = blockLocation.getBlock().getType();

                    if (blockType == Material.AIR || !blockType.isBlock()) continue;

                    if (rand.nextInt(100) < percentFlight) {
                        Vector velocity = blockLocation.toVector().subtract(explosionLocation.toVector());
                        if (velocity.lengthSquared() == 0) continue;

                        velocity = velocity.normalize().multiply(1.5);
                        FallingBlock fallingBlock = world.spawnFallingBlock(blockLocation, blockType.createBlockData());
                        fallingBlock.setVelocity(velocity);

                        blockLocation.getBlock().setType(Material.AIR);
                    } else {
                        blockLocation.getBlock().setType(Material.AIR);
                    }
                }
            }
        }
    }
    public static void spawnParticleCircle(Location center, double radius, Particle particle, int points, double rotationAngle, Vector direction) {
        World world = center.getWorld();
        if (world == null) return;

        direction = direction.normalize();

        Vector perpendicular = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize();

        if (perpendicular.length() == 0) {
            perpendicular = new Vector(1, 0, 0);
        }

        Vector orthogonal = direction.clone().crossProduct(perpendicular).normalize();

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points + rotationAngle;

            Vector offset = perpendicular.clone().multiply(radius * Math.cos(angle))
                    .add(orthogonal.clone().multiply(radius * Math.sin(angle)));

            Location particleLocation = center.clone().add(offset);
            world.spawnParticle(particle, particleLocation, 1, 0, 0, 0, 0);
        }
    }
    public static boolean damageItem(ItemStack item, int damageAmount) {
        if (item.getType() != Material.AIR) {
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable damageableMeta) {
                int currentDamage = damageableMeta.getDamage();
                int newDamage = currentDamage + damageAmount;

                if (newDamage >= item.getType().getMaxDurability()) {
                    item.setAmount(0);
                    return true;
                } else {
                    damageableMeta.setDamage(newDamage);
                    item.setItemMeta(meta);
                    return false;
                }
            }
        }
        return false;
    }
}
