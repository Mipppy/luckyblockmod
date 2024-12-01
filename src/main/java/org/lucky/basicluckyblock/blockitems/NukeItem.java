package org.lucky.basicluckyblock.blockitems;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

public class NukeItem implements Listener {

    private final Plugin plugin;

    public NukeItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack createNukeItem() {
        ItemStack nuke = new ItemStack(Material.STICK);
        ItemMeta meta = nuke.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§cNuke Stick");
            meta.setLore(java.util.Arrays.asList("§7Use this to summon a barrage of TNT!"));
        }
        nuke.setItemMeta(meta);
        return nuke;
    }

    @EventHandler
    public void onPlayerUseNuke(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() != Material.STICK) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName() || !meta.getDisplayName().equals("§cNuke Stick")) return;

        Location targetBlock = getTargetBlock(player, 250);
        if (targetBlock == null) {
            player.sendMessage("§cYou are not looking at a block!");
            return;
        }

        spawnTNTBarrage(targetBlock, player.getWorld());
        showNukeParticleEffect(targetBlock, player.getWorld());
        player.sendMessage("§eNuke incoming at " + targetBlock.getBlockX() + ", " + targetBlock.getBlockY() + ", " + targetBlock.getBlockZ() + "!");
    }

    private Location getTargetBlock(Player player, int maxRange) {
        BlockIterator iterator = new BlockIterator(player, maxRange);
        while (iterator.hasNext()) {
            Location blockLocation = iterator.next().getLocation();
            if (blockLocation.getBlock().getType() != Material.AIR) {
                return blockLocation;
            }
        }
        return null;
    }

    private void spawnTNTBarrage(Location location, World world) {
        int TNT_COUNT = 30;
        double SPREAD = 3.0;

        for (int i = 0; i < TNT_COUNT; i++) {
            double offsetX = (Math.random() - 0.5) * SPREAD;
            double offsetZ = (Math.random() - 0.5) * SPREAD;
            Location tntLocation = location.clone().add(offsetX, 200 + i, offsetZ);

            TNTPrimed tnt = world.spawn(tntLocation, TNTPrimed.class);
            tnt.setFuseTicks(160);
            tnt.setIsIncendiary(true);
        }
    }

    private void showNukeParticleEffect(Location location, World world) {
        int pillarHeight = 50;
        double spread = 0.2;
        Location startLocation = location.clone().add(0, 1, 0);
        double radius = 5.0;

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(255, 0, 0), 2.0f);

        new BukkitRunnable() {
            int ticksRemaining = 50;

            @Override
            public void run() {
                if (ticksRemaining <= 0) {
                    this.cancel();
                    return;
                }

                for (int i = 0; i < 360; i += 10) {
                    double angle = Math.toRadians(i);
                    double xOffset = radius * Math.cos(angle);
                    double zOffset = radius * Math.sin(angle);
                    Location particleLocation = startLocation.clone().add(xOffset, 0, zOffset);

                    world.spawnParticle(Particle.DUST, particleLocation, 1, spread, spread, spread, dustOptions);
                }

                for (int i = 0; i < pillarHeight; i++) {
                    Location particleLocation = startLocation.clone().add(0, i, 0);
                    world.spawnParticle(Particle.DUST, particleLocation, 1, spread, spread, spread, dustOptions);
                }

                ticksRemaining--;
            }
        }.runTaskTimer(plugin, 0, 3);
    }

}
