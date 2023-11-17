package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.WorldGuardUtils;
import com.canthideinbush.utils.managers.KeyedStorage;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ZoneManager implements KeyedStorage<DropWorld> {

    public ZoneManager() {
        worlds.addAll((Collection<? extends DropWorld>) FallingCratesPlus.getInstance().getZonesConfig().getList("Worlds", Collections.emptyList()));
        worlds.forEach(DropWorld::initZones);
    }


    private final ArrayList<DropWorld> worlds = new ArrayList<>();

    @Override
    public Collection<DropWorld> getObjects() {
        return worlds;
    }


    public void save() {
        FallingCratesPlus.getInstance().getZonesConfig().set("Worlds", worlds);
    }

    public DropZone findByLocation(Location location) {
        DropWorld world = findByKey(location.getWorld());
        if (world == null) return null;
        ProtectedRegion region = WorldGuardUtils.getHighestPriorityRegion(location);
        if (region == null) return null;
        return world.findByKey(region.getId());
    }


}
