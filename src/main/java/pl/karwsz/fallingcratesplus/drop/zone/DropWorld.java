package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.managers.KeyedStorage;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SerializableAs("DropWorld")
public class DropWorld implements Keyed<World>, ABSave, KeyedStorage<DropZone> {

    @YAMLElement
    private World world;

    @Override
    public World getKey() {
        return world;
    }

    @YAMLElement
    private Map<String, String> zoneSettings = new HashMap<>();

    private ArrayList<DropZone> zones = new ArrayList<>();

    public DropWorld(Map<String, Object> map) {
        deserializeFromMap(map);
    }


    public DropWorld(World world) {
        this.world = world;
        this.zones = new ArrayList<>();
    }

    void initZones() {
        for (Map.Entry<String, String> entry : zoneSettings.entrySet()) {
            register(new DropZone(entry.getKey(), DropZoneSettings.byId(entry.getValue())));
        }
    }


    public World getWorld() {
        return world;
    }

    public ArrayList<DropZone> getZones() {
        return zones;
    }

    @Override
    public Collection<DropZone> getObjects() {
        return zones;
    }


    @Override
    public @NotNull Map<String, Object> serialize() {
        zoneSettings.clear();
        zones.forEach(z -> {zoneSettings.put(z.getRegionId(), z.getSettings().getId());});
        return ABSave.super.serialize();
    }
}
