package org.lucky.basicluckyblock.blockitems;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.lucky.basicluckyblock.HelperFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AAGunItem implements Listener {
    private final JavaPlugin plugin;

    public AAGunItem(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createSpawnEgg(Plugin plugin) {
        ItemStack spawnEgg = new ItemStack(Material.ZOMBIE_SPAWN_EGG);
        ItemMeta meta = spawnEgg.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "AA Gun Spawn Egg");
            meta.setLore(Arrays.asList(
                    "§7Right-click to summon",
                    "§7the AA Gun structure!"
            ));

            NamespacedKey key = new NamespacedKey(plugin, "aa_gun_egg");
            meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
            spawnEgg.setItemMeta(meta);
        }
        return spawnEgg;
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().getType() == Material.ZOMBIE_SPAWN_EGG) {
            ItemStack item = event.getItem();
            NamespacedKey key = new NamespacedKey(plugin, "aa_gun_egg");
            if (item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at " + player.getName() + " run summon block_display ~ ~0.5 ~ {Passengers:[{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,0.5000f,-0.4830f,0.2588f,0.0000f,1.5000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,0.5000f,-0.4830f,0.2588f,0.0000f,2.5000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,1.4375f,-0.4830f,0.2588f,0.0000f,2.7500f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,1.4375f,-0.4830f,0.2588f,0.0000f,1.7500f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,0.5000f,-0.4830f,0.2588f,0.0000f,1.5000f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,0.5000f,-0.4830f,0.2588f,0.0000f,2.5000f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,1.4375f,-0.4830f,0.2588f,0.0000f,2.7500f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,1.4375f,-0.4830f,0.2588f,0.0000f,1.7500f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,2.3750f,-0.4830f,0.2588f,0.0000f,2.0000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,2.3750f,-0.4830f,0.2588f,0.0000f,3.0000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,2.3750f,-0.4830f,0.2588f,0.0000f,2.0000f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:cobbled_deepslate_wall\",Properties:{up:\"true\"}},transformation:[0.1294f,0.9659f,0.0000f,2.3750f,-0.4830f,0.2588f,0.0000f,3.0000f,0.0000f,0.0000f,0.5000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\"}},transformation:[0.2588f,0.7244f,0.0000f,3.1250f,-0.9659f,0.1941f,0.0000f,2.4375f,0.0000f,0.0000f,0.2000f,-0.3750f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\"}},transformation:[0.2588f,0.7244f,0.0000f,3.1250f,-0.9659f,0.1941f,0.0000f,2.4375f,0.0000f,0.0000f,0.2000f,0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\"}},transformation:[0.2588f,0.7244f,0.0000f,3.1250f,-0.9659f,0.1941f,0.0000f,3.4375f,0.0000f,0.0000f,0.2000f,-0.3750f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\"}},transformation:[0.2588f,0.7244f,0.0000f,3.1250f,-0.9659f,0.1941f,0.0000f,3.4375f,0.0000f,0.0000f,0.2000f,0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:chiseled_deepslate\",Properties:{}},transformation:[2.0000f,0.0000f,0.0000f,-0.8125f,0.0000f,0.3750f,0.0000f,0.1875f,0.0000f,0.0000f,2.0000f,-1.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,0.0000f,0.0000f,1.8125f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,-0.7500f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.3559f,1.1470f,0.0000f,0.6250f,-1.3281f,0.3073f,0.0000f,2.4375f,0.0000f,0.0000f,0.0625f,0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.3559f,1.1470f,0.0000f,0.6250f,-1.3281f,0.3073f,0.0000f,2.4375f,0.0000f,0.0000f,0.0625f,-0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.0000f,2.2941f,-0.0162f,0.0000f,0.0000f,0.6147f,0.0604f,2.2500f,1.3125f,0.0000f,0.0000f,-0.6250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[-0.0000f,0.0000f,0.1875f,0.8750f,0.0000f,0.7500f,0.0000f,0.3750f,-1.1875f,0.0000f,-0.0000f,0.6250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,0.0000f,0.0000f,1.8125f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,0.6250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.7500f,0.0000f,0.0000f,-0.6875f,0.0000f,0.9375f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,0.6250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.7500f,0.0000f,0.0000f,-0.6875f,0.0000f,0.9375f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,-0.7500f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.4330f,0.6562f,0.0000f,-0.6250f,-0.2500f,1.1367f,0.0000f,1.2500f,0.0000f,0.0000f,0.0625f,-0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.4330f,0.6562f,0.0000f,-0.6250f,-0.2500f,1.1367f,0.0000f,1.2500f,0.0000f,0.0000f,0.0625f,0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:chain\",Properties:{axis:\"x\"}},transformation:[-0.0000f,0.0000f,-1.0000f,1.1875f,0.0000f,1.0000f,0.0000f,0.7500f,1.5000f,0.0000f,-0.0000f,-0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:chain\",Properties:{axis:\"x\"}},transformation:[-0.0000f,0.0000f,-1.0000f,1.1875f,0.0000f,1.0000f,0.0000f,1.7500f,1.5000f,0.0000f,-0.0000f,-0.6875f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:redstone_block\",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,-0.4375f,0.0000f,0.1875f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:redstone_block\",Properties:{}},transformation:[0.7071f,0.0000f,0.7071f,-0.6875f,0.0000f,0.1875f,0.0000f,0.0000f,-0.7071f,0.0000f,0.7071f,-0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:redstone_block\",Properties:{}},transformation:[0.9659f,0.0000f,0.2588f,-0.5000f,0.0000f,0.1875f,0.0000f,0.0000f,-0.2588f,0.0000f,0.9659f,-0.4375f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:redstone_block\",Properties:{}},transformation:[0.5000f,0.0000f,0.8660f,-0.6875f,0.0000f,0.1875f,0.0000f,0.0000f,-0.8660f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\",south:\"true\"}},transformation:[1.0000f,0.0000f,0.0000f,0.1250f,0.0000f,0.6875f,0.0000f,1.3750f,0.0000f,0.0000f,0.5625f,-0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_bars\",Properties:{north:\"true\",south:\"true\"}},transformation:[1.0000f,0.0000f,0.0000f,0.1250f,0.0000f,0.6875f,0.0000f,1.3750f,0.0000f,0.0000f,0.6875f,-0.0625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:\"minecraft:block_display\",block_state:{Name:\"minecraft:iron_block\",Properties:{}},transformation:[0.6250f,0.0000f,0.0000f,-0.3125f,0.0000f,0.6250f,0.0000f,0.2500f,0.0000f,0.0000f,0.6250f,-0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]}]}");
                Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                horse.setInvisible(true);
                horse.setAI(false);
                horse.setInvulnerable(true);
                horse.setTamed(true);
                horse.getPersistentDataContainer().set(new NamespacedKey(plugin, "aa_gun_horse"), PersistentDataType.BYTE, (byte) 1);
                player.sendMessage(ChatColor.GREEN + "The AA Gun structure has been summoned!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Horse) {
            Horse horse = (Horse) event.getRightClicked();
            List<Entity> pass = horse.getPassengers();


            NamespacedKey key = new NamespacedKey(plugin, "aa_gun_horse");
            if (horse.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                if (horse.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != 0) {
                    player.sendTitle("",ChatColor.WHITE + "Press SPACE to shoot!",10, 100, 20);
                    new BukkitRunnable() {
                        double lastPlayerRotation = 0D;
                        final List<BlockDisplay> nearbyBlockDisplay = new ArrayList<>();
                        double radius = 2;
                        @Override
                        public void run() {
                            List<Entity> pass = horse.getPassengers();

                            if (horse.isDead() || !horse.isValid()) {
                                for (Entity entity : nearbyBlockDisplay) {
                                    entity.remove();
                                }
                                cancel();
                                return;
                            }
                            if (pass.size() != 1) {
                                cancel();
                                return;
                            }

                            Entity first = pass.getFirst();
                            double currentRotation = first.getLocation().getYaw();
                            if (nearbyBlockDisplay.isEmpty()) {
                                for (Entity entity : horse.getNearbyEntities(radius, radius, radius)) {
                                    if (entity instanceof BlockDisplay block && nearbyBlockDisplay.size() <= 36) {
                                        nearbyBlockDisplay.add(block);
                                    }
                                }
                            }
                            if (Math.abs(currentRotation - lastPlayerRotation) > 0.2) {
                                lastPlayerRotation = currentRotation;

                                Location playerLocation = first.getLocation();

                                for (BlockDisplay block : nearbyBlockDisplay) {
                                    block.setRotation(playerLocation.getYaw() + 90, block.getLocation().getPitch());
                                }
                            }
                        }
                    }.runTaskTimer(plugin, 10L, 1L);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();

            NamespacedKey key = new NamespacedKey(plugin, "aa_gun_horse");

            if (horse.getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                if (horse.getPersistentDataContainer().get(key, PersistentDataType.BYTE) != 0) {
                    shootArrowBarrage(player);
                }
            }
        }
    }

    private void shootArrowBarrage(Player player) {
        Location location = player.getEyeLocation();
        Vector direction = location.getDirection();

        Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize();
        Vector up = new Vector(0, 1, 0);

        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                Vector offset = right.clone().multiply(i * 0.3).add(up.clone().multiply(j * 0.3));

                Location spawnLocation = location.clone().add(offset).add(direction.clone().multiply(3));

                Arrow arrow = player.getWorld().spawn(spawnLocation, Arrow.class);
                arrow.setShooter(player);

                arrow.setRotation(location.getYaw(), location.getPitch());

                Vector velocity = direction.clone();
                arrow.setVelocity(velocity.multiply(5));
                arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
            }
        }
    }

}

///summon block_display ~-0.5 ~-0.5 ~-0.5 {Passengers:[{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,0.5625f,-0.4830f,0.2588f,0.0000f,1.5000f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,0.5625f,-0.4830f,0.2588f,0.0000f,2.5000f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,1.5000f,-0.4830f,0.2588f,0.0000f,2.7500f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,1.5000f,-0.4830f,0.2588f,0.0000f,1.7500f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,0.5625f,-0.4830f,0.2588f,0.0000f,1.5000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,0.5625f,-0.4830f,0.2588f,0.0000f,2.5000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,1.5000f,-0.4830f,0.2588f,0.0000f,2.7500f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,1.5000f,-0.4830f,0.2588f,0.0000f,1.7500f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,2.4375f,-0.4830f,0.2588f,0.0000f,2.0000f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,2.4375f,-0.4830f,0.2588f,0.0000f,3.0000f,0.0000f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,2.4375f,-0.4830f,0.2588f,0.0000f,2.0000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:cobbled_deepslate_wall",Properties:{up:"true"}},transformation:[0.1294f,0.9659f,0.0000f,2.4375f,-0.4830f,0.2588f,0.0000f,3.0000f,0.0000f,0.0000f,0.5000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true"}},transformation:[0.2588f,0.7244f,0.0000f,3.1875f,-0.9659f,0.1941f,0.0000f,2.4375f,0.0000f,0.0000f,0.2000f,0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true"}},transformation:[0.2588f,0.7244f,0.0000f,3.1875f,-0.9659f,0.1941f,0.0000f,2.4375f,0.0000f,0.0000f,0.2000f,1.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true"}},transformation:[0.2588f,0.7244f,0.0000f,3.1875f,-0.9659f,0.1941f,0.0000f,3.4375f,0.0000f,0.0000f,0.2000f,0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true"}},transformation:[0.2588f,0.7244f,0.0000f,3.1875f,-0.9659f,0.1941f,0.0000f,3.4375f,0.0000f,0.0000f,0.2000f,1.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:chiseled_deepslate",Properties:{}},transformation:[2.0000f,0.0000f,0.0000f,-0.7500f,0.0000f,0.3750f,0.0000f,0.1875f,0.0000f,0.0000f,2.0000f,-0.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,0.0625f,0.0000f,1.8125f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,-0.0625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.3559f,1.1470f,0.0000f,0.6875f,-1.3281f,0.3073f,0.0000f,2.4375f,0.0000f,0.0000f,0.0625f,1.3750f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.3559f,1.1470f,0.0000f,0.6875f,-1.3281f,0.3073f,0.0000f,2.4375f,0.0000f,0.0000f,0.0625f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.0000f,2.2941f,-0.0162f,0.0625f,0.0000f,0.6147f,0.0604f,2.2500f,1.3125f,0.0000f,0.0000f,0.0625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[-0.0000f,0.0000f,0.1875f,0.9375f,0.0000f,0.7500f,0.0000f,0.3750f,-1.1875f,0.0000f,-0.0000f,1.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,0.0625f,0.0000f,1.8125f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,1.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.7500f,0.0000f,0.0000f,-0.6250f,0.0000f,0.9375f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,1.3125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.7500f,0.0000f,0.0000f,-0.6250f,0.0000f,0.9375f,0.0000f,0.5625f,0.0000f,0.0000f,0.1875f,-0.0625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.4330f,0.6562f,0.0000f,-0.5625f,-0.2500f,1.1367f,0.0000f,1.2500f,0.0000f,0.0000f,0.0625f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.4330f,0.6562f,0.0000f,-0.5625f,-0.2500f,1.1367f,0.0000f,1.2500f,0.0000f,0.0000f,0.0625f,1.3750f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:chain",Properties:{axis:"x"}},transformation:[-0.0000f,0.0000f,-1.0000f,1.2500f,0.0000f,1.0000f,0.0000f,0.7500f,1.5000f,0.0000f,-0.0000f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:chain",Properties:{axis:"x"}},transformation:[-0.0000f,0.0000f,-1.0000f,1.2500f,0.0000f,1.0000f,0.0000f,1.7500f,1.5000f,0.0000f,-0.0000f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:redstone_block",Properties:{}},transformation:[1.0000f,0.0000f,0.0000f,-0.3750f,0.0000f,0.1875f,0.0000f,0.0000f,0.0000f,0.0000f,1.0000f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:redstone_block",Properties:{}},transformation:[0.7071f,0.0000f,0.7071f,-0.6250f,0.0000f,0.1875f,0.0000f,0.0000f,-0.7071f,0.0000f,0.7071f,0.5625f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:redstone_block",Properties:{}},transformation:[0.9659f,0.0000f,0.2588f,-0.4375f,0.0000f,0.1875f,0.0000f,0.0000f,-0.2588f,0.0000f,0.9659f,0.2500f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:redstone_block",Properties:{}},transformation:[0.5000f,0.0000f,0.8660f,-0.6250f,0.0000f,0.1875f,0.0000f,0.0000f,-0.8660f,0.0000f,0.5000f,0.8125f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true",south:"true"}},transformation:[1.0000f,0.0000f,0.0000f,0.1875f,0.0000f,0.6875f,0.0000f,1.3750f,0.0000f,0.0000f,0.5625f,0.1250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_bars",Properties:{north:"true",south:"true"}},transformation:[1.0000f,0.0000f,0.0000f,0.1875f,0.0000f,0.6875f,0.0000f,1.3750f,0.0000f,0.0000f,0.6875f,0.6250f,0.0000f,0.0000f,0.0000f,1.0000f]},{id:"minecraft:block_display",block_state:{Name:"minecraft:iron_block",Properties:{}},transformation:[0.6250f,0.0000f,0.0000f,-0.2500f,0.0000f,0.6250f,0.0000f,0.2500f,0.0000f,0.0000f,0.6250f,0.3750f,0.0000f,0.0000f,0.0000f,1.0000f]}]}