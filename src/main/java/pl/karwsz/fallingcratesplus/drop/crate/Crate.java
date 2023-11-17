package pl.karwsz.fallingcratesplus.drop.crate;

import com.canthideinbush.utils.AngleMath;
import com.canthideinbush.utils.InventoryUtils;
import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.tempblock.TempBlock;
import com.projectkorra.projectkorra.BendingManager;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.PKListener;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.ability.util.Collision;
import com.projectkorra.projectkorra.ability.util.CollisionManager;
import com.projectkorra.projectkorra.command.Commands;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.drop.zone.DropZone;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Crate implements Keyed<Block> {


    private DropZone dropZone;
    private CrateSettings crateSettings;
    private Block block;



    public Crate(DropZone dropZone, CrateSettings crateSettings, Block block) {
        this.dropZone = dropZone;
        this.crateSettings = crateSettings;
        this.block = block;
    }



    public void spawn() {
        if (dropZone.getSettings().fallAnimation) {
            block = block.getRelative(0, 5, 0);
            ArmorStand crateStand = block.getWorld().spawn(block.getLocation().add(0.5, 0, 0.5), ArmorStand.class, as -> {
                as.setVisible(false);
                as.getEquipment().setHelmet(new ItemStack(Material.CHEST));
                if (FallingCratesPlus.getInstance().getHooks().isProjectKorraHooked()) {
                    CoreAbility.getAbilitiesByInstances().forEach(ability -> {
                    });
                }
            });
            new BukkitRunnable() {
                double angle = ThreadLocalRandom.current().nextInt(0, 360);
                int removeTimer = 0;
                public void run() {
                    if (crateStand.isOnGround() || removeTimer > 0) {
                        removeTimer++;
                        crateStand.teleport(crateStand.getLocation().add(0, -0.05, 0));
                        if (crateStand.getLocation().add(0, 0.9, 0).getBlock().getType().isSolid()) {
                            block = crateStand.getLocation().getBlock().getRelative(BlockFace.UP);
                            org.bukkit.block.data.type.Chest chestData = (org.bukkit.block.data.type.Chest) Bukkit.createBlockData(Material.CHEST);
                            chestData.setFacing(AngleMath.yawToFace((float) AngleMath.roundTo360(angle - 180), false));
                            TempBlock.setData(block, chestData, 10000);
                            crateStand.remove();
                            fill();
                            cancel();
                            return;
                        }
                    }
                    crateStand.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 3, 2, false, false));
                    //Vector rotated = new Vector(eAngle.getX(), 0, crateStand.getEyeLocation().getZ()).rotateAroundY(Math.toRadians(angle));
                    angle += 5;
                    crateStand.setHeadPose(crateStand.getHeadPose().setY(Math.toRadians(angle)));
                }
            }.runTaskTimer(FallingCratesPlus.getInstance(), 0, 1);

        }
        else {
            TempBlock.setMaterial(block, Material.CHEST, 10000);
            fill();
        }
        dropZone.register(this);
    }


    private void fill() {
        Chest chest = (Chest) block.getState();
        Integer quantity = crateSettings.getRandomDropQuantity();
        for (int i = 0; i < Optional.of(quantity).orElse(1); i++) {
            DropItem item = crateSettings.getRandomDropItem();
            if (item == null) continue;
            chest.getInventory().setItem(InventoryUtils.getRandomEmptySlot(chest.getBlockInventory()), item.getRandomAmount());
        }




    }

    @Override
    public Block getKey() {
        return block;
    }
}
