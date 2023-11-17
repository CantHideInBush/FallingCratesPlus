package pl.karwsz.fallingcratesplus.drop.crate;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.drop.zone.DropZone;
import pl.karwsz.fallingcratesplus.drop.zone.ZoneManager;

public class CrateListener implements Listener {









    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();
            ZoneManager manager = FallingCratesPlus.getInstance().getZoneManager();
            assert block != null;
            DropZone zone;
            if ((zone = manager.findByLocation(block.getLocation())) != null) {
                Crate crate;
                if ((crate = zone.findByKey(block)) != null) {

                }
            }
        }
    }




}
